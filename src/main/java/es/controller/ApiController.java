package es.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    
    public ApiController() {
    }

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }
    
}
