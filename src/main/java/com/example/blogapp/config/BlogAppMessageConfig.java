package com.example.blogapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "blogapp")
@PropertySource("classpath:message.properties")
public class BlogAppMessageConfig {
    private String creationIssue;
    private String successMessage;

    public BlogAppMessageConfig() {
        super();
    }

    public String getCreationIssue() {
        return creationIssue;
    }

    public void setCreationIssue(String creationIssue) {
        this.creationIssue = creationIssue;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
