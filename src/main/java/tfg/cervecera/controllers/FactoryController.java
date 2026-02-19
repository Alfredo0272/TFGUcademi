package tfg.cervecera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import tfg.cervecera.aplication.factory.FactoryService;
import tfg.cervecera.dto.factory.FactoryDTO;
import tfg.cervecera.dto.factory.FactoryRegisterDTO;

import java.util.List;

@RestController
@RequestMapping("/api/factories")
public class FactoryController {

    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @PostMapping("/register")
    public ResponseEntity<FactoryDTO> createFactory(
            @Valid @RequestBody FactoryRegisterDTO dto) {
        FactoryDTO created = factoryService.createFactory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<FactoryDTO>> getAllFactories() {
        List<FactoryDTO> factories = factoryService.findAll();
        return ResponseEntity.ok(factories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactoryDTO> getFactoryById(@PathVariable Long id) {
        FactoryDTO factory = factoryService.findById(id);
        return ResponseEntity.ok(factory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactoryDTO> updateFactory(
            @PathVariable Long id,
            @Valid @RequestBody FactoryRegisterDTO dto) {
        FactoryDTO updated = factoryService.updateFactory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactory(@PathVariable Long id) {
        factoryService.deleteFactory(id);
        return ResponseEntity.noContent().build();
    }
}