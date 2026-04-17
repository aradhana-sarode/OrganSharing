package com.example.organsharing.Controller;



import com.example.organsharing.repository.UserRepository;

import com.example.organsharing.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@Controller
public class AuthController {
	@Autowired
    private UserRepository userRepo;
	
	 @Autowired
	    private JavaMailSender mailSender;
	

private Map<String, String> otpStorage = new HashMap<>();

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
        return "redirect:/dashboard";
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

    
    
    // 🔹 OPEN FORGOT PASSWORD PAGE
    @GetMapping("/forgot-password")
    public String forgotPage() {
        return "forgot-password";
    }

    // 🔹 SEND OTP
    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email, Model model) {

        UserService user = userRepo.findByEmail(email);

        if (user == null) {
            model.addAttribute("error", "Email not registered!");
            return "forgot-password";
        }

        String otp = String.format("%06d", new Random().nextInt(999999));
        otpStorage.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

        model.addAttribute("email", email);
        model.addAttribute("message", "OTP sent to your email");

        return "verify-otp";
    }

    // 🔹 VERIFY OTP + RESET PASSWORD
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            @RequestParam String newPassword,
                            Model model) {

        String storedOtp = otpStorage.get(email);

        if (storedOtp == null || !storedOtp.equals(otp)) {
            model.addAttribute("error", "Invalid OTP!");
            model.addAttribute("email", email);
            return "verify-otp";
        }

        UserService user = userRepo.findByEmail(email);
        user.setPassword(newPassword);
        userRepo.save(user);

        otpStorage.remove(email);

        model.addAttribute("message", "Password reset successful!");
        return "login";
    }
   
}