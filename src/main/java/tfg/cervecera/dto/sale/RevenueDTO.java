package tfg.cervecera.dto.sale;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RevenueDTO {

    private Long id;
    private String name;
    private BigDecimal revenue;
    private Integer year;
    private Integer month;
    
    public RevenueDTO() {}


    public RevenueDTO(Long id, String name, BigDecimal revenue) {
        this.id = id;
        this.name = name;
        this.revenue = revenue;
    }

    public RevenueDTO(Integer year, Integer month, BigDecimal revenue) {
        this.year = year;
        this.month = month;
        this.revenue = revenue;
    }

    public RevenueDTO(Long id, String name, BigDecimal revenue, Integer year, Integer month) {
        this.id = id;
        this.name = name;
        this.revenue = revenue;
        this.year = year;
        this.month = month;
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

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	}