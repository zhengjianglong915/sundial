package cn.wegostack.sundial.scheduler.core.cluster.server;

import cn.wegostack.sundial.common.proto.ClusterServiceGrpc;
import cn.wegostack.sundial.common.proto.SundialClusterProto.ClusterCommand;
import cn.wegostack.sundial.common.proto.SundialClusterProto.ClusterResult;
import cn.wegostack.sundial.common.utils.LocalServer;
import com.google.common.collect.Maps;
import io.grpc.stub.StreamObserver;

import java.util.Map;

public class ClusterService extends ClusterServiceGrpc.ClusterServiceImplBase {
    private Map<String, StreamObserver<ClusterResult>> serverMap = Maps.newConcurrentMap();

    @Override
    public StreamObserver<ClusterCommand> join(
            StreamObserver<ClusterResult> responseObserver) {
        return new StreamObserver<ClusterCommand>() {
            @Override
            public void onNext(ClusterCommand clusterCommand) {
                String cluster = clusterCommand.getCluster();
                if (!LocalServer.getCluster().equals(cluster)) {
                    // cluster does not match
                    return;
                }

                // update
                String ip = clusterCommand.getIp();
                serverMap.put(ip, responseObserver);

                // connection event

            }

            @Override
            public void onError(Throwable throwable) {
                // connection closed event

            }

            @Override
            public void onCompleted() {
                // connection closed event

            }
        };
    }
}
