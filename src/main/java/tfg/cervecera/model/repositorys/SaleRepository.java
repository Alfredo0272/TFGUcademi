package tfg.cervecera.model.repositorys;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tfg.cervecera.model.Sale;


@Repository
public interface SaleRepository extends JpaRepository <Sale, Long>{
		
	Optional<Sale> findById(Long saleId);
	
	List<Sale> findByFactoryId(Long factoryId);
	
	List<Sale> findByCompanyId(Long companyId);
	
	@Query("""
		    SELECT SUM(s.totalPrice)
		    FROM Sale s
		    WHERE s.factory.id = :factoryId
		""")
		BigDecimal getTotalRevenueByFactory(Long factoryId);
	
	
}
