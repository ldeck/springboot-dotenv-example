package com.example.testenv;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
public class HelloController {

    private final Environment environment;

    public HelloController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/hello/{key}")
    public String hello(@NotBlank @PathVariable String key) {
        return environment.getProperty("hello." + key, "Default Prop");
    }
}
