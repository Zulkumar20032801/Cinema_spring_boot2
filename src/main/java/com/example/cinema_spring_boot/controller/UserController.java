package com.example.cinema_spring_boot.controller;
import com.example.cinema_spring_boot.layer.impl.UserLayer;
import com.example.cinema_spring_boot.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserLayer userLayer;

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("user", new User());
        return "/user/save";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user) {
        userLayer.sendMessage(user.getEmail());
        userLayer.save(user);
        return "redirect:/cinema/find_all";
    }
}

