package cn.wegostack.sundial.scheduler.core.cluster;

import cn.wegostack.sundial.common.enums.LoadStatus;
import cn.wegostack.sundial.common.enums.ServerStatus;
import cn.wegostack.sundial.common.model.Server;
import cn.wegostack.sundial.common.threadpool.ScheduledService;
import cn.wegostack.sundial.common.utils.HostUtils;
import cn.wegostack.sundial.common.utils.LocalServer;
import cn.wegostack.sundial.scheduler.dal.service.JobTriggerService;
import cn.wegostack.sundial.scheduler.dal.service.ServerService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhengjianglong
 * @since 2021-07-10
 */
@Service
public class SundialCluster implements Cluster {
    private Logger LOGGER = LoggerFactory.getLogger(SundialCluster.class);

    private volatile boolean isExist = false;
    private volatile boolean isJoin = false;

    private ScheduledService scheduledService;

    private Map<String, Server> serverMap = Maps.newConcurrentMap();

    @Autowired
    private ServerService serverService;

    @Autowired
    private JobTriggerService triggerService;

    public SundialCluster() {

    }

    @Override
    public void join(String cluster) {
        if (isJoin) {
            return;
        }

        synchronized (this) {
            if (isJoin) {
                return;
            }

            String localIp = HostUtils.getHostIp();
            Server server = serverService.queryByIp(cluster, localIp);
            if (server == null) {
                server = buildServer(cluster, localIp);
                serverService.add(server);
            }

            scheduledService = new ScheduledService("Sundial-Cluster", () -> {
                if (isExist) {
                    return;
                }
                try {
                    // update
                    serverService.updateHeartBeat(cluster, localIp);
                    LOGGER.debug("[Cluster] update heartbeat. {} {}", cluster, localIp);

                    Date date = serverService.queryCurrentTime();
                    Date timeoutTime = getRejectTime(date);

                    // query cluster server
                    List<Server> serverList = serverService.getRunningServers(cluster, timeoutTime);
                    boolean isChange = isServerChange(serverList);
                    if (isChange) {
                        Set<String> serverSet = serverList.stream().map(Server::getIp)
                                .collect(Collectors.toSet());
                        LOGGER.info("[Cluster] cluster server is change. new server list:{}",
                                JSON.toJSONString(serverSet));
                        ConsistantHash.load(serverSet);

                        Set<Integer> oldSlots = SlotManager.getAllSlots();
                        SlotManager.refresh();
                        LOGGER.info("[Cluster] refresh slot");
                        Set<Integer> newSlots = SlotManager.getAllSlots();

                        for (Integer slot : newSlots) {
                            if (!oldSlots.contains(slot)) {
                                triggerService.updateLoadServerBySlot(LoadStatus.INIT.INIT.name(),
                                        LocalServer.getIp(), slot);
                            }
                        }
                    }

                    LOGGER.debug("[Cluster] running server {}", JSON.toJSONString(serverList));
                } catch (Exception e) {
                    LOGGER.error("[Cluster] sync exception.", e);
                }
            }, 0, 1, TimeUnit.SECONDS);
            // sync heartbeat every second
            scheduledService.start();
            isJoin = true;
        }
    }

    private Server buildServer(String cluster, String localIp) {
        Server server = new Server();
        server.setCluster(cluster);
        server.setIp(localIp);
        server.setHostname(HostUtils.getHostname());
        server.setStatus(ServerStatus.RUNNING);
        return server;
    }

    private boolean isServerChange(List<Server> serverList) {
        boolean isChange = false;
        Map<String, Server> newMap = Maps.newConcurrentMap();
        for (Server server : serverList) {
            String key = server.getCluster() + "-" + server.getIp();
            newMap.put(key, server);
            if (!serverMap.containsKey(key)) {
                isChange = true;
            }
        }

        if (isChange) {
            serverMap = newMap;
            return true;
        }

        for (String key : serverMap.keySet()) {
            if (!newMap.containsKey(key)) {
                serverMap = newMap;
                return true;
            }
        }

        return false;
    }

    private Date getRejectTime(Date current) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.SECOND, -3);
        return calendar.getTime();
    }

    @Override
    public void exit(String cluster) {
        isExist = true;
    }
}
