package tfg.cervecera.aplication.company;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tfg.cervecera.config.JwtService;
import tfg.cervecera.dto.company.CompanyLoginResponseDTO;
import tfg.cervecera.exceptions.InvalidCredentialsException;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.repositorys.CompanyRepository;

@Service
public class CompanyLoginService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public CompanyLoginService(CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public CompanyLoginResponseDTO login(String email, String rawPassword) {

        Company company = companyRepository.findByEmail(email.toLowerCase())
            .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(rawPassword, company.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        
        String token = jwtService.generateToken(company);

        return new CompanyLoginResponseDTO(
                company.getId(),
                company.getName(),
                company.getEmail(),
                token
        );
    }
}
