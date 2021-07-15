package cn.wegostack.sundial.scheduler.dal.repository;

import cn.wegostack.sundial.scheduler.dal.entity.ServerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Repository
public interface ServerRepository extends JpaRepository<ServerDO, Long> {

    /**
     * update heartbeat of server
     *
     * @param cluster
     * @param localIp
     * @return
     */
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update t_server set heartbeat = CURRENT_TIMESTAMP where cluster = :cluster and ip = :localIp",
            nativeQuery = true)
    int updateHeartBeat(@Param("cluster") String cluster, @Param("localIp") String localIp);

    /**
     * Query current time of database
     *
     * @return
     */
    @Query(value = "select CURRENT_TIMESTAMP", nativeQuery = true)
    Date queryCurrentTime();

    /**
     * Query all running servers
     *
     * @param cluster
     * @return
     */
    @Query(value = "select * from t_server where cluster = :cluster and heartbeat > :timeout", nativeQuery = true)
    List<ServerDO> queryAllRunningServers(@Param("cluster") String cluster, @Param("timeout") Date timeout);
}
