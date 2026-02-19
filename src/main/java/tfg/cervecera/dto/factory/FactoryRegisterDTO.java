package tfg.cervecera.dto.factory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FactoryRegisterDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @Positive
    private Long capacity;

    public FactoryRegisterDTO() {}

    public FactoryRegisterDTO(String name, String location, Long capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
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

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}