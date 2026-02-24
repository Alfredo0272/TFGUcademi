package tfg.cervecera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tfg.cervecera.aplication.sale.SaleService;
import tfg.cervecera.dto.sale.SaleDTO;
import tfg.cervecera.dto.sale.SaleRegisterDTO;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(
            @Valid @RequestBody SaleRegisterDTO dto) {

        SaleDTO created = saleService.createSale(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}