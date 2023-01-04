package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.jwt.JwtTokenHelper;
import com.cybersoft.food_project.payload.request.SignInRequest;
import com.cybersoft.food_project.payload.response.SignInResponse;
import com.cybersoft.food_project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin//cho phep domain khac truy cap vao API de tranh error Cross policy
@RequestMapping("/signin")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @GetMapping("/test")
    public String testSpringSecurity(){
        return "Hello";
    }

    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request){
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        //tạo token sau khi đăng nhập
        String token = jwtTokenHelper.generateToken(request.getUsername());
        //giả mã token
        String decoDeToken = jwtTokenHelper.decodeToken(token);
        //khi nhập thành công, trả thêm refresh token (ko có thời gian expired)
        //tạo controller /refresh-token
        //kiểm tra refresh token có hợp lệ ko
        //nếu hợp lệ trả ra token mới

        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setStatus(HttpStatus.OK.value());//chay smooth va chay toi day
        //signInResponse.setDesc(""); //ko can thiet tra ra gia tri rong khi log in thanh cong
        signInResponse.setDesc(decoDeToken);
        //signInResponse.setSuccess(loginService.checkLogin(request.getUsername(), request.getPassword()));
        signInResponse.setSuccess(true);
        signInResponse.setData(token);

//        return new  ResponseEntity<> ("", HttpStatus.OK);
        return new  ResponseEntity<> (signInResponse, HttpStatus.OK);

    }


}
