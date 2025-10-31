package org.yiqixue.bootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.yiqixue.bootshop.service.UserService;
import org.yiqixue.bootshop.entity.User;
import java.util.Optional;

@Controller
public class WebController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "saveUser";
    }

    @GetMapping("/editUser/{userId}")
    public String editUser(@PathVariable Integer userId, Model model) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "saveUser";
        }
        return "redirect:/users";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        if (user.getUserId() != null) {
            userService.updateUser(user);
        } else {
            userService.saveUser(user);
        }
        return "redirect:/users";
    }
}
