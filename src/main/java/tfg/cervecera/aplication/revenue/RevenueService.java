package tfg.cervecera.aplication.revenue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tfg.cervecera.config.SecurityUtils;
import tfg.cervecera.dto.sale.RevenueDTO;
import tfg.cervecera.model.repositorys.SaleRepository;

@Service
public class RevenueService {

    private final SaleRepository saleRepository;
    
    public RevenueService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

    private Long getCurrentCompanyId() {
        return SecurityUtils.getCurrentCompanyId();
    }

    public BigDecimal getTotalRevenue() {
        return saleRepository.getTotalRevenueByCompany(getCurrentCompanyId());
    }

    public List<RevenueDTO> getRevenueByBeer() {
        return saleRepository.getRevenueByBeer(getCurrentCompanyId());
    }

    public List<RevenueDTO> getRevenueByFactory() {
        return saleRepository.getRevenueByFactory(getCurrentCompanyId());
    }

    public BigDecimal getRevenueBetweenDates(
            LocalDate start,
            LocalDate end) {

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        BigDecimal result = saleRepository.getRevenueBetweenDates(
                getCurrentCompanyId(),
                startDateTime,
                endDateTime
        );

        return result != null ? result : BigDecimal.ZERO;
    }

    public List<RevenueDTO> getMonthlyRevenue() {
        return saleRepository.getMonthlyRevenue(getCurrentCompanyId());
        
    }
    
    public List<RevenueDTO> getMonthlyProfitFactory(Long factoryId) {
        return saleRepository.getMonthlyProfitFactory(factoryId);
    }
    
    public BigDecimal getTotalProfit() {
        return saleRepository.getTotalProfitByCompany(getCurrentCompanyId());
    }

    public List<RevenueDTO> getProfitByBeer() {
        return saleRepository.getProfitByBeer(getCurrentCompanyId());
    }

    public List<RevenueDTO> getProfitByFactory() {
        return saleRepository.getProfitByFactory(getCurrentCompanyId());
    }
    
    public List<RevenueDTO> getTop5ProfitableBeers() {

        Pageable topFive = PageRequest.of(0, 5);

        return saleRepository.getTopProfitableBeers(
                getCurrentCompanyId(),
                topFive
        );
    }
}