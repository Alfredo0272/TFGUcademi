package tfg.cervecera.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "beers", indexes = {
	    @Index(name = "idx_beer_factory", columnList = "factory_id"),
	    @Index(name = "idx_beer_style", columnList = "style"),
	    @Index(name = "idx_beer_company", columnList = "company_id")
	})
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String style;

    @Column(nullable = false)
    private Double alcohol;

    @Column(name = "price_per_l", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerL;

    @ManyToOne(optional = false)   
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(optional = false)
    @JoinColumn(name = "factory_id", nullable = false)
    private Factory factory;
    
    
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

    public BigDecimal getPricePerL() {
        return pricePerL;
    }

    public void setPricePerL(BigDecimal pricePerL) {
        this.pricePerL = pricePerL;
    }
    
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}