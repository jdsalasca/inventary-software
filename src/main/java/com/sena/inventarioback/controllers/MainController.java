package com.sena.inventarioback.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class MainController {
    @GetMapping({"/", "/api", ""})
    public RedirectView redirectToSwaggerUI() {
        log.info("redirecting to swagger");
        return new RedirectView("/api/swagger-ui/index.html");
    }
}
