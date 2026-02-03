package tfg.cervecera.dto.factory;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Company;

public class FactoryDTO {
	
	private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "La ubicación es obligatoria")
    private String location;
    @NotNull(message = "La capacidad de producción es obligatoria")
    @Positive(message = "La capacidad debe ser mayor que 0")
    private Long capacity;
    private Company company;
    private List<Beer> beers;
    
    public FactoryDTO(Long id, String name, String location, Long copacity, Company company, List<Beer> beers) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.capacity = copacity;
		this.company = company;
		this.beers = beers;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Beer> getBeers() {
		return beers;
	}

	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}
    
    

}
