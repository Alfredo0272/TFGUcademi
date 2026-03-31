package tfg.cervecera.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tfg.cervecera.aplication.stock.StockService;
import tfg.cervecera.dto.stock.StockDTO;
import tfg.cervecera.dto.stock.StockRegisterDTO;
import tfg.cervecera.model.Stock;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
	
	private final StockService stockService;

	public StockController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> registerStock(
			@Valid @RequestBody StockRegisterDTO dto) {

	   stockService.createStock(dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Stock registrado correctamente");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getStockById(@PathVariable Long id) {
	    return ResponseEntity.ok(stockService.getStockById(id));
	}
	
	@GetMapping("/beer/{id}")
	public ResponseEntity<?> findStockByBeerId(@PathVariable Long id){
	    return ResponseEntity.ok(stockService.findStocksByBeerId(id));
	}
	
	@PutMapping("/{id}/production")
	public ResponseEntity<StockDTO> addProduction(
	        @PathVariable Long id,
	        @RequestBody StockDTO dto) {

	    Stock updatedStock = stockService.addProduction(id, dto.getProductionVolumeL());

	    StockDTO response = new StockDTO();
	    response.setId(updatedStock.getId());
	    response.setBeerId(updatedStock.getBeer().getId());
	    response.setFactoryId(updatedStock.getFactory().getId());
	    response.setProductionCostL(updatedStock.getProductionCostL());
	    response.setProductionVolumeL(updatedStock.getProductionVolumeL());
	    response.setAvailableL(updatedStock.getAvailableL());
	    response.setUpdatedAt(updatedStock.getUpdatedAt());

	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
}
