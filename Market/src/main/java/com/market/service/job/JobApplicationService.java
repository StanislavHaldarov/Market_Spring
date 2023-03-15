package com.market.service.job;

import com.market.entity.job.JobApplication;

import java.util.List;

public interface JobApplicationService {
    void applyForJob(JobApplicationServiceModel jobApplicationServiceModel);

    List<JobApplication> getAllJobApplications();

    void deleteApplication(Long jobApplicationId, String action);
}
