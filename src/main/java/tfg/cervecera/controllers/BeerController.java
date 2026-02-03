package tfg.cervecera.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<BeerRegisterDTO> registerBeer(
            @Valid @RequestBody BeerRegisterDTO dto) {

        beerService.registerBeer(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<BeerDTO>> getAllBeers() {
		List<BeerDTO> beers = beerService.findAll();
		return ResponseEntity.ok(beers);
	}
}
