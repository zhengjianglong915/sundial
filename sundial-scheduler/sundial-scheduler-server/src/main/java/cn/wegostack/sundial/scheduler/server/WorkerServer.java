package cn.wegostack.sundial.scheduler.server;

import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.scheduler.server.service.ExecuteTaskServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class WorkerServer implements AutoCloseable {

    private Server server;

    private void start() throws IOException {
        int port = 8801;
        server = ServerBuilder.forPort(port)
                .addService(new ExecuteTaskServiceImpl())
                .build()
                .start();

        LogUtils.info("[worker] Server started, listening on ", port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LogUtils.error("[worker] shutting down gRPC server since JVM is shutting down");
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    /**
     * @throws InterruptedException
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    @Override
    public void close() throws Exception {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final WorkerServer server = new WorkerServer();
        server.start();
        server.blockUntilShutdown();
    }
}
