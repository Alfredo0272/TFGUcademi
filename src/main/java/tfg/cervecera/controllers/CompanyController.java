package tfg.cervecera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import tfg.cervecera.aplication.company.CompanyLoginService;
import tfg.cervecera.aplication.company.CompanyService;
import tfg.cervecera.dto.company.CompanyLoginDTO;
import tfg.cervecera.dto.company.CompanyLoginResponseDTO;
import tfg.cervecera.dto.company.CompanyRegisterDTO;
import tfg.cervecera.exceptions.InvalidCredentialsException;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
    private final CompanyService registerService;
    private final CompanyLoginService loginService;

    public CompanyController(CompanyService registerService,
                             CompanyLoginService loginService) {
        this.registerService = registerService;
        this.loginService = loginService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<CompanyRegisterDTO> register(
            @Valid @RequestBody CompanyRegisterDTO dto) {

        registerService.registerCompany(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    } 
   
    @PostMapping("/login")
    public ResponseEntity<CompanyLoginResponseDTO> login(
            @Valid @RequestBody CompanyLoginDTO dto) {

        CompanyLoginResponseDTO response =
                loginService.login(dto.getEmail(), dto.getPassword());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<CompanyLoginResponseDTO> loginWithToken(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidCredentialsException();
        }

        String token = authHeader.substring(7);

        CompanyLoginResponseDTO response = loginService.loginWithToken(token);

        return ResponseEntity.ok(response);
    }
}