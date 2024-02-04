package com.yamadatadashidai.chatapp.chatapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins =  "http://localhost:8080")
public class MessageController {

    @GetMapping("/api/message")
    public String getMessage() {
        return "Hello Yamada from spring boot";
    }
    
}
