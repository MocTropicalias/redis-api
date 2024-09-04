package com.example.redisapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SwaggerRedirectController {

    @GetMapping("/docs")
    public RedirectView redirectToSwaggerUi() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
