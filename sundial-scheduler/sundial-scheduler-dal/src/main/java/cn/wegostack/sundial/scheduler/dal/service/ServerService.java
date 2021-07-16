package cn.wegostack.sundial.scheduler.dal.service;

import cn.wegostack.sundial.scheduler.dal.entity.Server;
import cn.wegostack.sundial.scheduler.dal.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

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
     * @param serverDO
     */
    public void add(Server serverDO) {
        serverRepository.saveAndFlush(serverDO);
    }

    /**
     * Query server in special cluster by ip.
     *
     * @param cluster
     * @param ip
     * @return
     */
    public Server queryByIp(String cluster, String ip) {
        Server serverDO = new Server();
        serverDO.setIp(ip);
        serverDO.setCluster(cluster);

        Example example = Example.of(serverDO);
        List<Server> serverDOList = serverRepository.findAll(example);
        return CollectionUtils.isEmpty(serverDOList) ? null : serverDOList.get(0);
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
        return serverRepository.queryAllRunningServers(cluster, timeout);
    }
}
