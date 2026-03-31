package tfg.cervecera.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tfg.cervecera.aplication.revenue.RevenueService;
import tfg.cervecera.dto.sale.RevenueDTO;

@RestController
@RequestMapping("/api/revenue")
public class RevenueController {

    private final RevenueService revenueService;
    
    public RevenueController(RevenueService revenueService) {
		this.revenueService = revenueService;
	}

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalRevenue() {
        return ResponseEntity.ok(revenueService.getTotalRevenue());
    }

    @GetMapping("/by-beer")
    public ResponseEntity<List<RevenueDTO>> getRevenueByBeer() {
        return ResponseEntity.ok(revenueService.getRevenueByBeer());
    }

    @GetMapping("/by-factory")
    public List<RevenueDTO> getRevenueByFactory() {
        return revenueService.getRevenueByFactory();
    }

    @GetMapping("/monthly")
    public List<RevenueDTO> getMonthlyRevenue() {
        return revenueService.getMonthlyRevenue();
    }
    
    @GetMapping("/monthly/factory/{factoryId}")
    public ResponseEntity<List<RevenueDTO>> getMonthlyRevenueByFactory(
            @PathVariable Long factoryId) {

        if (factoryId == null) {
            throw new IllegalArgumentException("Factory id cannot be null");
        }

        return ResponseEntity.ok(
                revenueService.getMonthlyProfitFactory(factoryId)
        );
    }
    @GetMapping("/range")
    public BigDecimal getRevenueBetweenDates(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate start,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate end) {

        return revenueService.getRevenueBetweenDates(start, end);
    }
    
    @GetMapping("/profit/total")
    public BigDecimal getTotalProfit() {
        return revenueService.getTotalProfit();
    }

    @GetMapping("/profit/by-beer")
    public List<RevenueDTO> getProfitByBeer() {
        return revenueService.getProfitByBeer();
    }

    @GetMapping("/profit/by-factory")
    public List<RevenueDTO> getProfitByFactory() {
        return revenueService.getProfitByFactory();
    }
    
    @GetMapping("/profit/top5")
    public List<RevenueDTO> getTop5ProfitableBeers() {
        return revenueService.getTop5ProfitableBeers();
    }
}