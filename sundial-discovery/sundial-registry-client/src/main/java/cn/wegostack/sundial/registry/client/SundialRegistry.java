package cn.wegostack.sundial.registry.client;


import cn.wegostack.sundial.registry.client.api.Registry;
import cn.wegostack.sundial.registry.client.api.SubscribeDataListener;
import cn.wegostack.sundial.registry.client.enums.ReplyType;
import cn.wegostack.sundial.registry.client.model.DataInfo;
import cn.wegostack.sundial.registry.client.model.Publisher;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryGrpc;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.PublishCommand;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.RegistryReply;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.SubscribeCommand;
import cn.wegostack.sundial.registry.client.utils.HostUtils;
import com.alibaba.fastjson.JSON;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
public class SundialRegistry implements Registry {
    private static final Logger LOGGER = LoggerFactory.getLogger(SundialRegistry.class);

    private final static int REGISTRY_PORT = 8600;

    private static Map<String, List<Publisher>> publisherMap = new HashMap<>();

    private static Map<String, SubscribeDataListener> subscribeMap = new HashMap<>();

    private String registryEndpoint;

    private static SundialRegistryGrpc.SundialRegistryStub registryStub;


    private static StreamObserver<RegistryReply> responseObserver;

    public SundialRegistry(String registryEndpoint) {
        this.registryEndpoint = registryEndpoint;
    }

    private volatile boolean inited = false;


    public synchronized void init() {
        if (inited) {
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder.forAddress(registryEndpoint,
                REGISTRY_PORT).usePlaintext().build();
        registryStub = SundialRegistryGrpc.newStub(channel);

        responseObserver = newResponseObserver();
        inited = true;
    }

    @Override
    public boolean publish(String dataId) {
        String appName = System.getProperty("spring.application.name");
        Publisher publisher = new Publisher();
        publisher.setAppName(appName);
        publisher.setIp(HostUtils.getHostIp());

        publisherMap.putIfAbsent(dataId, new ArrayList<>());
        List<Publisher> publishers = publisherMap.get(dataId);
        publishers.add(publisher);

        PublishCommand command = PublishCommand.newBuilder()
                .setDataId(dataId)
                .setAppName(appName)
                .setIp(HostUtils.getHostIp())
                .setHostName(HostUtils.getHostname())
                .build();

        StreamObserver<PublishCommand> publish = registryStub.publish(responseObserver);
        publish.onNext(command);
        return true;
    }

    @Override
    public boolean unPublish(String dataId) {
        return false;
    }

    @Override
    public boolean subscribe(String dataId, SubscribeDataListener subscribeDataListener) {
        SubscribeDataListener listener = subscribeMap.get(dataId);
        if (null != listener) {
            return true;
        }
        subscribeMap.put(dataId, subscribeDataListener);

        SubscribeCommand command = SubscribeCommand.newBuilder()
                .setDataId(dataId)
                .setIp(HostUtils.getHostIp())
                .setHostName(HostUtils.getHostname())
                .build();
        StreamObserver<SubscribeCommand> subscribe = registryStub.subscribe(responseObserver);
        subscribe.onNext(command);
        return true;
    }

    @Override
    public boolean unSubscribe(String dataId) {
        return false;
    }

    private StreamObserver<RegistryReply> newResponseObserver() {
        return new StreamObserver<RegistryReply>() {
            @Override
            public void onNext(RegistryReply reply) {
                String type = reply.getType();
                if (ReplyType.NOTIFY.name().equals(type)) {
                    DataInfo dataInfo = JSON.parseObject(reply.getContent(), DataInfo.class);
                    String dataId = dataInfo.getDataId();
                    SubscribeDataListener listener = subscribeMap.get(dataId);
                    if (null != listener) {
                        List<Publisher> publishers = dataInfo.getPublishers();
                        listener.notify(dataId, publishers);
                    }
                } else if (ReplyType.SUBSCRIBE.name().equals(type)) {
                    LOGGER.info("[registry] subscribe data success.");

                } else if (ReplyType.PUBLISH.name().equals(type)) {
                    LOGGER.info("[registry] publish data success.");
                }
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
            }
        };
    }
}
