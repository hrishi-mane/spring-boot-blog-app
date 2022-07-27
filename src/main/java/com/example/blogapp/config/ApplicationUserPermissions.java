package com.example.blogapp.config;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public enum ApplicationUserPermissions {
    BLOG_READ("blog:read"),
    BLOG_WRITE("blog:write"),
    BLOG_UPDATE("blog:update"),
    BLOG_DELETE("blog:delete");
    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
