package tfg.cervecera.aplication.beer;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import tfg.cervecera.dto.beer.BeerDTO;
import tfg.cervecera.dto.beer.BeerRegisterDTO;
import tfg.cervecera.exceptions.InvalidDataException;
import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Factory;
import tfg.cervecera.model.repositorys.BeerRepository;
import tfg.cervecera.model.repositorys.FactoryRepository;

@Service
public class BeerService {
    
    private final BeerRepository beerRepository;
    private final FactoryRepository factoryRepository;

    public BeerService(BeerRepository beerRepository, FactoryRepository factoryRepository) {
        this.beerRepository = beerRepository;
        this.factoryRepository = factoryRepository;
    }

    public BeerDTO registerBeer(BeerRegisterDTO dto) {
        validateBeerRegisterDTO(dto);
        
        Factory factory = factoryRepository.findById(dto.getFactoryId())
                .orElseThrow(() -> new EntityNotFoundException("Factory no encontrada"));

        Beer beer = new Beer();
        beer.setName(dto.getName());
        beer.setStyle(dto.getStyle());
        beer.setAlcohol(dto.getAlcohol());
        beer.setPricePerL(dto.getPricePerL());
        beer.setFactory(factory);

        Beer saved = beerRepository.save(beer);
        return mapToDTO(saved);
    }

    private void validateBeerRegisterDTO(BeerRegisterDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidDataException("El nombre de la cerveza no puede estar vacío");
        }
        if (dto.getStyle() == null || dto.getStyle().isEmpty()) {
            throw new InvalidDataException("El estilo de la cerveza no puede estar vacío");
        }
        if (dto.getAlcohol() <= 0) {
            throw new InvalidDataException("El contenido de alcohol debe ser mayor que 0");
        }
        if (dto.getPricePerL().compareTo(java.math.BigDecimal.ZERO) <= 0) {
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

    public void deleteBeer(Long id) {
        Beer beer = beerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cerveza no encontrada"));
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