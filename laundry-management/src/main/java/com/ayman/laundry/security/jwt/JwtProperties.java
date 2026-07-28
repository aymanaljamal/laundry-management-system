package com.ayman.laundry.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Secret Key
     */
    private String secret;

    /**
     * Expiration in milliseconds
     */
    private long expiration;

    /**
     * Token Prefix
     */
    private String tokenPrefix = "Bearer ";

    /**
     * Header Name
     */
    private String header = "Authorization";

}