package cn.wegostack.sundial.discovery.registry;

import cn.wegostack.sundial.discovery.registry.processor.SundialRegistryProcessor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-28
 */
@Service
public class SundialRegistryServer implements AutoCloseable {
    private Server server;

    @Autowired
    private SundialRegistryProcessor registryProcessor;

    public void start() throws IOException {
        int port = 8600;
        server = ServerBuilder.forPort(port)
                .addService(registryProcessor)
                .build()
                .start();

        System.out.println("[registry] Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("[worker] shutting down gRPC server since JVM is shutting down");
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
        final SundialRegistryServer server = new SundialRegistryServer();
        server.start();
        server.blockUntilShutdown();

    }
}
