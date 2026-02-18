package tfg.cervecera.dto.beer;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BeerDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String style;

    @NotNull
    @Positive
    private Double alcohol;

    @NotNull
    @Positive
    private BigDecimal pricePerL;

    private Long factoryId;
    private String factoryName;

    public BeerDTO() {}

    public BeerDTO(Long id, String name, String style, Double alcohol, 
                   BigDecimal pricePerL, Long factoryId, String factoryName) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.alcohol = alcohol;
        this.pricePerL = pricePerL;
        this.factoryId = factoryId;
        this.factoryName = factoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Double alcohol) {
        this.alcohol = alcohol;
    }

    public BigDecimal getPricePerL() {
        return pricePerL;
    }

    public void setPricePerL(BigDecimal pricePerL) {
        this.pricePerL = pricePerL;
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
}