package cn.wegostack.sundial.registry.client;


import cn.wegostack.sundial.registry.client.api.Registry;
import cn.wegostack.sundial.registry.client.api.SubscribeDataListener;
import cn.wegostack.sundial.registry.client.model.Publisher;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryGrpc;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.PublishCommand;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.RegistryReply;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.SubscribeCommand;
import cn.wegostack.sundial.registry.client.utils.HostUtils;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
public class SundialRegistry implements Registry {

    private final static int REGISTRY_PORT = 8600;

    private Map<String, List<Publisher>> publisherMap = new HashMap<>();

    private Map<String, SubscribeDataListener> subscribeMap = new HashMap<>();

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
                .setAppName(appName)
                .setIp(HostUtils.getHostIp())
                .setHostName(HostUtils.getHostname())
                .build();

        StreamObserver<PublishCommand> publish = registryStub.publish(responseObserver);
        publish.onNext(command);
        publish.onCompleted();
        return false;
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
        return false;
    }

    @Override
    public boolean unSubscribe(String dataId) {
        return false;
    }

    private StreamObserver<RegistryReply> newResponseObserver() {
        return new StreamObserver<RegistryReply>() {
            @Override
            public void onNext(RegistryReply registryReply) {
                System.out.println(registryReply.getContent());
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
