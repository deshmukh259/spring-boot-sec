package com.security.springbootsec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

  //  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("abcd").password(encoder().encode("abcd")).roles("ADMIN").build(),
                User.withUsername("user").password(encoder().encode("user")).roles("USER").build());
    }
    @Bean
    protected BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/admin/**")
                .hasAnyRole("ADMIN").anyRequest().authenticated();
        http.httpBasic();
        http.userDetailsService(inMemoryUserDetailsManager());
        return http.build();
    }
}
