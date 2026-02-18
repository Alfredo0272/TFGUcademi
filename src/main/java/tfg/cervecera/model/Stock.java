package tfg.cervecera.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "stocks",
    uniqueConstraints = @UniqueConstraint(columnNames = {"factory_id", "beer_id"}),
    indexes = {
        @Index(name = "idx_stock_factory", columnList = "factory_id"),
        @Index(name = "idx_stock_beer", columnList = "beer_id"),
        @Index(name = "idx_stock_updated", columnList = "updatedAt")
    }
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

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal productionCostL;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal productionVolumeL;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal availableL;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Stock() {}

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public BigDecimal getProductionCostL() {
        return productionCostL;
    }

    public void setProductionCostL(BigDecimal productionCostL) {
        this.productionCostL = productionCostL;
    }

    public BigDecimal getProductionVolumeL() {
        return productionVolumeL;
    }

    public void setProductionVolumeL(BigDecimal productionVolumeL) {
        this.productionVolumeL = productionVolumeL;
    }

    public BigDecimal getAvailableL() {
        return availableL;
    }

    public void setAvailableL(BigDecimal availableL) {
        this.availableL = availableL;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}