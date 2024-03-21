package com.example.springsecurityolderversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/home").authenticated()
                .antMatchers("/all").permitAll()
                .and()
                .formLogin()
                .loginPage("/customlogin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/h2-console");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("gfg")
                .password(passwordEncoder().encode("gfg123"))
                .roles("admin");
    }

}
