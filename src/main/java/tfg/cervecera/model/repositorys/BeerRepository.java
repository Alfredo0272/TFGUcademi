package tfg.cervecera.model.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfg.cervecera.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
	
	List<Beer> findByCompanyId(Long companyId);

}

