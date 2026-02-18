package tfg.cervecera.dto.stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StockDTO {

    private Long id;

    @NotNull
    private Long factoryId;
    private String factoryName;

    @NotNull
    private Long beerId;
    private String beerName;

    @NotNull
    @Positive
    private BigDecimal productionCostL;

    @NotNull
    @Positive
    private BigDecimal productionVolumeL;

    @NotNull
    @Positive
    private BigDecimal availableL;

    private LocalDateTime updatedAt;

    public StockDTO() {}

    public StockDTO(Long id, Long factoryId, String factoryName, Long beerId, 
                   String beerName, BigDecimal productionCostL, 
                   BigDecimal productionVolumeL, BigDecimal availableL, 
                   LocalDateTime updatedAt) {
        this.id = id;
        this.factoryId = factoryId;
        this.factoryName = factoryName;
        this.beerId = beerId;
        this.beerName = beerName;
        this.productionCostL = productionCostL;
        this.productionVolumeL = productionVolumeL;
        this.availableL = availableL;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public BigDecimal getProductionCostL() {
        return productionCostL;
    }

    public void setProductionCostL(BigDecimal productionCostL) {
        this.productionCostL = productionCostL;
    }

    public BigDecimal getProductionVolumeL() {
        return productionVolumeL;
    }

    public void setProductionVolumeL(BigDecimal productionVolumeL) {
        this.productionVolumeL = productionVolumeL;
    }

    public BigDecimal getAvailableL() {
        return availableL;
    }

    public void setAvailableL(BigDecimal availableL) {
        this.availableL = availableL;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}