package com.cybersoft.food_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
//khoi tao danh sach user cung va danh sach nay se duoc luu o Ram
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user1 = User.withUsername("cybersoft")
                .password(passwordEncoder().encode("123"))
                .roles("USERS").build();

        UserDetails user2 = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);

    }
    //kiểu mã hóa dữ liệu
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//quy định các rule về bảo mật và quyền truy cập
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signin").authenticated()
                //antMathcher định nghĩa link cần xác thực
                //=> authenticated: phải chứng thực (signin)
                //=> permitAll: cho phép truy cập full quyền vào link chỉ định
                .antMatchers("/signin/test").permitAll()//tạo thêm cho 1 link khác
                .anyRequest().authenticated();// và tất cả các request đều phải authenticated

        return http.build();
    }

}
