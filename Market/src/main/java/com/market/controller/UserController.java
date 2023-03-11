package com.market.controller;

import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import com.market.entity.User;
import com.market.entity.binding.UserLoginBindingModel;
import com.market.entity.binding.UserRegisterBindingModel;
import com.market.service.UserService;
import com.market.service.UserServiceModel;
import com.market.utility.exception.EmailAlreadyExistsException;
import com.market.utility.exception.PasswordVerificationException;
import com.market.utility.exception.UsernameAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult,
                                     HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
            modelAndView.addObject("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return modelAndView;
        }
        try {
            User user = userService.findUserByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
            httpSession.setAttribute("user", user);
        }
        catch (Exception e) {

            if (e instanceof UsernameNotFoundException) {
                modelAndView.addObject("errorUsername", e.getMessage());
            }
            if (e instanceof PasswordVerificationException) {
                modelAndView.addObject("errorPassword", e.getMessage());
            }
            modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
            return modelAndView;
        }

        return new ModelAndView("/products/all");
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("register");
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                modelAndView.addObject("errorMsg", "Паролите не съвпадат!");
            }
            modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
            modelAndView.addObject("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return modelAndView;
        }
        try {
            UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
            userServiceModel.setRole(new Role(RoleNameEnum.CUSTOMER));
            userService.registerUser(userServiceModel);
        } catch (Exception e) {

            if (e instanceof UsernameAlreadyExistsException) {
                modelAndView.addObject("errorUsername", e.getMessage());
            }
            if (e instanceof EmailAlreadyExistsException) {
                modelAndView.addObject("errorEmail", e.getMessage());
            }
            modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
            return modelAndView;

        }
        return new ModelAndView("/products/all");
    }

}
