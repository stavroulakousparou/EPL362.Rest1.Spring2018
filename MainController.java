package org.o7planning.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping("/login")
  public String index() {
    return "login.html";
  }
  
  @RequestMapping("/home_lawyer")
  public String home_lawyer() {
    return "home_lawyer.html";
  }
  
  @RequestMapping("/home_secretary")
  public String home_secretary() {
    return "home_secretary.html";  
  }
  
  @RequestMapping("/new_cons")
  public String new_consultation() {
    return "newConsultation.html";  
  }
  
  
  

}