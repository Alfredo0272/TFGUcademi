package tfg.cervecera.aplication.company;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tfg.cervecera.dto.company.CompanyRegisterDTO;
import tfg.cervecera.exceptions.EmailAlreadyExistsException;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.repositorys.CompanyRepository;


@Service
public class CompanyRegisterService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CompanyRegisterService(CompanyRepository companyRepository,
                                  PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerCompany(CompanyRegisterDTO dto) {
    	
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
}