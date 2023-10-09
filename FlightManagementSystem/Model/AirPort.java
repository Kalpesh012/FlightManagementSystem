package FlightManagementSystem.Model;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="airport_detail")
@DynamicUpdate
public class AirPort {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer airportId;
	
	@Column(name="airport_name")
	private String airportName;
	
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="country_name")
	private String countryName;
	
	@JsonIgnore
	@OneToMany(mappedBy="airport")
	private List<FlightDetails> flight;
	
	public Integer getAirportId() {
		return airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<FlightDetails> getFlight() {
		return flight;
	}

	public void setFlight(List<FlightDetails> flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "AirPort [airportId=" + airportId + ", airportName=" + airportName + ", cityName=" + cityName
				+ ", countryName=" + countryName + "]";
	}
	
}
