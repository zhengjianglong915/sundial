package cn.wegostack.sundial.scheduler.dal.repository;

import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    @Modifying(flushAutomatically = true)
    @Query(value = "update t_job_trigger set load_status = :loadStatus, load_server = :loadServer"
            + " where slot = :slot and load_server != :loadServer",
            nativeQuery = true)
    int updateLoadServerBySlot(@Param("loadStatus") String loadStatus,
                               @Param("loadStatus") String loadServer,
                               @Param("slot") Integer slot);

}