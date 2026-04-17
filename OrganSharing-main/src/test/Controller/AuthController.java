package com.example.organsharing.Controller;



import com.example.organsharing.repository.UserRepository;
import com.example.organsharing.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
	@Autowired
    private UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserService user, Model model) {

        if (userRepo.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        userRepo.save(user);
        return "redirect:/";
    }

    @PostMapping("/login_action")
    public String login(String email, String password, Model model) {

        UserService user = userRepo.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid Credentials!");
            return "login";
        }

        model.addAttribute("user", user);
        return "dashboard";
    }
}