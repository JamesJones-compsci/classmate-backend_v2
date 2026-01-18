package ca.gbc.comp3095.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        // For Docker, make sure you set JWT_SECRET in your environment or .env file
        String secret = System.getenv("JWT_SECRET");
        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET environment variable not set!");
        }

        byte[] keyBytes = secret.getBytes();
        SecretKeySpec key = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}
