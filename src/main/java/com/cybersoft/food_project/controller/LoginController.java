package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.payload.request.SignInRequest;
import com.cybersoft.food_project.payload.response.SignInResponse;
import com.cybersoft.food_project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin//cho phep domain khac truy cap vao API de tranh error Cross policy
@RequestMapping("/signin")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }

    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request){
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setStatus(HttpStatus.OK.value());//chay smooth va chay toi day
        signInResponse.setDesc(""); //ko can thiet tra ra gia tri rong khi log in thanh cong
        signInResponse.setSuccess(loginService.checkLogin(request.getUsername(), request.getPassword()));
        signInResponse.setData("");

//        return new  ResponseEntity<> ("", HttpStatus.OK);
        return new  ResponseEntity<> (signInResponse, HttpStatus.OK);

    }


}
