package tfg.cervecera.aplication.company;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tfg.cervecera.dto.company.CompanyLoginResponseDTO;
import tfg.cervecera.exceptions.InvalidCredentialsException;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.repositorys.CompanyRepository;

@Service
public class CompanyLoginService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyLoginService(CompanyRepository companyRepository,
                               PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CompanyLoginResponseDTO login(String email, String rawPassword) {

        Company company = companyRepository.findByEmail(email.toLowerCase())
            .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(rawPassword, company.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return new CompanyLoginResponseDTO(
                company.getId(),
                company.getName(),
                company.getEmail()
        );
    }
}
