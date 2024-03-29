package cn.wegostack.sundial.scheduler.dal.repository;

import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Repository
public interface JobRepository extends JpaRepository<JobDO, Long> {

    JobDO findByJobId(String jobId);
}
