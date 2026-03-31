package tfg.cervecera.aplication.sale;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tfg.cervecera.config.SecurityUtils;
import tfg.cervecera.dto.sale.SaleDTO;
import tfg.cervecera.dto.sale.SaleRegisterDTO;
import tfg.cervecera.exceptions.InsufficientStockException;
import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Company;
import tfg.cervecera.model.Factory;
import tfg.cervecera.model.Sale;
import tfg.cervecera.model.Stock;
import tfg.cervecera.model.repositorys.BeerRepository;
import tfg.cervecera.model.repositorys.CompanyRepository;
import tfg.cervecera.model.repositorys.FactoryRepository;
import tfg.cervecera.model.repositorys.SaleRepository;
import tfg.cervecera.model.repositorys.StockRepository;



@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final StockRepository stockRepository;
    private final BeerRepository beerRepository;
    private final FactoryRepository factoryRepository;
    private final CompanyRepository companyRepository;

    public SaleService(
            SaleRepository saleRepository,
            StockRepository stockRepository,
            BeerRepository beerRepository,
            FactoryRepository factoryRepository,
            CompanyRepository companyRepository) {

        this.saleRepository = saleRepository;
        this.stockRepository = stockRepository;
        this.beerRepository = beerRepository;
        this.factoryRepository = factoryRepository;
        this.companyRepository = companyRepository;
    }

    public SaleDTO createSale(SaleRegisterDTO dto) {
    	
        Long companyId = SecurityUtils.getCurrentCompanyId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company no encontrada"));
        

        Beer beer = beerRepository.findById(dto.getBeerId())
                .orElseThrow(() -> new RuntimeException("Beer not found"));

        Factory factory = factoryRepository.findById(dto.getFactoryId())
                .orElseThrow(() -> new RuntimeException("Factory not found"));
        
        if (!factory.getCompany().getId().equals(companyId)) {
            throw new RuntimeException("Factory does not belong to your company");
        }
        if (!beer.getCompany().getId().equals(companyId)) {
            throw new RuntimeException("Beer does not belong to your company");
        }
        

        Stock stock = stockRepository
                .findByFactoryIdAndBeerId(factory.getId(), beer.getId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        if (dto.getQuantityL() == null) {
            throw new RuntimeException("Quantity cannot be null");
        }

        if (stock.getAvailableL().compareTo(dto.getQuantityL()) < 0) {
            throw new InsufficientStockException(
                "Stock insuficiente. Disponible: "
                + stock.getAvailableL()
                + "L"
            );
        }
        
		 if (dto.getUnitPrice() == null) {
			throw new RuntimeException("Unit price cannot be null");
        }
			 
        stock.setAvailableL(
                stock.getAvailableL().subtract(dto.getQuantityL())
        );

        Sale sale = new Sale();
        sale.setCompany(company);
        sale.setBeer(beer);
        sale.setFactory(factory);
        sale.setQuantityL(dto.getQuantityL());
        sale.setUnitPrice(dto.getUnitPrice());

        sale.setTotalPrice(
                dto.getUnitPrice().multiply(dto.getQuantityL())
        );

        Sale saved = saleRepository.save(sale);

        return mapToDTO(saved);
    }
    
    public List<SaleDTO> getSalesByCompany() {

        Long companyId = SecurityUtils.getCurrentCompanyId();

        return saleRepository.findByCompanyId(companyId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    public List<SaleDTO> getSalesByFactory(Long factoryId) {

        Long companyId = SecurityUtils.getCurrentCompanyId();

        Factory factory = factoryRepository.findById(factoryId)
                .orElseThrow(() -> new RuntimeException("Factory not found"));

        if (!factory.getCompany().getId().equals(companyId)) {
            throw new RuntimeException("Factory does not belong to your company");
        }

        return saleRepository.findByFactoryId(factoryId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    
    private SaleDTO mapToDTO(Sale sale) {

        SaleDTO dto = new SaleDTO();

        dto.setId(sale.getId());

        dto.setCompanyId(sale.getCompany().getId());
        dto.setCompanyName(sale.getCompany().getName());

        dto.setBeerId(sale.getBeer().getId());
        dto.setBeerName(sale.getBeer().getName());

        dto.setFactoryId(sale.getFactory().getId());
        dto.setFactoryName(sale.getFactory().getName());

        dto.setUnitPrice(sale.getUnitPrice());
        dto.setQuantityL(sale.getQuantityL());
        dto.setTotalPrice(sale.getTotalPrice());
        dto.setSoldAt(sale.getSoldAt());

        return dto;
    }
	}

    	
