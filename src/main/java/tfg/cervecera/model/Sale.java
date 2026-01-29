package tfg.cervecera.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "beer_id", nullable = false)
    private Beer beer;

    @Column(nullable = false)
    private Double quantityL;

    @Column(nullable = false)
    private Double totalPrice;

    private LocalDateTime soldAt = LocalDateTime.now();

    public Sale() {}
}
