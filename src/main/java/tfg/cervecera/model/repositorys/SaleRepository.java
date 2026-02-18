package tfg.cervecera.model.repositorys;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfg.cervecera.model.Sale;


@Repository
public interface SaleRepository extends JpaRepository <Sale, Long>{
		
	Optional<Sale> findById(Long saleId);
}
