package ru.nanaslav.notificationmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Notification is alive!";
    }

}
