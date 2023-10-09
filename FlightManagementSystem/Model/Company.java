package FlightManagementSystem.Model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="company_details")
@DynamicUpdate
public class Company {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
	private Integer companyId;
	
	
	@Column(name="company_name", nullable = false, length = 100, updatable = true)
	private String companyName;
	
	@Column(name="owner_name", nullable = false, length = 50, updatable = true)
	private String companyOwnerName;
	
	@Min(value=1)
	@Column(name="years_of_service", nullable = false)
	private Integer yearsOfService;
	
	@Column(name="year_established")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate establishedYear;
	
	@JsonIgnore
	@OneToMany(mappedBy = "ownerCompanyName")
	private List<FlightDetails> flightId;

	public Integer getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyOwnerName() {
		return companyOwnerName;
	}

	public Integer getYearsOfService() {
		return yearsOfService;
	}

	public LocalDate getEstablishedYear() {
		return establishedYear;
	}

	public List<FlightDetails> getFlightId() {
		return flightId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyOwnerName(String companyOwnerName) {
		this.companyOwnerName = companyOwnerName;
	}

	public void setYearsOfService(Integer yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public void setEstablishedYear(LocalDate establishedYear) {
		this.establishedYear = establishedYear;
	}

	public void setFlightId(List<FlightDetails> flightId) {
		this.flightId = flightId;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyOwnerName="
				+ companyOwnerName + ", yearsOfService=" + yearsOfService + ", establishedYear=" + establishedYear
				+ "]";
	}
	
}
