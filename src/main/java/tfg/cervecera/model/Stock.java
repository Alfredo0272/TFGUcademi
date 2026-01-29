package tfg.cervecera.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "stocks",
    uniqueConstraints = @UniqueConstraint(columnNames = {"factory_id", "beer_id"})
)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factory_id", nullable = false)
    private Factory factory;

    @ManyToOne
    @JoinColumn(name = "beer_id", nullable = false)
    private Beer beer;

    @Column(nullable = false)
    private Double productionCostL;

    @Column(nullable = false)
    private Double productionVolumeL;

    @Column(nullable = false)
    private Double availableL;

    private LocalDateTime updatedAt = LocalDateTime.now();

    public Stock() {}
}
