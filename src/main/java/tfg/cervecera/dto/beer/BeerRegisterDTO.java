package tfg.cervecera.dto.beer;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser positivo")
    @Digits(integer = 3, fraction = 2, message = "El alcohol debe tener máximo 2 decimales")
    private Double pricePerL;
    

    public Double getPricePerL() {
		return pricePerL;
	}


	public void setPricePerL(Double pricePerL) {
		this.pricePerL = pricePerL;
	}

    private Long companyId;
    
	@NotNull(message = "La factoria es obligatoria")
    private Long factoryId;

    public BeerRegisterDTO() {}


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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }
}
    

