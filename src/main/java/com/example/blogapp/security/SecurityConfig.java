package com.example.blogapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.blogapp.security.ApplicationUserRoles.ADMIN;
import static com.example.blogapp.security.ApplicationUserRoles.USER;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoders;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoders) {
        this.passwordEncoders = passwordEncoders;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //authorizeRequests() anyRequest() must be authenticated() and we must use httpBasic() i.e. basic authentication.
        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/blogs/**").hasAuthority(BLOG_WRITE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        //Use of runtime-polymorphism a lot
        //Main observation is the obj being built is stored in var of different type and object returned and return type are different


        //User of builder pattern where we have a static builder method which returns us an inner class object
        //which has setter methods that return the object at the end which allows for such function call chaining
        //and the build() at the end constructs the parent class object by using these inner-class variables that we just set.
        //Var of type UserDetails is used because the object stored of type User implements UserDetails interface.
        UserDetails user = User.builder()
                .username("Hrishi")
                .password(passwordEncoders.encode("password"))
                .roles(USER.name())
//                .authorities(USER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("Administrator")
                .password(passwordEncoders.encode("admin@123"))
                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        //object of type InMemoryUserDetailsManager has return type of UserDetailService because the InMemoryUserDetailsManager implements
        //UserDetailService interface.
        return new InMemoryUserDetailsManager(
                admin,user
        );
    }
}
