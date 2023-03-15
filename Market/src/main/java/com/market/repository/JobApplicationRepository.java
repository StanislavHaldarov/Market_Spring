package com.market.repository;

import com.market.entity.job.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    @Query("SELECT u FROM JobApplication u WHERE u.user.id = :userId")
    JobApplication getJobApplicationByUser(@Param("userId") Long userId);
}
