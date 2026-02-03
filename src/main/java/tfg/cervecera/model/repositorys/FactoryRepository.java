package tfg.cervecera.model.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfg.cervecera.model.Factory;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {

	Optional<Factory> findById(Long companyId);

	boolean existsByName(String name);	
	

}
