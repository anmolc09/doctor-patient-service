package com.learning.client;

import com.learning.shared.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "notification-sender", url = "${notification-sender}")
public interface EmailClient {

    @PostMapping("/send")
    void sendEmails(@RequestBody EmailRequest emailRequest);
}
