package tfg.cervecera.aplication.company;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tfg.cervecera.exceptions.EmailAlreadyExistsException;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.company.CompanyRepository;


@Service
public class CompanyRegisterService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyRegisterService(CompanyRepository companyRepository,
                                  PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Company registerCompany(Company company) {

        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new EmailAlreadyExistsException("El email ya está registrado");
        }

        company.setPasswordHash(
            passwordEncoder.encode(company.getPasswordHash())
        );

        return companyRepository.save(company);
    }
}