package tfg.cervecera.aplication.company;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tfg.cervecera.exceptions.InvalidCredentialsException;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.company.CompanyRepository;

@Service
public class CompanyLoginService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyLoginService(CompanyRepository companyRepository,
                               PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Company login(String email, String rawPassword) {

        Company company = companyRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("Invalid email or password")
            );

        if (!passwordEncoder.matches(rawPassword, company.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        return company;
    }
}