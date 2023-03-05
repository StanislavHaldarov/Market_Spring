package com.market.controller;

import com.market.entity.Role;
import com.market.entity.RoleNameEnum;
import com.market.entity.User;
import com.market.repository.RoleRepository;
import com.market.repository.UserRepository;
import com.market.service.RoleService;
import com.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/user-manager")
    public String getAllUsers(Model model) {
        Iterable<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        Iterable<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "user-manager";
    }
    @PostMapping("/update")
    public String updateRole(@ModelAttribute User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "redirect:user-manager";
        }
        userService.updateUserRole(user);
        return "redirect:user-manager";
    }
    @PostMapping("/delete/{userId}")
    public ModelAndView deleteResort(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return new ModelAndView("redirect:/admin/user-manager");
    }

}
