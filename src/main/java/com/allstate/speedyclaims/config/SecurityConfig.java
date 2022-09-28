package com.allstate.speedyclaims.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //AUTHENTICATION
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    //AUTHORISATION
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/user")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/claim/**")
                .hasAnyRole("CUSTOMER", "STAFF")
                .antMatchers(HttpMethod.POST, "/api/claim/**")
                .hasAnyRole("CUSTOMER", "STAFF")
                .antMatchers(HttpMethod.PUT, "/api/claim/**")
                .hasAnyRole("STAFF")
                .antMatchers(HttpMethod.GET,"/api/claim/findAll")
                .hasAnyRole("STAFF")
                .antMatchers(HttpMethod.POST, "/api/login")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/login")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/customer")
                .hasAnyRole("STAFF")
                .antMatchers(HttpMethod.POST)
                .hasRole("STAFF")
                .antMatchers(HttpMethod.PUT)
                .hasRole("STAFF")
                .and().csrf().disable()
                .httpBasic();
    }

}