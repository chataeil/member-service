package org.koreait.member.jwp.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.global.exceptions.UnAuthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginFilter extends GenericFilterBean {
    private final org.koreait.member.jwt.TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       try {
           tokenService.authenticate((HttpServletRequest) request);
       }catch (UnAuthorizedException e){
           HttpServletResponse res = (HttpServletResponse) response;
           res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
           e.printStackTrace();

       }
       chain.doFilter(request, response);
    }
}
