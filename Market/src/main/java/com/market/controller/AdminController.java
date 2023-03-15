package com.market.controller;

import com.market.entity.Role;
import com.market.entity.User;
import com.market.service.user.RoleService;
import com.market.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user-management")
    public String getAllUsers(Model model) {
        Iterable<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        Iterable<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "user-management";
    }
    @PostMapping("/update")
    public String updateRole(@ModelAttribute User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "redirect:user-management";
        }
        userService.updateUserRole(user);
        return "redirect:user-management";
    }
    @PostMapping("/delete/{userId}")
    public ModelAndView deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return new ModelAndView("redirect:/admin/user-management");
    }

}
