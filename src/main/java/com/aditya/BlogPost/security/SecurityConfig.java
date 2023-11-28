package com.aditya.BlogPost.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .authorizeHttpRequests(configure ->
                        configure

                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/login").anonymous()
                                .anyRequest().authenticated()
                )
                .formLogin(form->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/processAuthentication")
                                .permitAll()
                );

        return http.build();
    }

}
