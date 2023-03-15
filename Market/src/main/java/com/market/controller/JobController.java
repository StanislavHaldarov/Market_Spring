package com.market.controller;

import com.market.entity.job.JobApplication;
import com.market.entity.User;
import com.market.entity.binding.JobApplicationBindingModel;
import com.market.service.job.JobApplicationService;
import com.market.service.job.JobApplicationServiceModel;
import com.market.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/job")
public class JobController {
    private final JobApplicationService jobApplicationService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public JobController(JobApplicationService jobApplicationService, ModelMapper modelMapper, UserService userService) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }
    @GetMapping("/job-applications-management")
    public String allJobApplications(Model model)
    {
        Iterable<JobApplication> jobApplications = jobApplicationService.getAllJobApplications();
        model.addAttribute("jobApplications", jobApplications);
        return "job-applications-management";
    }
    @GetMapping("/employee-management")
    public String allEmployees(Model model)
    {
        Iterable<User> employees = userService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee-management";
    }

    @GetMapping("/apply")
    public String applyForm(Model model) {
        model.addAttribute("jobApplicationBindingModel", new JobApplicationBindingModel());
        return "job-application";
    }

    @PostMapping("/apply")
    public ModelAndView applyConfirm(@Valid @ModelAttribute JobApplicationBindingModel jobApplicationBindingModel,
                                     BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("job-application");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("jobApplicationBindingModel", jobApplicationBindingModel);
            modelAndView.addObject("org.springframework.validation.BindingResult.jobApplicationBindingModel", bindingResult);
            return modelAndView;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return modelAndView;
        }
        JobApplicationServiceModel jobApplicationServiceModel = modelMapper.map(jobApplicationBindingModel, JobApplicationServiceModel.class);
        jobApplicationServiceModel.setUser(user);
        jobApplicationService.applyForJob(jobApplicationServiceModel);

        return new ModelAndView("redirect:/products/available");
    }


    @PostMapping("/update-salary/{id}")
    public ModelAndView updateSalary(@PathVariable(name = "id") Long employeeId,
                                     @RequestParam Double salary,
                                     RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/job/employee-management");
        if(salary<0) {
            redirectAttributes.addFlashAttribute("errorSalary", "Заплатата не може да бъде отрицателно число!");
            return modelAndView;
        }
        userService.updateSalaryById(employeeId, salary);
        return modelAndView;
    }
    @PostMapping("/fire/{id}")
    public ModelAndView fireEmployee(@PathVariable(name = "id") Long employeeId)
    {
        userService.fireEmployeeById(employeeId);
        return new ModelAndView("redirect:/job/employee-management");
    }

    @PostMapping("/hire/{id}")
    public ModelAndView hireEmployee(@PathVariable(name = "id") Long jobApplicationId)
    {
        jobApplicationService.deleteApplication(jobApplicationId, "hire");
        return new ModelAndView("redirect:/job/job-applications-management");
    }
    @PostMapping("/reject/{id}")
    public ModelAndView rejectApplication(@PathVariable(name = "id") Long jobApplicationId)
    {
        jobApplicationService.deleteApplication(jobApplicationId, null);
        return new ModelAndView("redirect:/job/job-applications-management");
    }
}
