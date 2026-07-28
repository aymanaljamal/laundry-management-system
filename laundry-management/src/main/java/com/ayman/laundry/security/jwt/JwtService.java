package com.ayman.laundry.security.jwt;


import com.ayman.laundry.security.service.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtService {


    private final JwtProperties jwtProperties;



    private SecretKey getSigningKey(){

        byte[] keyBytes =
                Decoders.BASE64.decode(
                        jwtProperties.getSecret()
                );


        return Keys.hmacShaKeyFor(keyBytes);

    }



    public String generateToken(
            CustomUserDetails userDetails
    ){


        Date now = new Date();


        Date expiry =
                new Date(
                        now.getTime()
                        +
                        jwtProperties.getExpiration()
                );


        return Jwts.builder()

                .subject(
                        userDetails.getUsername()
                )

                .claim(
                        "id",
                        userDetails.getUserId()
                )

                .claim(
                        "role",
                        userDetails.getRole()
                )

                .claim(
                        "fullName",
                        userDetails.getFullName()
                )

                .claim(
                        "email",
                        userDetails.getEmail()
                )

                .issuedAt(now)

                .expiration(expiry)

                .signWith(
                        getSigningKey()
                )

                .compact();

    }



    public String extractUsername(
            String token
    ){

        return extractClaim(
                token,
                Claims::getSubject
        );

    }



    public <T> T extractClaim(
            String token,
            Function<Claims,T> resolver
    ){

        Claims claims =
                extractAllClaims(token);


        return resolver.apply(claims);

    }



    private Claims extractAllClaims(
            String token
    ){

        return Jwts.parser()

                .verifyWith(
                        getSigningKey()
                )

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }



    public boolean isTokenValid(
            String token,
            CustomUserDetails userDetails
    ){

        String username =
                extractUsername(token);


        return username.equals(
                userDetails.getUsername()
        )
        &&
        !isExpired(token);

    }



    private boolean isExpired(
            String token
    ){

        return extractClaim(
                token,
                Claims::getExpiration
        )
        .before(
                new Date()
        );

    }


}