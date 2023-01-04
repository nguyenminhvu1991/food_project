package com.cybersoft.food_project.security;

import com.cybersoft.food_project.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
    //Khởi tạo danh sách user cứng và lưu ở Ram
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user1 = User.withUsername("cybersoft")
//                .password(passwordEncoder().encode("123"))
//                .roles("USERS").build();
//
//        UserDetails user2 = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user1, user2);
//
//    }


    @Autowired
    CustomAuthentProvider customAuthentProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthentProvider);

        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //kiểu mã hóa dữ liệu: Bcrypt tốt vì đã mã hóa thì ko giải mã được ?

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }


    //Quy định các rule về bảo mật và quyền truy cập
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //https://www.baeldung.com/spring-security-login
        /*
        antMathcher: định nghĩa 1 link cần xác thực
        authenticated(): phải chứng thực (signin)
        permitAll(): cho phép truy cập full quyền vào link chỉ định
        anyRequest().authenticated();// và tất cả các request đều phải authenticated, có thể lỗi 403 forbidden (ko có quyền truy cập)
        antMatchers("/signin").hasRole.authenticated()
        antMatchers("/signin").hasAnyRole.authenticated(
        */

        http.csrf().disable()//tránh việc spam đăng nhập liên tục?
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()//phải luôn có
                .antMatchers("/signin").permitAll()
                .antMatchers("/signin/test").authenticated()
                .anyRequest().authenticated();

        //thêm filter trước một filter nào đó
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
