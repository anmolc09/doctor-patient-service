package com.learning.config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix ="email-config" )
public class EmailConfig {

    private String sendTo;
    private String from;
    private String attachment;

}
