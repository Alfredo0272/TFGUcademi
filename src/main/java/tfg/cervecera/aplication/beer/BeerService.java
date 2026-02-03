package tfg.cervecera.aplication.beer;

import java.util.List;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tfg.cervecera.dto.beer.BeerDTO;
import tfg.cervecera.dto.beer.BeerRegisterDTO;

import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Factory;
import tfg.cervecera.model.repositorys.BeerRepository;
import tfg.cervecera.model.repositorys.FactoryRepository;

@Service
public class BeerService {

    private final BeerRepository beerRepository;
    private final FactoryRepository factoryRepository;

    public BeerService(BeerRepository beerRepository,
    		FactoryRepository factoryRepository) {
        this.beerRepository = beerRepository;
        this.factoryRepository = factoryRepository;
    }

    public BeerDTO registerBeer(BeerRegisterDTO dto) {

    	Factory factory = factoryRepository.findById(dto.getFactoryId())
    			.orElseThrow(() -> new EntityNotFoundException("Factory no encontrada"));

        Beer beer = new Beer();
        beer.setName(dto.getName());
        beer.setStyle(dto.getStyle());
        beer.setAlcohol(dto.getAlcohol());
        beer.setPricePerL(dto.getPricePerL());
        beer.setFactory(factory);

        Beer saved = beerRepository.save(beer);

        return mapToResponse(saved);
    }

    public List<BeerDTO> findAll() {
        return beerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private BeerDTO mapToResponse(Beer beer) {
    	return new BeerDTO(
    		    beer.getId(),
    		    beer.getName(),
    		    beer.getStyle(),
    		    beer.getAlcohol(),
    		    beer.getPricePerL(),
    		    beer.getCompany()!=null ? beer.getCompany().getId() : null,
    		    beer.getFactory().getId()
    		);
    }
}