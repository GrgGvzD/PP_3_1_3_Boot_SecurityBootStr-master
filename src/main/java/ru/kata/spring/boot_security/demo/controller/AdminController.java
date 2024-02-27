package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;

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

    @GetMapping("")
    public String getAllUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/show")
    public String getUserById(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String createUserFrom(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getListOfRoles());
        return "new";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getListOfRoles());
            return "new";
        }
        user.setPassword(user.getPassword());
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUserById(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", roleService.getListOfRoles());
        return "edit";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.getListOfRoles());
            return "edit";
        }
        user.setPassword(user.getPassword());
        userService.editUserById(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
