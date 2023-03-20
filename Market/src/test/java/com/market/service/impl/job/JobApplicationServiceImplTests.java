package com.market.service.impl.job;

import com.market.entity.job.JobApplication;
import com.market.entity.Role;
import com.market.utility.enums.RoleNameEnum;
import com.market.entity.User;
import com.market.repository.JobApplicationRepository;
import com.market.repository.UserRepository;
import com.market.service.job.JobApplicationServiceModel;
import com.market.service.user.RoleService;
import com.market.service.impl.job.JobApplicationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceImplTests {
    @InjectMocks
    private JobApplicationServiceImpl jobApplicationService;
    @Mock
    private RoleService roleService;
    @Mock
    private JobApplicationRepository jobApplicationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testDeleteApplication() {
        JobApplication testJobApplication = new JobApplication();
        testJobApplication.setPhoneNumber("test");
        User testUser = new User();
        testJobApplication.setUser(testUser);
        Long testId = 1L;
        testUser.setRole(new Role(RoleNameEnum.EMPLOYEE));
        testUser.setSalary(1000.0);
        testUser.setPhoneNumber(testJobApplication.getPhoneNumber());
        String action = "hire";
        when(jobApplicationRepository.findById(testId))
                .thenReturn(Optional.of(testJobApplication));
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(roleService.findRole(RoleNameEnum.EMPLOYEE))
                .thenReturn(new Role(RoleNameEnum.EMPLOYEE));
        jobApplicationService.deleteApplication(testId, action);
        verify(jobApplicationRepository, Mockito.times(1))
                .findById(testId);
        verify(roleService, Mockito.times(1))
                .findRole(RoleNameEnum.EMPLOYEE);
        verify(userRepository, Mockito.times(1))
                .save(testUser);
        verify(jobApplicationRepository, Mockito.times(1))
                .deleteById(testId);
    }

    @Test
    public void testGetAllJobApplications() {
        List<JobApplication> allJobApplications = new ArrayList<>();
        JobApplication jobApplication = new JobApplication();
        allJobApplications.add(jobApplication);
        when(jobApplicationRepository.findAll()).
                thenReturn(allJobApplications);
        List<JobApplication> testJobApplications = jobApplicationService.getAllJobApplications();
        assertEquals(allJobApplications,testJobApplications);

    }
    @Test
    public void testGetAllJobApplicationsWhenListIsEmpty() {
        List<JobApplication> allJobApplications = new ArrayList<>();
        when(jobApplicationRepository.findAll()).
                thenReturn(allJobApplications);
        List<JobApplication> testJobApplications = jobApplicationService.getAllJobApplications();
        assertNull(testJobApplications);

    }
    @Test
    public void testApplyForJob(){
        JobApplicationServiceModel jobApplicationServiceModel = new JobApplicationServiceModel();
        User testUser = new User();
        testUser.setId(1L);
        jobApplicationServiceModel.setId(1L);
        jobApplicationServiceModel.setUser(testUser);
        jobApplicationServiceModel.setDescription("test");
        jobApplicationServiceModel.setPhoneNumber("test");
        JobApplication jobApplicationToSave = new JobApplication();
        jobApplicationToSave.setId(1L);
        jobApplicationToSave.setDescription("test");
        jobApplicationToSave.setPhoneNumber("test");
        jobApplicationToSave.setUser(jobApplicationServiceModel.getUser());
        when(modelMapper.map(jobApplicationServiceModel, JobApplication.class))
                .thenReturn(jobApplicationToSave);
        when(jobApplicationRepository.getJobApplicationByUser(jobApplicationToSave.getUser().getId()))
                .thenReturn(jobApplicationToSave);
        when(jobApplicationRepository.save(jobApplicationToSave)).thenReturn(jobApplicationToSave);
        jobApplicationService.applyForJob(jobApplicationServiceModel);
        verify(modelMapper, Mockito.times(1))
                .map(jobApplicationServiceModel,JobApplication.class);
        verify(jobApplicationRepository, Mockito.times(2))
                .getJobApplicationByUser(jobApplicationToSave.getUser().getId());
        verify(jobApplicationRepository, Mockito.times(1))
                .delete(jobApplicationToSave);
        verify(jobApplicationRepository, Mockito.times(1))
                .save(jobApplicationToSave);
    }
}
