package tfg.cervecera.dto.beer;

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
    private Double pricePerL;
    
    private Long companyId;
    private Long factoryId;
    
    public BeerDTO(Long id, String name, String style,
                           Double alcohol, Double pricePerL,
                           Long companyId, Long factoryId) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.alcohol = alcohol;
        this.pricePerL = pricePerL;
        this.companyId = companyId;
        this.factoryId = factoryId;
    }
    
    public void setId(Long id) {
 		this.id = id;
 	}

 	public void setName(String name) {
 		this.name = name;
 	}

 	public void setStyle(String style) {
 		this.style = style;
 	}

 	public void setAlcohol(Double alcohol) {
 		this.alcohol = alcohol;
 	}

 	public void setPricePerL(Double pricePerL) {
 		this.pricePerL = pricePerL;
 	}

 	public void setCompanyId(Long companyId) {
 		this.companyId = companyId;
 	}
 	
 	public void setFactoryId(Long factoryId) {
 		this.factoryId = factoryId;
 	}


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public Double getAlcohol() {
        return alcohol;
    }

    public Double getPricePerL() {
        return pricePerL;
    }

    public Long getCompanyId() {
        return companyId;
    }
    
    public Long getFactoryId() {
        return factoryId;
    }
}
