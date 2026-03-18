package tfg.cervecera.aplication.stock;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import tfg.cervecera.dto.stock.StockDTO;
import tfg.cervecera.dto.stock.StockRegisterDTO;
import tfg.cervecera.model.Stock;
import tfg.cervecera.model.repositorys.BeerRepository;
import tfg.cervecera.model.repositorys.FactoryRepository;
import tfg.cervecera.model.repositorys.StockRepository;
import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Factory;


@Service
public class StockService {

    private final StockRepository stockRepository;
    private final BeerRepository beerRepository;
    private final FactoryRepository factoryRepository;

    public StockService(StockRepository stockRepository,
                        BeerRepository beerRepository,
                        FactoryRepository factoryRepository) {
        this.stockRepository = stockRepository;
        this.beerRepository = beerRepository;
        this.factoryRepository = factoryRepository;
    }
    
    private Stock findStockEntityById(Long id) {
        return stockRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("El stock no existe"));
    }

    public void createStock(StockRegisterDTO dto) {

        Beer beer = beerRepository.findById(dto.getBeerId())
                .orElseThrow(() -> new IllegalArgumentException("La cerveza no existe"));

        Factory factory = factoryRepository.findById(dto.getFactoryId())
                .orElseThrow(() -> new IllegalArgumentException("La fábrica no existe"));
        
        if (!beer.getCompany().getId().equals(factory.getCompany().getId())) {
            throw new IllegalArgumentException("La cerveza no pertenece a esta empresa");
        }
        
        if (stockRepository.findByBeerIdAndFactoryId(
                dto.getBeerId(), dto.getFactoryId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe stock para esta cerveza y fábrica");
        }

        Stock stock = new Stock();
        stock.setBeer(beer);
        stock.setFactory(factory);
        stock.setProductionCostL(dto.getProductionCostL());
        stock.setProductionVolumeL(dto.getProductionVolumeL());
        stock.setAvailableL(dto.getProductionVolumeL());

        stockRepository.save(stock);
    }
    
    public StockDTO getStockById(Long id) {
        return mapToDTO(findStockEntityById(id));
    }
    
    public void deleteStockById(Long id) {

        Stock stock = findStockEntityById(id);

        if (stock.getAvailableL().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("No se puede eliminar un stock con volumen disponible");
        }

        stockRepository.delete(stock);
    }
    
    public List<StockDTO> findStocksByBeerId(Long beerId) {
        List<Stock> stocks = stockRepository.findByBeerId(beerId);

        if (stocks.isEmpty()) {
            throw new IllegalArgumentException("No hay stock para esta cerveza");
        }

        return stocks.stream()
                .map(this::mapToDTO)
                .toList();
    }
    
    public List<StockDTO> findAll(){
    	return stockRepository.findAll()
    			.stream()
    			.map(this::mapToDTO)
                .toList();
    	
    }
    
    public void addProduction(Long stockId, BigDecimal additionalVolume) {
    	
        if (additionalVolume == null || additionalVolume.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El volumen adicional debe ser mayor que 0");
        }

        Stock stock = findStockEntityById(stockId);

        stock.setProductionVolumeL(
            stock.getProductionVolumeL().add(additionalVolume)
        );

        stock.setAvailableL(
            stock.getAvailableL().add(additionalVolume)
        );

        stockRepository.save(stock);
    }
    
    private StockDTO mapToDTO(Stock stock) {

        StockDTO dto = new StockDTO();

        dto.setId(stock.getId());

        dto.setFactoryId(stock.getFactory().getId());
        dto.setFactoryName(stock.getFactory().getName());

        dto.setBeerId(stock.getBeer().getId());
        dto.setBeerName(stock.getBeer().getName());

        dto.setProductionCostL(stock.getProductionCostL());
        dto.setProductionVolumeL(stock.getProductionVolumeL());
        dto.setAvailableL(stock.getAvailableL());

        dto.setUpdatedAt(stock.getUpdatedAt());

        return dto;
    }
    
    
}