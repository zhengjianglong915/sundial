package cn.wegostack.sundial.scheduler.core.invoker;


import cn.wegostack.sundial.common.constants.SundialPortConstants;
import cn.wegostack.sundial.common.model.ScheduleContext;
import cn.wegostack.sundial.common.model.Worker;
import cn.wegostack.sundial.common.model.command.ExecuteCommand;
import cn.wegostack.sundial.common.proto.ExecuteCommandProto;
import cn.wegostack.sundial.common.proto.ExecuteTaskServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class RpcInvoker implements IInvoker {

    /**
     * 远程连接管理器，管理连接的生命周期
     */
    private ManagedChannel channel;

    public RpcInvoker() {

    }

    @Override
    public boolean invoke(List<Worker> workers, ScheduleContext context) {
        for (Worker worker : workers) {
            // build request
            ExecuteCommand command = buildCommand(context);

            doInvoke(worker, command);

        }
        return false;
    }

    private ExecuteCommand buildCommand(ScheduleContext context) {
        return new ExecuteCommand();
    }

    private void doInvoke(Worker worker, ExecuteCommand executeCommand) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(worker.getOriginIp(),
                SundialPortConstants.WORKER_SERVER_PORT).usePlaintext().build();
        ExecuteTaskServiceGrpc.ExecuteTaskServiceBlockingStub stub = ExecuteTaskServiceGrpc.newBlockingStub(channel);
        ExecuteCommandProto.ExecuteCommand command = ExecuteCommandProto.ExecuteCommand.newBuilder().build();
        ExecuteCommandProto.CommandResult result = stub.execute(command);
        System.out.println("返回结果：" + result.getMessage());
    }
}
