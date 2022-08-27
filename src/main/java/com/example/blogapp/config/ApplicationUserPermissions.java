package com.example.blogapp.config;

public enum ApplicationUserPermissions {
    //This is something that I am doing, and it can be done to make code better.
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
