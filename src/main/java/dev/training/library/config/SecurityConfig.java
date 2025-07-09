package dev.training.library.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Date;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //TODO: REMOVER DEPOIS DE TESTAR AS ROTAS
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable()) // necessário para exibir o H2 console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // permite o H2
                        .anyRequest().permitAll() // permite tudo temporariamente
                );

        return http.build();
    }

    public class JwtUtil {

        private static final String SECRET_KEY = "segredoDQLCB";
        private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 2;

        public static String generateToken(String email, Long id) {
            return Jwts.builder()
                    .setSubject(email)
                    .claim("id", id)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        }
    }

}
