package ru.gb.springbootdemoapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.springbootdemoapp.controller.exceptions.NoPasswordException;
import ru.gb.springbootdemoapp.service.RegistrationService;

@Controller
public class AuthController {

  private final RegistrationService registrationService;

  public AuthController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }

  @GetMapping("/register")
  public String registerForm() {
    return "register";
  }

  @PostMapping("/register")
  public String register(@RequestParam String username, @RequestParam String password, @RequestParam String repeat, Model model) {
    try {
      String token = registrationService.sighUp(username, password, repeat);
      model.addAttribute("token", token);
      return "register-confirm";
    }catch (NoPasswordException e){
      model.addAttribute("noPasswordException", e);
      return "register";
    }catch (IllegalStateException e){
      model.addAttribute("illegalStateException", e);
      return "register";
    }
  }

  @GetMapping("/register/confirm")
  public String registerConfirm(@RequestParam String token) {
    int result = registrationService.confirmRegistration(token);
    if (result == 1) {
      return "register-complete";
    } else if (result == 0){
      return "register-repeat";
    }
    return "register-error";
  }
}
