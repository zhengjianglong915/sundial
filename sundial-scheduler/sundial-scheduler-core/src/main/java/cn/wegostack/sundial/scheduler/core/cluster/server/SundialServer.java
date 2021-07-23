package cn.wegostack.sundial.scheduler.core.cluster.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SundialServer implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SundialServer.class);

    private Server server;

    public void start() throws IOException {
        int port = 8601;
        server = ServerBuilder.forPort(port)
                .addService(new ClusterService())
                .build()
                .start();

        LOGGER.info("[server] Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.error("[server] shutting down gRPC server since JVM is shutting down");
            try {
                this.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            LOGGER.error("[server] *** server shut down");
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
