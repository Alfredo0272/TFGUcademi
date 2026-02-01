package tfg.cervecera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import tfg.cervecera.aplication.company.CompanyLoginService;
import tfg.cervecera.aplication.company.CompanyRegisterService;
import tfg.cervecera.dto.company.CompanyLoginDTO;
import tfg.cervecera.dto.company.CompanyRegisterDTO;
import tfg.cervecera.model.Company;


@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
    private final CompanyRegisterService registerService;
    private final CompanyLoginService loginService;

    public CompanyController(CompanyRegisterService registerService,
                             CompanyLoginService loginService) {
        this.registerService = registerService;
        this.loginService = loginService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody CompanyRegisterDTO dto) {

        Company company = new Company();
        company.setName(dto.getName());
        company.setEmail(dto.getEmail().toLowerCase());
        company.setPasswordHash(dto.getPassword());
        company.setCountry(dto.getCountry());
        company.setFoundedYear(dto.getFoundedYear());

        registerService.registerCompany(company);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody CompanyLoginDTO dto) {
        loginService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok("Login correcto");
    }
}
    
    

