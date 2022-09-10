package com.example.blogapp.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.blogapp.security.ApplicationUserPermissions.*;

public enum ApplicationUserRoles {
    USER(Sets.newHashSet(BLOG_READ.name())),
    ADMIN(Sets.newHashSet(BLOG_WRITE.name(),
            BLOG_READ.name(),
            BLOG_UPDATE.name(),
            BLOG_DELETE.name()));

    private final Set<String> permissions;

    ApplicationUserRoles(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities(){
        return getPermissions().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
