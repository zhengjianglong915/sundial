package cn.wegostack.sundial.scheduler.dal.repository;

import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Repository
public interface JobTriggerRepository extends JpaRepository<JobTriggerDO, Long> {

    /**
     * update load server by slot
     *
     * @param loadStatus
     * @param slot
     * @return
     */
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "update JobTriggerDO j set j.loadStatus = :loadStatus, j.loadServer = :loadServer"
            + " where j.slot = :slot")
    int updateLoadServerBySlot(@Param("loadStatus") String loadStatus,
                               @Param("loadServer") String loadServer,
                               @Param("slot") Integer slot);

}
