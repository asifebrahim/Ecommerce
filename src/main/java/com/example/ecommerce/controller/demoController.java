package com.example.ecommerce.controller;


import com.example.ecommerce.entity.Person;
import com.example.ecommerce.product_entity.product_details;
import com.example.ecommerce.service.ecommservice;
import com.example.ecommerce.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class demoController {

    //service Initialize
    private ecommservice ecomms;
    private employeeService emp;
    @Autowired
    public demoController(ecommservice ecomms,employeeService emp){
        this.emp=emp;
        this.ecomms=ecomms;
    }




    //endpoints begin


    @GetMapping("/login")
    public String logging(){
        return "login";
    }

    @GetMapping("/register")
    public String Registration(Model theModel){
        Person thePerson=new Person();
        theModel.addAttribute("employee",thePerson);
        return "registration";
    }

    @PostMapping("/register-form-submission")
    public String savingtheemployee(@ModelAttribute("employee") Person thePerson){
        emp.save(thePerson);
        return "redirect:/";
    }
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/about")
    public String about(){
        return "About";
    }
    @GetMapping("/contact")
    public String Contact(){
        return "contactUs";
    }
    @GetMapping("/more")
    public String more(){
        return "More";
    }
    @GetMapping("/admin/home")
    public String adminHome(){
        return "landing-pages/admin-home";
    }
    @GetMapping("/manager/home")
    public String managerHome(){
        return "landing-pages/manager-home";
    }
    @GetMapping("/employee/home")
    public String empHome(){
        return "landing-pages/emp-home";
    }

    @GetMapping("/error")
    public String errorHandler(){
        return "error";
    }
    @GetMapping("/productAdd")
    public String productForm(Model theModel){
        product_details temp=new product_details();
        theModel.addAttribute("product-object",temp);
        return "product-form";
    }
    @PostMapping("/Submission")
    public String finalProduct(@ModelAttribute("product-object") product_details temp_product){
        ecomms.save(temp_product);
        return "redirect:/manager/home";
    }

    @GetMapping("/profileInfo")
    public String profile(){
        return "profileDetails";
    }

    @GetMapping("/forgotPass")
    public String forgotPassword(){
        return "reset-pass";
    }
    @GetMapping("/Seller")
    public String sell(){
        return "seller-home";
    }
    @GetMapping("/existing-seller")
    public String ex_sell(){
        return "seller-login";
    }
    @GetMapping("new-seller")
    public String new_sell(){
        return "new-seller-login";
    }

}
