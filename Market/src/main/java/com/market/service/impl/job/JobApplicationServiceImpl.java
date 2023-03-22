package com.market.service.impl.job;

import com.market.entity.job.JobApplication;
import com.market.util.enums.RoleNameEnum;
import com.market.entity.User;
import com.market.repository.JobApplicationRepository;
import com.market.repository.UserRepository;
import com.market.service.job.JobApplicationService;
import com.market.service.job.JobApplicationServiceModel;
import com.market.service.user.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplicationServiceImpl(ModelMapper modelMapper, RoleService roleService, JobApplicationRepository jobApplicationRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void applyForJob(JobApplicationServiceModel jobApplicationServiceModel) {
        JobApplication jobApplication = modelMapper.map(jobApplicationServiceModel, JobApplication.class);
        if(jobApplicationRepository.getJobApplicationByUser(jobApplication.getUser().getId()) != null)
        {
            JobApplication deleteJobApplication = jobApplicationRepository.getJobApplicationByUser(jobApplication.getUser().getId());
            jobApplicationRepository.delete(deleteJobApplication);
        }
        jobApplication.setUser(jobApplicationServiceModel.getUser());
        jobApplicationRepository.save(jobApplication);

    }

    @Override
    public List<JobApplication> getAllJobApplications() {
        List<JobApplication> allJobApplications = jobApplicationRepository.findAll();
        if (allJobApplications.isEmpty()) {
            return null;
        }
        return allJobApplications;
    }

    @Override
    public void deleteApplication(Long jobApplicationId, String action) {
        if(action.equals("hire"))
        {
            Optional<JobApplication> jAO= jobApplicationRepository.findById(jobApplicationId);
            if(jAO.isPresent())
            {
                JobApplication jobApplication = jAO.get();
                User user = jobApplication.getUser();
                user.setRole(roleService.findRole(RoleNameEnum.EMPLOYEE));
                user.setPhoneNumber(jobApplication.getPhoneNumber());
                user.setSalary(1000.0);
                userRepository.save(user);
            }
        }
        jobApplicationRepository.deleteById(jobApplicationId);
    }
}
