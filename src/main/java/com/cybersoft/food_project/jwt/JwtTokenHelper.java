package com.cybersoft.food_project.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenHelper {//tạo token

    private long expireDate = 8*60*60*1000; //nên để ở config, ví dụ file yml
    private final String strKey = "xJHDonkgbMOgIGNodeG7l2kgYuG6o28gbeG6rXQgxJHhu6cgMjU2IGJpdA=="; //chuỗi 64
    public String generateToken(String data){//muốn lưu trữ gì thì chuyền vào đây
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + expireDate);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        return Jwts.builder()
                .setSubject(data) //lưu dữ liệu vào token dưới dạng string
                .setIssuedAt(now) //thời gian tạo token
                .setExpiration(dateExpired)//thời gian hết hạn
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();//trả token đã mã hóa


    }

    public String decodeToken(String token){

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getSubject();



    }

    public boolean validateToken(String token){ //copy 1 phần từ hàm decode, chỉ để kiểm tra có lấy được token từ request

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        boolean isSuccess = false;
        try{ Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token);
            isSuccess = true;

        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return isSuccess;
    }



}
