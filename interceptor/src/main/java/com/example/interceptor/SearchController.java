package com.example.interceptor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping("/search")
    public String searchFeature() {
        return "You have access to the Beta Search Feature!";
    }
}

