package tfg.cervecera.aplication.beer;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import tfg.cervecera.config.SecurityUtils;
import tfg.cervecera.dto.beer.BeerDTO;
import tfg.cervecera.dto.beer.BeerRegisterDTO;
import tfg.cervecera.exceptions.InvalidDataException;
import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.Factory;
import tfg.cervecera.model.repositorys.BeerRepository;
import tfg.cervecera.model.repositorys.CompanyRepository;
import tfg.cervecera.model.repositorys.FactoryRepository;

@Service
public class BeerService {
    
	private final BeerRepository beerRepository;
	private final FactoryRepository factoryRepository;
	private final CompanyRepository companyRepository;

	public BeerService(BeerRepository beerRepository,
	                   FactoryRepository factoryRepository,
	                   CompanyRepository companyRepository) {
	    this.beerRepository = beerRepository;
	    this.factoryRepository = factoryRepository;
	    this.companyRepository = companyRepository;
	}

	public Beer createBeer(BeerRegisterDTO dto) {
	    validateBeerRegisterDTO(dto);

	    Long companyId = SecurityUtils.getCurrentCompanyId();

	    Company company = companyRepository.findById(companyId)
	            .orElseThrow(() -> new EntityNotFoundException("Company no encontrada"));

	    Factory factory = factoryRepository.findById(dto.getFactoryId())
	            .orElseThrow(() -> new EntityNotFoundException("Factory no encontrada"));

	    Beer beer = new Beer();
	    beer.setName(dto.getName());
	    beer.setStyle(dto.getStyle());
	    beer.setAlcohol(dto.getAlcohol());
	    beer.setPricePerL(dto.getPricePerL());
	    beer.setCompany(company);
	    beer.setFactory(factory);

	    return beerRepository.save(beer);
	}
	
    private void validateBeerRegisterDTO(BeerRegisterDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidDataException("El nombre de la cerveza no puede estar vacío");
        }
        if (dto.getStyle() == null || dto.getStyle().isEmpty()) {
            throw new InvalidDataException("El estilo de la cerveza no puede estar vacío");
        }
        if (dto.getAlcohol() == null || dto.getAlcohol() <= 0) {
            throw new InvalidDataException("El contenido de alcohol debe ser mayor que 0");
        }
        if (dto.getPricePerL() == null || dto.getPricePerL().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new InvalidDataException("El precio por litro debe ser mayor que 0");
        }
    }

    public List<BeerDTO> findAll() {
        return beerRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public BeerDTO findById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id no es válido");
        }
        Beer beer = beerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cerveza no encontrada"));
        return mapToDTO(beer);
    }
    
    public List<BeerDTO> findAllByFactory(Long factoryId) {

        if (factoryId == null || factoryId <= 0) {
            throw new IllegalArgumentException("FactoryId no válido");
        }

        Long companyId = SecurityUtils.getCurrentCompanyId();

        return beerRepository
                .findByFactoryIdAndCompanyId(factoryId, companyId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    public List<BeerDTO> findAllByCompany() {
        Long companyId = SecurityUtils.getCurrentCompanyId();
        return beerRepository.findByCompanyId(companyId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    public void deleteBeer(Long id) {

        Long companyId = SecurityUtils.getCurrentCompanyId();

        Beer beer = beerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cerveza no encontrada"));

        if (!beer.getCompany().getId().equals(companyId)) {
            throw new SecurityException("No tienes permiso para eliminar esta cerveza");
        }

        beerRepository.delete(beer);
    }

    private BeerDTO mapToDTO(Beer beer) {
        return new BeerDTO(
                beer.getId(),
                beer.getName(),
                beer.getStyle(),
                beer.getAlcohol(),
                beer.getPricePerL(),
                beer.getFactory().getId(),
                beer.getFactory().getName() 
        );
    }
}