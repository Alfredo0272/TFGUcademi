package tfg.cervecera.dto.company;
import jakarta.validation.constraints.*;

public class CompanyLoginDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
