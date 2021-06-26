package cn.wegostack.sundial.scheduler.server.service;

import cn.wegostack.sundial.common.proto.ExecuteCommandProto;
import cn.wegostack.sundial.common.proto.ExecuteTaskServiceGrpc;
import cn.wegostack.sundial.common.utils.LogUtils;
import io.grpc.stub.StreamObserver;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class ExecuteTaskServiceImpl extends ExecuteTaskServiceGrpc.ExecuteTaskServiceImplBase {

    @Override
    public void execute(ExecuteCommandProto.ExecuteCommand request,
                        StreamObserver<ExecuteCommandProto.CommandResult> responseObserver) {
        LogUtils.info("[%s] received schedule event from sundial server");

        ExecuteCommandProto.CommandResult commandResult = ExecuteCommandProto.CommandResult
                .newBuilder().setMessage("success").build();
        responseObserver.onNext(commandResult);
        responseObserver.onCompleted();
    }
}
