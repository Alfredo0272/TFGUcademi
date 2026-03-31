package tfg.cervecera.model.repositorys;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tfg.cervecera.model.Sale;


@Repository
public interface SaleRepository extends JpaRepository <Sale, Long>{
			
	List<Sale> findByFactoryId(Long factoryId);
	
	@Query("""
		    SELECT s
		    FROM Sale s
		    JOIN FETCH s.beer
		    JOIN FETCH s.factory
		    JOIN FETCH s.company
		    WHERE s.company.id = :companyId
		""")
		List<Sale> findByCompanyId(Long companyId);
	
		
	@Modifying
	@Query("DELETE FROM Sale s WHERE s.beer.id = :beerId")
	void deleteByBeerId(@Param("beerId") Long beerId);
	
		boolean existsByBeerId(Long beerId);
		
}
