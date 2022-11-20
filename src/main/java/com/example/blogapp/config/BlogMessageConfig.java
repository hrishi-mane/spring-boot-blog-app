package com.example.blogapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "blogapp")
@PropertySource("classpath:message.properties")
public class BlogMessageConfig {
    private String creationIssue;
    private String successMessage;

    private String invalidBlogIdMessage;

    private String deletionSuccessMessage;

    public BlogMessageConfig() {
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

    public String getInvalidBlogIdMessage() {
        return invalidBlogIdMessage;
    }

    public void setInvalidBlogIdMessage(String invalidBlogIdMessage) {
        this.invalidBlogIdMessage = invalidBlogIdMessage;
    }

    public String getDeletionSuccessMessage() {
        return deletionSuccessMessage;
    }

    public void setDeletionSuccessMessage(String deletionSuccessMessage) {
        this.deletionSuccessMessage = deletionSuccessMessage;
    }
}
