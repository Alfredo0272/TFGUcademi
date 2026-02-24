package tfg.cervecera.dto.sale;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SaleRegisterDTO {

    @NotNull
    private Long beerId;

    @NotNull
    private Long factoryId;

    @NotNull
    @Positive
    private BigDecimal quantityL;

    @NotNull
    @Positive
    private BigDecimal unitPrice;

    public SaleRegisterDTO() {}

    public SaleRegisterDTO(Long beerId, Long factoryId,
                           BigDecimal quantityL,
                           BigDecimal unitPrice) {
        this.beerId = beerId;
        this.factoryId = factoryId;
        this.quantityL = quantityL;
        this.unitPrice = unitPrice;
    }

    public Long getBeerId() { return beerId; }
    public Long getFactoryId() { return factoryId; }
    public BigDecimal getQuantityL() { return quantityL; }
    public BigDecimal getUnitPrice() { return unitPrice; }

    public void setBeerId(Long beerId) { this.beerId = beerId; }
    public void setFactoryId(Long factoryId) { this.factoryId = factoryId; }
    public void setQuantityL(BigDecimal quantityL) { this.quantityL = quantityL; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}