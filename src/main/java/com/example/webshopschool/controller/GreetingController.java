package com.example.webshopschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam String name,
                           @RequestParam Long dateOfBirth,
                           @RequestParam String placeOfBirth,
                           Model model){
        model.addAttribute("name", name);
        model.addAttribute("dateOfBirth", dateOfBirth);
        model.addAttribute("placeOfBirth", placeOfBirth);
        return "greeting";
    }

    @GetMapping("/formGreeting")
    public String formGreeting() {
        System.out.println("in form Greeting");
        return "greetingstart";
    }


    @RequestMapping("/greetingThanks")
    public String greeting(@RequestParam String name,
                           @RequestParam String email, Model model) {
        System.out.println("Hey " + name + ", your email address is " + email);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "greetingthanks";
    }
}
