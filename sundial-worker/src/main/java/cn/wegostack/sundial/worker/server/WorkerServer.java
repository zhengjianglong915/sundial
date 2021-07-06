package cn.wegostack.sundial.worker.server;

import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.registry.client.SundialRegistry;
import cn.wegostack.sundial.registry.client.api.Registry;
import cn.wegostack.sundial.registry.client.utils.DataIdUtils;
import cn.wegostack.sundial.worker.processor.ExecuteTaskServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class WorkerServer implements AutoCloseable {

    private Server server;

    public void start() throws IOException {
        int port = 8801;
        server = ServerBuilder.forPort(port)
                .addService(new ExecuteTaskServiceImpl())
                .build()
                .start();

        // registry
        SundialRegistry registry = new SundialRegistry("localhost");
        registry.init();

        String appName = System.getProperty("spring.application.name");
        if (StringUtils.isEmpty(appName)) {
            throw new RuntimeException("The name of application does not found by spring.application.name.");
        }
        String dataId = DataIdUtils.genDataId("Worker", appName);
        registry.publish(dataId);

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
    public void blockUntilShutdown() throws InterruptedException {
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
}
