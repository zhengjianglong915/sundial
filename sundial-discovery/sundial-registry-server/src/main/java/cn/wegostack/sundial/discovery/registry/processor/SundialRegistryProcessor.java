package cn.wegostack.sundial.discovery.registry.processor;


import cn.wegostack.sundial.discovery.registry.model.RegistryClient;
import cn.wegostack.sundial.discovery.registry.session.RegistrySession;
import cn.wegostack.sundial.registry.client.ReplyStatus;
import cn.wegostack.sundial.registry.client.enums.ReplyType;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryGrpc;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.HeartbeatCommand;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.PublishCommand;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.RegistryReply;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.SubscribeCommand;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhengjianglong
 * @since 2021-07-07
 */
@Service
public class SundialRegistryProcessor extends SundialRegistryGrpc.SundialRegistryImplBase {

    @Autowired
    private RegistrySession session;


    @Override
    public StreamObserver<PublishCommand> publish(StreamObserver<RegistryReply> responseObserver) {
        return new StreamObserver<PublishCommand>() {
            @Override
            public void onNext(PublishCommand command) {
                // registry publish
                RegistryClient instance = buildInstance(responseObserver, command.getAppName(),
                        command.getIp(), command.getHostName());
                session.registryPublisher(command.getDataId(), instance);

                // reply
                RegistryReply reply = RegistryReply.newBuilder()
                        .setStatus(ReplyStatus.OK.name())
                        .setType(ReplyType.PUBLISH.name())
                        .build();
                responseObserver.onNext(reply);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("publisher connection closed");
                // session.remove();
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<SubscribeCommand> subscribe(StreamObserver<RegistryReply> responseObserver) {
        return new StreamObserver<SubscribeCommand>() {
            @Override
            public void onNext(SubscribeCommand command) {
                RegistryClient instance = buildInstance(responseObserver, command.getAppName(),
                        command.getIp(), command.getHostName());

                // registry subscriber
                session.registrySubscriber(command.getDataId(), instance);

                RegistryReply reply = RegistryReply.newBuilder()
                        .setStatus(ReplyStatus.OK.name())
                        .setType(ReplyType.SUBSCRIBE.name())
                        .build();
                responseObserver.onNext(reply);

                // notify
                RegistryReply notifyReply = session.getNotifyReply(command.getDataId());
                if (null != notifyReply) {
                    responseObserver.onNext(notifyReply);
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("subscribe connection closed");
                // session.remove();
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HeartbeatCommand> heartbeat(StreamObserver<RegistryReply> responseObserver) {
        return super.heartbeat(responseObserver);
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
}
