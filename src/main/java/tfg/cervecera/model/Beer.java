package tfg.cervecera.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String style;
    private Double abv;

    @Column(nullable = false)
    private Double pricePerL;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "beer")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "beer")
    private List<Sale> sales;

    public Beer() {}
}
