package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/allUsers")
    public String showAllUsers(Model model, Principal principal, @ModelAttribute ("user") User user) {
        model.addAttribute("admin", userService.findUserByEmail(principal.getName()));
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin/all-users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model, Principal principal) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("admin", userService.findUserByEmail(principal.getName()));
        return "admin/add-user";
    }

    @PostMapping("/addNewUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/allUsers";
    }

    @PutMapping("/edit/{id}")
    public String updateUserInfo(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/allUsers";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/allUsers";
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/user")
    public String infoUser(Model model, Principal principal) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("admin", userService.findUserByEmail(principal.getName()));
        return "admin/user";
    }

}
