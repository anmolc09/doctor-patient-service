package com.learning.rest;

import com.learning.entity.EmailEntity;
import com.learning.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailSender emailSender;

    @PostMapping("/send")
    public void sendEmails(@RequestBody EmailEntity email){
        emailSender.sendEmails(email);
    }

}
