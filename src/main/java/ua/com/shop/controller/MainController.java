package ua.com.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class MainController {

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/mainMenu")
    public String mainMenu() {
        return "mainMenu";
    }
}
