package com.security.springbootsec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Autowired
    private DataSource dataSource;

    //  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

   /* @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("abcd").password(encoder().encode("abcd")).roles("ADMIN").build(),
                User.withUsername("user").password(encoder().encode("user")).roles("USER").build());
    }*/
    @Bean
    protected PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    /*@Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/admin/**")
                .hasAnyRole("ADMIN").anyRequest().authenticated();
        http.httpBasic();
        http.userDetailsService(inMemoryUserDetailsManager());
        return http.build();///
    }*/

    private DigestAuthenticationEntryPoint digestAuthenticationEntryPoint(){
        DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
        entryPoint.setRealmName("admin-digest-realm-2");
        entryPoint.setKey("my-digest-key-5");
        return entryPoint;

    }
    private DigestAuthenticationFilter digestAuthenticationFilter(){

        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setUserDetailsService(jdbcUserDetails());
        filter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint());
        return filter;
    }

    @Bean
    protected UserDetailsService jdbcUserDetails() {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {

        http.antMatcher("/**").addFilter(digestAuthenticationFilter())
                .exceptionHandling()
                .authenticationEntryPoint(digestAuthenticationEntryPoint()).and()
                .authorizeRequests().antMatchers("/**")
                .hasRole("ADMIN");
        return http.build();
}

}
