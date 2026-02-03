package tfg.cervecera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tfg.cervecera.aplication.factory.FactoryServices;
import tfg.cervecera.dto.factory.FactoryDTO;

@RestController
@RequestMapping("/api/factories")
public class FactoryController {

    private final FactoryServices factoryService;

    public FactoryController(FactoryServices factoryService) {
        this.factoryService = factoryService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createFactory(
            @Valid @RequestBody FactoryDTO dto) {

        factoryService.createFactory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}