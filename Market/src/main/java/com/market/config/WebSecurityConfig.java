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
                .mvcMatchers("/admin/user-management", "/admin/update","/admin/delete/*")
                .hasAnyAuthority("ADMIN") //Само администраторът има достъп до всеки потребител
                .mvcMatchers("/job/hire/*", "/job/reject/*", "/job/update-salary/*","/job/fire/*" ,"/job/job-applications-management","/job/employee-management")
                .hasAnyAuthority("MANAGER","ADMIN")
                //Само мениджър или админ могат да назначават,отвхърлят кандидатури,
                //да променят заплати или да уволняват служители
                .mvcMatchers("/job/apply")
                .hasAnyAuthority("CUSTOMER") //Само клиентските акаунти могат да попълват кандидатури за работа
                .mvcMatchers("/products/add", "/products/all","/products/update","/products/update/*","/products/delete/*")
                .hasAnyAuthority("EMPLOYEE", "MANAGER","ADMIN")
                .mvcMatchers("/users/register","/users/login").anonymous()
                .anyRequest().permitAll().and()
                .formLogin().loginPage("/users/login").failureForwardUrl("/users/login").permitAll()
                .successHandler(successHandler()).permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/products/available")
                .permitAll()
                .logoutSuccessHandler(new LogoutSuccessHandler() {

                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication)
                            throws IOException, ServletException {


                        response.sendRedirect("/products/available");
                    }
                });
    }
}
