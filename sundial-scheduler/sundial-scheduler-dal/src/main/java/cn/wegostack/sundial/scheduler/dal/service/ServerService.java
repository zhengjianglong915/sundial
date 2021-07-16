package cn.wegostack.sundial.scheduler.dal.service;

import cn.wegostack.sundial.common.model.Server;
import cn.wegostack.sundial.scheduler.dal.convector.ServerConvector;
import cn.wegostack.sundial.scheduler.dal.entity.ServerDO;
import cn.wegostack.sundial.scheduler.dal.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author zhengjianglong
 * @since 2021-07-10
 */
@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    /**
     * Add server
     *
     * @param server
     */
    public boolean add(Server server) {
        ServerDO serverDO = ServerConvector.convect(server);
        return serverRepository.saveAndFlush(serverDO) != null;
    }

    /**
     * Query server in special cluster by ip.
     *
     * @param cluster
     * @param ip
     * @return
     */
    public Server queryByIp(String cluster, String ip) {
        ServerDO serverDO = new ServerDO();
        serverDO.setIp(ip);
        serverDO.setCluster(cluster);
        Example example = Example.of(serverDO);
        Optional<ServerDO> one = serverRepository.findOne(example);
        if (!one.isPresent()) {
            return null;
        }
        return ServerConvector.convect(one.get());
    }

    /**
     * Update heartbeat of server by ip
     *
     * @param cluster
     * @param localIp
     * @return
     */
    public boolean updateHeartBeat(String cluster, String localIp) {
        return serverRepository.updateHeartBeat(cluster, localIp) > 0;
    }

    public Date queryCurrentTime() {
        return serverRepository.queryCurrentTime();
    }

    /**
     * Query all running servers in special cluster, which heartbeat is after timeout
     *
     * @param cluster
     * @param timeout
     * @return
     */
    public List<Server> getRunningServers(String cluster, Date timeout) {
        return ServerConvector.convect(serverRepository.queryAllRunningServers(cluster, timeout));
    }
}
