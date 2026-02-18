package tfg.cervecera.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SaleDTO {

    private Long id;

    @NotNull
    private Long companyId;
    private String companyName;

    @NotNull
    private Long beerId;
    private String beerName;

    @NotNull
    private Long factoryId;
    private String factoryName;

    @NotNull
    @Positive
    private BigDecimal quantityL;

    @NotNull
    @Positive
    private BigDecimal totalPrice;

    private LocalDateTime soldAt;

    public SaleDTO() {}

    public SaleDTO(Long id, Long companyId, String companyName, Long beerId, 
                  String beerName, Long factoryId, String factoryName, 
                  BigDecimal quantityL, BigDecimal totalPrice, LocalDateTime soldAt) {
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
        this.beerId = beerId;
        this.beerName = beerName;
        this.factoryId = factoryId;
        this.factoryName = factoryName;
        this.quantityL = quantityL;
        this.totalPrice = totalPrice;
        this.soldAt = soldAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public LocalDateTime getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(LocalDateTime soldAt) {
        this.soldAt = soldAt;
    }
}