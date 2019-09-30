package com.kodilla.kodilla.diplomaBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableScheduling
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN", "USER")
                .and()
                .withUser("user").password(encoder().encode("userPass")).roles("USER");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/v1/rent/book").hasRole("USER")
                .antMatchers("/v1/rent/cancel").hasRole("USER")
                .antMatchers("/v1/rent/history").hasRole("USER")
                .antMatchers("/v1/cars").hasRole("USER")
                .antMatchers("/v1/categories").hasRole("USER")
                .antMatchers("/v1/weather/**").hasRole("USER")
                .antMatchers("/v1/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                //.antMatchers("/v1/user/login").permitAll() ---> TO BE DONE IN THE FUTURE
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
