package controller;

//CustomerController.java

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.CustomerService;

import dto.Customer;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class CustomerController {

 private final CustomerService customerService;

 public CustomerController(CustomerService customerService) {
     this.customerService = customerService;
 }

 @RequestMapping("/customer")
 public String showCustomerList(Model model, HttpSession session) {
     String token = (String) session.getAttribute("token");
     if (token == null) {
         return "redirect:/";
     }

     List<Customer> customerList = customerService.getCustomerList1(token);
     model.addAttribute("customerList", customerList);
     return "customerList";
 }

 @GetMapping("/customer/{uuid}")
 public String showCustomerDetails(@PathVariable String uuid, Model model, HttpSession session) {
     String token = (String) session.getAttribute("token");
     if (token == null) {
         return "redirect:/";
     }

     
      Customer customer = customerService.getCustomerDetails(uuid, token);
      model.addAttribute("customer", customer);
    

     return "customerDetails";
 }

 @PostMapping("/add-customer")
 public String addCustomer(@RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam String street, @RequestParam String address, @RequestParam String city,
                           @RequestParam String state, @RequestParam String email, @RequestParam String phone,
                           HttpSession session) {
     String token = (String) session.getAttribute("token");
     if (token == null) {
         return "redirect:/";
     }

     Customer customer = new Customer();
     customer.setFirst_name(firstName);
     customer.setLast_name(lastName);
     customer.setStreet(street);
     customer.setAddress(address);
     customer.setCity(city);
     customer.setState(state);
     customer.setEmail(email);
     customer.setPhone(phone);

   
     return "redirect:/customer";
 }
}
