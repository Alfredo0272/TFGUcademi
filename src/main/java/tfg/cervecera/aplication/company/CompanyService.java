package tfg.cervecera.aplication.company;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tfg.cervecera.dto.company.CompanyDTO;
import tfg.cervecera.dto.company.CompanyRegisterDTO;
import tfg.cervecera.exceptions.EmailAlreadyExistsException;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.repositorys.CompanyRepository;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyService(CompanyRepository companyRepository,
                                  PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerCompany(CompanyRegisterDTO dto) throws EmailAlreadyExistsException {
    	
    	String email = dto.getEmail().toLowerCase();

    	if (companyRepository.existsByEmail(email)) {
    	     throw new EmailAlreadyExistsException("El email ya está registrado");
    	  }
    	    Company company = new Company();
    	    company.setName(dto.getName());
    	    company.setEmail(email);
    	    company.setPasswordHash(
    	            passwordEncoder.encode(dto.getPassword())
    	    );
    	    company.setCountry(dto.getCountry());
    	    company.setFoundedYear(dto.getFoundedYear());

    	    companyRepository.save(company);
    }
    
    public CompanyDTO findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow();
        return mapToDTO(company);
    }

    public CompanyDTO findByEmail(String email) {
        Company company = companyRepository.findByEmail(email)
                .orElseThrow();
        return mapToDTO(company);
    }

    private CompanyDTO mapToDTO(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getCountry(),
                company.getFoundedYear(),
                company.getCreatedAt()
        );
    }
}
    
    