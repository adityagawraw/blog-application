package com.aditya.BlogPost.security;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user1 = User.builder()
                .username("jhon")
                .password("{noop}test123")
                .roles("author")
                .build();
        UserDetails user2 = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("author")
                .build();
        UserDetails user3 = User.builder()
                .username("bob")
                .password("{noop}test123")
                .roles("author,admin")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                        configurer
                                .requestMatchers("/","/post","/nextPage","/previousPage", "/css/**").
                                permitAll()
                                .requestMatchers("/editPost", "/updatePost","/deletePost","/newPost","/publish")
                                .hasAnyRole("author","admin")
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .usernameParameter("email")
                                .loginProcessingUrl("/processAuthentication")
                                .permitAll()
                )
                .exceptionHandling(configurer->
                        configurer
                                .accessDeniedPage("/access-denied"));

        return http.build();
    }

}
