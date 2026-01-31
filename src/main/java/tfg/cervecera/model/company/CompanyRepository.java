package tfg.cervecera.model.company;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	void Guardar(Company company);

    Optional<Company> findByEmail(String email);

    boolean existsByEmail(String email);
}