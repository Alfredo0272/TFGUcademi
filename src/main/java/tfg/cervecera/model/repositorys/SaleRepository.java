package tfg.cervecera.model.repositorys;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tfg.cervecera.dto.sale.RevenueDTO;
import tfg.cervecera.model.Sale;


@Repository
public interface SaleRepository extends JpaRepository <Sale, Long>{
			
	List<Sale> findByFactoryId(Long factoryId);
	
	List<Sale> findByCompanyId(Long companyId);
	
	@Query("""
		    SELECT new tfg.cervecera.dto.sale.RevenueDTO(
		        s.factory.id,
		        s.factory.name,
		        COALESCE(SUM(s.totalPrice), 0)
		    )
		    FROM Sale s
		    WHERE s.company.id = :companyId
		    GROUP BY s.factory.id, s.factory.name
		""")
		List<RevenueDTO> getRevenueByFactory(Long companyId);
	
	@Query("""
		    SELECT COALESCE(SUM(s.totalPrice), 0)
		    FROM Sale s
		    WHERE s.company.id = :companyId
		""")
		BigDecimal getTotalRevenueByCompany(Long companyId);
	
	@Query("""
		    SELECT new tfg.cervecera.dto.sale.RevenueDTO(
		        s.beer.id,
		        s.beer.name,
		        COALESCE(SUM(s.totalPrice), 0)
		    )
		    FROM Sale s
		    WHERE s.company.id = :companyId
		    GROUP BY s.beer.id, s.beer.name
		""")
		List<RevenueDTO> getRevenueByBeer(@Param("companyId") Long companyId);
		
	@Query("""
			SELECT COALESCE(SUM(s.totalPrice), 0)
			FROM Sale s
			WHERE s.company.id = :companyId
			AND s.soldAt BETWEEN :start AND :end
			""")
			BigDecimal getRevenueBetweenDates(
			        Long companyId,
			        LocalDateTime start,
			        LocalDateTime end
			);
		
	@Query("""
		    SELECT new tfg.cervecera.dto.sale.RevenueDTO(
		        YEAR(s.soldAt),
		        MONTH(s.soldAt),
		        COALESCE(SUM(s.totalPrice), 0)
		    )
		    FROM Sale s
		    WHERE s.company.id = :companyId
		    GROUP BY YEAR(s.soldAt),
		             MONTH(s.soldAt)
		    ORDER BY YEAR(s.soldAt),
		             MONTH(s.soldAt)
		""")
		List<RevenueDTO> getMonthlyRevenue(@Param("companyId") Long companyId);
	
		@Query("""
			    SELECT COALESCE(SUM(
			        s.totalPrice - (st.productionCostL * s.quantityL)
			    ), 0)
			    FROM Sale s
			    JOIN Stock st 
			        ON st.beer.id = s.beer.id
			        AND st.factory.id = s.factory.id
			    WHERE s.company.id = :companyId
			""")
			BigDecimal getTotalProfitByCompany(Long companyId);
		
		@Query("""
			    SELECT new tfg.cervecera.dto.sale.RevenueDTO(
			        s.beer.id,
			        s.beer.name,
			        COALESCE(SUM(
			            s.totalPrice - (st.productionCostL * s.quantityL)
			        ), 0)
			    )
			    FROM Sale s
			    JOIN Stock st 
			        ON st.beer.id = s.beer.id
			        AND st.factory.id = s.factory.id
			    WHERE s.company.id = :companyId
			    GROUP BY s.beer.id, s.beer.name
			""")
			List<RevenueDTO> getProfitByBeer(Long companyId);
		
		@Query("""
			    SELECT new tfg.cervecera.dto.sale.RevenueDTO(
			        s.factory.id,
			        s.factory.name,
			        COALESCE(SUM(
			            s.totalPrice - (st.productionCostL * s.quantityL)
			        ), 0)
			    )
			    FROM Sale s
			    JOIN Stock st 
			        ON st.beer.id = s.beer.id
			        AND st.factory.id = s.factory.id
			    WHERE s.company.id = :companyId
			    GROUP BY s.factory.id, s.factory.name
			""")
			List<RevenueDTO> getProfitByFactory(Long companyId);
		
		@Query("""
			    SELECT new tfg.cervecera.dto.sale.RevenueDTO(
			        s.beer.id,
			        s.beer.name,
			        COALESCE(SUM(
			            s.totalPrice - (st.productionCostL * s.quantityL)
			        ), 0)
			    )
			    FROM Sale s
			    JOIN Stock st
			        ON st.beer.id = s.beer.id
			        AND st.factory.id = s.factory.id
			    WHERE s.company.id = :companyId
			    GROUP BY s.beer.id, s.beer.name
			    ORDER BY SUM(
			        s.totalPrice - (st.productionCostL * s.quantityL)
			    ) DESC
			""")
			List<RevenueDTO> getTopProfitableBeers(Long companyId, Pageable pageable);
		
		@Query("""
			    SELECT new tfg.cervecera.dto.sale.RevenueDTO(
			        YEAR(s.soldAt),
			        MONTH(s.soldAt),
			        COALESCE(SUM(
			            s.totalPrice - (st.productionCostL * s.quantityL)
			        ), 0)
			    )
			    FROM Sale s
			    JOIN Stock st
			        ON st.beer.id = s.beer.id
			        AND st.factory.id = s.factory.id
			    WHERE s.factory.id = :factoryId
			    GROUP BY YEAR(s.soldAt),
			             MONTH(s.soldAt)
			    ORDER BY YEAR(s.soldAt),
			             MONTH(s.soldAt)
			""")
			List<RevenueDTO> getMonthlyProfitFactory(@Param("factoryId") Long factoryId);
}
