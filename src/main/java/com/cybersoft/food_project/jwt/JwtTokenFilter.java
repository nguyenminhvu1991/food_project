package com.cybersoft.food_project.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //cắt header và lấy token
        String token = getTokenFromHeader(request);
        System.out.println("Token " + token);

        if(token != null){
            //kiểm tra token có phải do hệ thống của mình sinh ra
            if(jwtTokenHelper.validateToken(token)){
                //Token hợp lệ//ko cần else
                //Tạo chứng thưc từ bài login
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("","", new ArrayList<>());
                //Authentication auth = authenticationManager.authenticate(authRequest);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationToken);
            }

        }
        filterChain.doFilter(request, response);

    }
    private String getTokenFromHeader(HttpServletRequest request){
        //lấy giá trị token ở header có key là Authorization
        String strToken = request.getHeader("Authorization");
        if(StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")){
            //Xử lý khi lấy token thành công
            String finalToken = strToken.substring(7);
            return finalToken;

        } else {
            return null;
        }
    }

}
