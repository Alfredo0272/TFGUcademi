package tfg.cervecera.model.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfg.cervecera.model.Stock;


@Repository
public interface StockRepository extends JpaRepository <Stock, Long>{
		
	Optional<Stock> findById(Long saleId);
}
