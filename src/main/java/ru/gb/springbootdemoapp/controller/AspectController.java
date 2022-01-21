package ru.gb.springbootdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootdemoapp.service.AspectService;

@Controller
@RequestMapping("/statistic")
public class AspectController {

    private final AspectService aspectService;

    public AspectController(AspectService aspectService) {
        this.aspectService = aspectService;
    }

    @GetMapping
    public String getAspectTime(Model model){
        model.addAttribute("time", aspectService.getAspectTime());
        return "aspect";
    }
}
