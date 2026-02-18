package tfg.cervecera.dto.stock;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StockRegisterDTO {

    @NotNull
    private Long factoryId;

    @NotNull
    private Long beerId;

    @NotNull
    @Positive
    private BigDecimal productionCostL;

    @NotNull
    @Positive
    private BigDecimal productionVolumeL;

    @NotNull
    @Positive
    private BigDecimal availableL;

    public StockRegisterDTO() {}

    public StockRegisterDTO(Long factoryId, Long beerId, BigDecimal productionCostL, 
                           BigDecimal productionVolumeL, BigDecimal availableL) {
        this.factoryId = factoryId;
        this.beerId = beerId;
        this.productionCostL = productionCostL;
        this.productionVolumeL = productionVolumeL;
        this.availableL = availableL;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
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
}