package com.learning.shared;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmailRequest {

    private String recipient;
    private String message;
   private String subject;
}
