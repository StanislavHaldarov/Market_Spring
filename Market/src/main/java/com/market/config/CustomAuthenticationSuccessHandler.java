package com.market.config;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("CUSTOMER")) {
                response.sendRedirect("/index");
                return;
            } else if (authority.getAuthority().equals("EMPLOYEE")) {
                response.sendRedirect("/index");
                return;
            }
            else if (authority.getAuthority().equals("ADMIN")) {
                response.sendRedirect("/index");
                return;
            }
        }
    }
}
