package tfg.cervecera.config;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import tfg.cervecera.model.Company;

@Service
public class JwtService {
	
	private static final String secretKey =  System.getenv("JWT_SECRET");
	
    @PostConstruct
    public void checkKey() {
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalStateException(
                "JWT_SECRET debe tener al menos 32 caracteres"
            );
        }
    }

    public String generateToken(Company company) {
        return Jwts.builder()
                .setSubject(company.getEmail())
                .claim("companyId", company.getId())
                .setIssuedAt(new Date())
                .setExpiration(
                    new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4)
                )
                .signWith(
                    Keys.hmacShaKeyFor(secretKey.getBytes()),
                    SignatureAlgorithm.HS256
                )
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}