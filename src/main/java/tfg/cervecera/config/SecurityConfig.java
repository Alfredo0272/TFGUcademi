package tfg.cervecera.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/api/companies/register", "/api/companies/login").permitAll()
            	    .anyRequest().authenticated()
            	);
        return http.build();
    }
    static {
        System.out.println(">>> SECURITY CONFIG LOADED <<<");
    }
    @Bean public PasswordEncoder passwordEncoder() { 
    	return new BCryptPasswordEncoder(); 
    	} 
   }