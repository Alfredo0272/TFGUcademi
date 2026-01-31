package tfg.cervecera.model.company;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import tfg.cervecera.model.Beer;
import tfg.cervecera.model.Factory;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String country;
    private Integer foundedYear;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "company")
    private List<Factory> factories;

    @OneToMany(mappedBy = "company")
    private List<Beer> beers;

    public Company() {
        this.createdAt = LocalDateTime.now();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Factory> getFactories() {
		return factories;
	}

	public void setFactories(List<Factory> factories) {
		this.factories = factories;
	}

	public List<Beer> getBeers() {
		return beers;
	}

	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}

}
