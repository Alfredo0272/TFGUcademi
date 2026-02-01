package tfg.cervecera.dto.company;
import jakarta.validation.constraints.*;

public class CompanyRegisterDTO {
	
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "El país es obligatorio")
    private String country;

    @NotNull(message = "El año de fundación es obligatorio")
    @Min(value = 1800, message = "El año de fundación no es válido")
    @Max(value = 2100, message = "El año de fundación no es válido")
    private Integer foundedYear;

	    public CompanyRegisterDTO() {}

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }
	    
	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getCountry() {
	        return country;
	    }
	    
	    public void setCountry(String country) {
	        this.country = country;
	    }

	    public Integer getFoundedYear() {
	        return foundedYear;
	    }

	    public void setFoundedYear(Integer foundedYear) {
	        this.foundedYear = foundedYear;
	    }

}
