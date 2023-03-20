package com.market.config;

import com.market.service.impl.user.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .mvcMatchers("/admin/*", "/admin/**")
                .hasAnyAuthority("ADMIN")
                //Only Admin role users can access the /admin requests
                .mvcMatchers("/job/**", "/job/*")
                .hasAnyAuthority("MANAGER", "ADMIN")
                //Only Admin and Manager role users can hire or fire employees, update their salary
                //and reject job applications
                .mvcMatchers("/job/apply")
                .hasAnyAuthority("CUSTOMER")
                //Only Customer role users can make job applications
                .mvcMatchers("/products/*", "/products/**",
                        "/order/order-management","/order/order-details/*",
                        "/order/send/*","/order/complete/*")
                .hasAnyAuthority("EMPLOYEE", "MANAGER", "ADMIN")
                //Only !Customer role users can do product related requests
                .mvcMatchers("/users/*").anonymous()
                //Register and Login requests require an unauthenticated user
                .mvcMatchers("/products/available", "/orders/", "/orders/***",
                        "/item/details/*", "/delete/item/*", "/item/save", "/update/*").authenticated()
                //Requests for authenticated users only
                .anyRequest().permitAll().and()
                .formLogin().loginPage("/users/login").failureForwardUrl("/users/login").permitAll()
                .successHandler(successHandler()).permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/users/login")
                .permitAll()
                .logoutSuccessHandler(new LogoutSuccessHandler() {

                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication)
                            throws IOException, ServletException {


                        response.sendRedirect("/users/login");
                    }
                });
    }
}
