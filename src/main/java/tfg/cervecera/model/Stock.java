package tfg.cervecera.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
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
    private BigDecimal productionCostL;

    @Column(nullable = false)
    private Double productionVolumeL;

    @Column(nullable = false)
    private Double availableL;

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

	public Double getProductionVolumeL() {
		return productionVolumeL;
	}

	public void setProductionVolumeL(Double productionVolumeL) {
		this.productionVolumeL = productionVolumeL;
	}

	public Double getAvailableL() {
		return availableL;
	}

	public void setAvailableL(Double availableL) {
		this.availableL = availableL;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
