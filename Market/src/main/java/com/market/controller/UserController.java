package com.market.controller;

import com.market.entity.User;
import com.market.entity.binding.UserLoginBindingModel;
import com.market.entity.binding.UserRegisterBindingModel;
import com.market.repository.UserRepository;
import com.market.service.UserService;
import com.market.service.UserServiceModel;
import com.market.utility.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
        @RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model) {
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());

        }
        return "login";}
    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession)
    {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }
        User user = userService.findUserByUsername(userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());

        if(user==null) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            return "redirect:login";
        }

        httpSession.setAttribute("user", user);

        return "redirect:/products";
    }

    @GetMapping("/register")
    public String register(Model model, @ModelAttribute("error") String error){
        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        model.addAttribute("error", error);
        return "register";
    }
    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return new ModelAndView("redirect:/users/register");
        }

        try {
            UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel,UserServiceModel.class);
            userService.registerUser(userServiceModel);
        }
        catch(NotFoundException e){
            if(e.getMessage().equals("Потребител с такъв имейл вече е регистриран!")) {
                return new ModelAndView("redirect:/users/register").addObject("errorEmail", e.getMessage());
            }
            else{
                return new ModelAndView("redirect:/users/register").addObject("errorUsername", e.getMessage());
            }
        }
        return new ModelAndView("redirect:/products/all");
    }



//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "/register";
//    }
//    @PostMapping("/process_register")
//    public ModelAndView processRegister(@Valid User user, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            return new ModelAndView("/register");
//        } else{
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//            user.setEnabled(true);
//            user.setPassword(encodedPassword);
//            Role role = new Role();
//            role.setName(RoleNameEnum.CUSTOMER);
//            user.setRole(role);
//            userRepository.save(user);
//            return new ModelAndView("redirect:/login");
//        }
//    }
}
