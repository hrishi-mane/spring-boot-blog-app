package com.example.blogapp.security;

public enum ApplicationUserPermissions {
    //This is a feature 2 branch comment.
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
