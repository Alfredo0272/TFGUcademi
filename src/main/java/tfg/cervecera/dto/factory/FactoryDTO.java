package tfg.cervecera.dto.factory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class FactoryDTO {
	
	private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "La ubicación es obligatoria")
    private String location;
    @NotNull(message = "La capacidad de producción es obligatoria")
    @Positive(message = "La capacidad debe ser mayor que 0")
    private Long capacity;

    private Long companyId;
    private String companyName;

    public FactoryDTO(Long id,
                      String name,
                      String location,
                      Long capacity,
                      Long companyId,
                      String companyName) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}