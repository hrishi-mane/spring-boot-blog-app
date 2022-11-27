package com.example.blogapp.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "blogapp")
@PropertySource("classpath:message.properties")
@Data
public class BlogMessageConfig {
    private String creationIssue;
    private String successMessage;

    private String invalidBlogIdMessage;

    private String deletionSuccessMessage;

    private String incorrectObjectTypeMessage;

}
