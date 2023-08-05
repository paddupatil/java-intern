package controller;

//AuthController.java

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CustomerService;

import javax.servlet.http.HttpSession;

@Controller
@RestController
@RequestMapping("/api")
public class AuthController {

 private final CustomerService customerService;

 public AuthController(CustomerService customerService) {
     this.customerService = customerService;
 }

 @RequestMapping("/")
 public String showLoginPage() {
     return "login";
 }

 @PostMapping("/login")
 public String login(String loginId, String password, HttpSession session, Model model) {
     try {
         String token = customerService.authenticateUser(loginId, password);
         session.setAttribute("token", token);
         return "redirect:/customer";
     } catch (RuntimeException e) {
         model.addAttribute("errorMessage", "Authentication failed. Invalid credentials.");
         return "login";
     }
 }

 @RequestMapping("/logout")
 public String logout(HttpSession session) {
     session.removeAttribute("token");
     return "redirect:/";
 }
 @GetMapping("/hello")
 public String hello() {
     return "Hello, World!";
 }
}
