package tfg.cervecera.aplication.beer;

import java.util.List;

import org.springframework.stereotype.Service;

import tfg.cervecera.dto.beer.BeerDTO;
import tfg.cervecera.dto.beer.BeerRegisterDTO;

import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.repositorys.BeerRepository;
import tfg.cervecera.model.repositorys.CompanyRepository;

@Service
public class BeerService {

    private final BeerRepository beerRepository;
    private final CompanyRepository companyRepository;

    public BeerService(BeerRepository beerRepository,
                       CompanyRepository companyRepository) {
        this.beerRepository = beerRepository;
        this.companyRepository = companyRepository;
    }

    public BeerDTO registerBeer(BeerRegisterDTO dto) {

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() ->
                        new IllegalArgumentException("La empresa no existe")
                );

        Beer beer = new Beer();
        beer.setName(dto.getName());
        beer.setStyle(dto.getStyle());
        beer.setAlcohol(dto.getAlcohol());
        beer.setPricePerL(dto.getPricePerL());
        beer.setCompany(company);

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
                beer.getCompany().getId()
        );
    }
}