package tfg.cervecera.dto.beer;

public class BeerDTO {

    private Long id;
    private String name;
    private String style;
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

	private Double alcohol;
    private Double pricePerL;
    private Long companyId;

    public BeerDTO(Long id, String name, String style,
                           Double alcohol, Double pricePerL,
                           Long companyId) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.alcohol = alcohol;
        this.pricePerL = pricePerL;
        this.companyId = companyId;
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
}
