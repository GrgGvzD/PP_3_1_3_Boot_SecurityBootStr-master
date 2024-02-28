package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;
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


@GetMapping("")
public String printUsers(ModelMap modelMap, Principal principal) {
    modelMap.addAttribute("user", userService.findUserByLogin(principal.getName()));
    modelMap.addAttribute("usersList", userService.getAllUsers());
    modelMap.addAttribute("roles", roleService.getListOfRoles());
    modelMap.addAttribute("newUser", new User());
    return "admin";
}


@PostMapping("/add")
public String createUser(User user) {
    userService.addUser(user);
    return "redirect:/admin";
}



@PostMapping("/edit/{id}")
public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam("roles") Long[] rolesId) {
    userService.editUserById(user);

    return "redirect:/admin";
}
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);

        return "redirect:/admin";
    }


}
