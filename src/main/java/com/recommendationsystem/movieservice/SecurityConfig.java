package com.recommendationsystem.movieservice;

import com.recommendationsystem.movieservice.Service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.recommendationsystem.movieservice")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/css/**", "/signup", "/saveuser").permitAll() // Enable css when logged out
                .and()
                .authorizeRequests()
                .antMatchers("/admin", "/h2_console/**").hasRole("ADMIN").anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/movies")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and();
                http.csrf().disable();
                http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(new BCryptPasswordEncoder());
    }

}