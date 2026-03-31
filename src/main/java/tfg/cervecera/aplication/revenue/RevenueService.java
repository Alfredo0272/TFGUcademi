package tfg.cervecera.aplication.revenue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tfg.cervecera.config.SecurityUtils;
import tfg.cervecera.dto.sale.RevenueDTO;
import tfg.cervecera.model.repositorys.RevenueRepository;

@Service
public class RevenueService {

	private final RevenueRepository revenueRepository;
    
    public RevenueService( RevenueRepository revenueRepository) {
		this.revenueRepository = revenueRepository;
	}

    private Long getCurrentCompanyId() {
        return SecurityUtils.getCurrentCompanyId();
    }

    public BigDecimal getTotalRevenue() {
        return revenueRepository.getTotalRevenueByCompany(getCurrentCompanyId());
    }

    public List<RevenueDTO> getRevenueByBeer() {
        return revenueRepository.getRevenueByBeer(getCurrentCompanyId());
    }

    public List<RevenueDTO> getRevenueByFactory() {
        return revenueRepository.getRevenueByFactory(getCurrentCompanyId());
    }

    public BigDecimal getRevenueBetweenDates(
            LocalDate start,
            LocalDate end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        return revenueRepository.getRevenueBetweenDates(
                getCurrentCompanyId(),
                startDateTime,
                endDateTime
        );
    }

    public List<RevenueDTO> getMonthlyRevenue() {
        return revenueRepository.getMonthlyRevenue(getCurrentCompanyId());
        
    }
    
    public List<RevenueDTO> getMonthlyProfitFactory(Long factoryId) {
        return revenueRepository.getMonthlyProfitFactory(factoryId);
    }
    
    public BigDecimal getTotalProfit() {
        return revenueRepository.getTotalProfitByCompany(getCurrentCompanyId());
    }

    public List<RevenueDTO> getProfitByBeer() {
        return revenueRepository.getProfitByBeer(getCurrentCompanyId());
    }

    public List<RevenueDTO> getProfitByFactory() {
        return revenueRepository.getProfitByFactory(getCurrentCompanyId());
    }
    
    public List<RevenueDTO> getTop5ProfitableBeers() {

        Pageable topFive = PageRequest.of(0, 5);

        return revenueRepository.getTopProfitableBeers(
                getCurrentCompanyId(),
                topFive
        );
    }
}