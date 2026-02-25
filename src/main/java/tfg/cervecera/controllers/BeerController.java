package tfg.cervecera.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tfg.cervecera.aplication.beer.BeerService;
import tfg.cervecera.dto.beer.BeerDTO;
import tfg.cervecera.dto.beer.BeerRegisterDTO;

@RestController
@RequestMapping("/api/beers")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> registerBeer(
            @Valid @RequestBody BeerRegisterDTO dto) {

       beerService.createBeer(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Cerveza registrada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<BeerDTO>> getAllBeers() {
        return ResponseEntity.ok(beerService.findAllByCompany());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable Long id) {
        return ResponseEntity.ok(beerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
        return ResponseEntity.ok("Cerveza eliminada correctamente");
    }
}
