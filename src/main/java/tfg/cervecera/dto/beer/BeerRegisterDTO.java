package tfg.cervecera.dto.beer;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BeerRegisterDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El estilo es obligatorio")
    private String style;

    @NotNull(message = "El alcohol es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El alcohol no puede ser negativo")
    @DecimalMax(value = "100.0", inclusive = true, message = "El alcohol no puede superar el 100%")
    private Double alcohol;
    
    @NotNull(message = "El precio por litro es obligatorio")
    @Positive
    private BigDecimal pricePerL;
    
    
	@NotNull(message = "La factoria es obligatoria")
    private Long factoryId;

    public BeerRegisterDTO() {}
    
    public BeerRegisterDTO(String name, String style, Double alcohol, 
            BigDecimal pricePerL, Long factoryId) {
    		this.name = name;
    		this.style = style;
    		this.alcohol = alcohol;
    		this.pricePerL = pricePerL;
    		this.factoryId = factoryId;
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
}