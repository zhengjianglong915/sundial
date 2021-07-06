package cn.wegostack.sundial.discovery.registry.processor;


import cn.wegostack.sundial.discovery.registry.model.RegistryClient;
import cn.wegostack.sundial.discovery.registry.proto.SundialRegistryGrpc;
import cn.wegostack.sundial.discovery.registry.proto.SundialRegistryProto.HeartbeatCommand;
import cn.wegostack.sundial.discovery.registry.proto.SundialRegistryProto.PublishCommand;
import cn.wegostack.sundial.discovery.registry.proto.SundialRegistryProto.RegistryReply;
import cn.wegostack.sundial.discovery.registry.proto.SundialRegistryProto.SubscribeCommand;
import cn.wegostack.sundial.discovery.registry.session.RegistrySession;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author zhengjianglong
 * @since 2021-06-29
 */
@Service
public class SundialRegistryService extends SundialRegistryGrpc.SundialRegistryImplBase {

    @Autowired
    private RegistrySession session;

    @Override
    public void publish(PublishCommand request,
                        StreamObserver<RegistryReply> responseObserver) {
        // registry publish
        RegistryClient instance = buildInstance(responseObserver, request.getAppName(),
                request.getIp(), request.getHostName());

        session.registryPublisher(request.getDataId(), instance);

        // reply
        RegistryReply reply = RegistryReply.newBuilder()
                .setStatus("OK")
                .setType("publish")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void subscribe(SubscribeCommand request,
                          StreamObserver<RegistryReply> responseObserver) {
        RegistryClient instance = buildInstance(responseObserver, request.getAppName(),
                request.getIp(), request.getHostName());

        // registry subscriber
        session.registrySubscriber(request.getDataId(), instance);

        RegistryReply reply = RegistryReply.newBuilder()
                .setStatus("OK")
                .setType("publish")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private RegistryClient buildInstance(StreamObserver<RegistryReply> responseObserver,
                                         String appName, String ip, String hostname) {
        RegistryClient instance = new RegistryClient();
        instance.setIp(ip);
        instance.setHostname(hostname);
        instance.setAppName(appName);
        Date date = new Date();
        instance.setHeartbeat(date.getTime());
        instance.setObserver(responseObserver);
        return instance;
    }

    @Override
    public void heartbeat(HeartbeatCommand request,
                          StreamObserver<RegistryReply> responseObserver) {

    }
}
