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
    private BigDecimal totalPrice;

    public SaleRegisterDTO() {}

    public SaleRegisterDTO(Long beerId, Long factoryId, BigDecimal quantityL, 
                          BigDecimal totalPrice) {
        this.beerId = beerId;
        this.factoryId = factoryId;
        this.quantityL = quantityL;
        this.totalPrice = totalPrice;
    }

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public BigDecimal getQuantityL() {
        return quantityL;
    }

    public void setQuantityL(BigDecimal quantityL) {
        this.quantityL = quantityL;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
