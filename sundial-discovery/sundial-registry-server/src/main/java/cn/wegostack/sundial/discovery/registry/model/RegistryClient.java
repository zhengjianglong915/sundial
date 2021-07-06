package cn.wegostack.sundial.discovery.registry.model;

import io.grpc.stub.StreamObserver;
import lombok.Data;

/**
 * @author zhengjianglong
 * @since 2021-06-29
 */
@Data
public class RegistryClient {
    private String appName;

    private String ip;

    private String hostname;

    /**
     * heartbeat time
     */
    private Long heartbeat;

    /**
     * connection
     */
    private StreamObserver observer;
}
