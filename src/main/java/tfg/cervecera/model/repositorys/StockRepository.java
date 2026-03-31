package tfg.cervecera.model.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfg.cervecera.model.Stock;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	Optional<Stock> findByBeerIdAndFactoryId(Long beerId, Long factoryId);
	
	Optional<Stock> findByFactoryIdAndBeerId(Long factoryId, Long beerId);

    List<Stock> findByFactoryId(Long factoryId);

    List<Stock> findByBeerId(Long beerId);
    
    void deleteByBeerId(Long beerId);
}
