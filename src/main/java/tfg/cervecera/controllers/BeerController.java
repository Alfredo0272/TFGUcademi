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
import tfg.cervecera.model.Beer;

@RestController
@RequestMapping("/api/beers")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @PostMapping("/new")
    public ResponseEntity<BeerDTO> registerBeer(
            @Valid @RequestBody BeerRegisterDTO dto) {

        Beer beer = beerService.createBeer(dto);

        BeerDTO response = new BeerDTO(
            beer.getId(),
            beer.getName(),
            beer.getStyle(),
            beer.getAlcohol(),
            beer.getPricePerL(),
            beer.getFactory().getId(),
            beer.getFactory().getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BeerDTO>> getAllBeers() {
        return ResponseEntity.ok(beerService.findAllByCompany());
    }
    
    @GetMapping("/factory/{factoryId}")
    public ResponseEntity<List<BeerDTO>> getBeersByFactory(
            @PathVariable Long factoryId) {

        return ResponseEntity.ok(
            beerService.findAllByFactory(factoryId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable Long id) {
        return ResponseEntity.ok(beerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        beerService.deleteBeer(id);
        return ResponseEntity.noContent().build();
    }
}
