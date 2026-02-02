package tfg.cervecera.dto.company;

import java.time.LocalDateTime;

public class CompanyDTO {

    private Long id;
    private String name;
    private String email;
    private String country;
    private Integer foundedYear;
    private LocalDateTime createdAt;

    public CompanyDTO(Long id, String name, String email,
                              String country, Integer foundedYear,
                              LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.foundedYear = foundedYear;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setFoundedYear(Integer foundedYear) {
		this.foundedYear = foundedYear;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
