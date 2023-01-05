package com.cybersoft.food_project.security;

import com.cybersoft.food_project.entity.UserEntity;
import com.cybersoft.food_project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component //đưa lên Bean
public class CustomAuthentProvider implements AuthenticationProvider {

    @Autowired
    LoginService loginService;

//    @Autowired
////            @Qualifier("B")//lấy bean theo tên chỉ định muốn lấy nếu có nhiều bean giống class
//    PasswordEncoder passwordEncoder;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();//tạo mới để xài thay vì dùng Autowired

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //xử lý logic code đăng nhập thành công/ thất bại
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        //nhận username và pass , sau đó có thể dùng 2 info trên để query database

        //query database:
//        boolean isSuccess = loginService.checkLogin(userName, password);
//        System.out.println("Username: " + userName + " Password: " + password);
        //System.out.println("kiểm tra: " + isSuccess);
//        if(isSuccess){
//            return new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>()); //ArrayList: danh sách roles rỗng
//        } else {
//            return null;
//        }

        UserEntity userEntity = loginService.checkLogin(userName);
        //boolean isMatchPassword = passwordEncoder.matches(password, userEntity.getPassword());

        System.out.println("Username: " + userName + " Password: " + password);
        //System.out.println("kiểm tra: " + isMatchPassword);
        //System.out.println("kiểm tra encoded pass: " + userEntity.getPassword());
        if(userEntity != null ){


            boolean isMatchPassword = passwordEncoder.matches(password, new BCryptPasswordEncoder().encode(userEntity.getPassword()));
            //https://l.facebook.com/l.php?u=https%3A%2F%2Fstackoverflow.com%2Fquestions%2F60166583%2Fspring-security-encoded-password-does-not-look-like-bcrypt%3Ffbclid%3DIwAR3-LS8kUZPNPxcMont0jwxmFNoYNTJdkwzLyPE_GN5zwZ79BRXf4fG0gSA&h=AT0CjC1PgXlVMMQedLWIgYMXJAyIUzO1kae3W_NiuiqgR3OKOpA6Hr67x5ZoJi86jFwqkQmX5FeZRIJ5hCWsNmvSSFFPPZPW1O3m34UHgUVLdRuAxf_-RAt9CcclhMNplodrJQ
            if(isMatchPassword){
                return new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>()); //ArrayList: danh sách roles rỗng
            } else{
                return null;
            }

        } else {
            return null; //làm vầy để loại lỗi null object => error  500 khi nhập sai email
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);//so sánh
    }
}
