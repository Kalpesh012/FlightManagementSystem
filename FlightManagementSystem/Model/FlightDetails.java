package FlightManagementSystem.Model;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "flightDetails")
@DynamicUpdate
/*
 * This is the base class which consists of our attributes.
 * All the attributes which should to taken for table creation are written here */
public class FlightDetails {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "record_number")
	private Integer recordNumber;
	
	@Column(name = "commercial_flight", nullable = false)
	private Boolean isCommercial;
	
	@Column(name = "flight_code", length = 6, nullable = false)
	private String flightCode;
	
	@Column(name = "flight_name", length = 10, nullable = false)
	private String flightName;
	
	@Column(name = "flight_source", length = 25, nullable = false)
	private String source;
	
	@Column(name = "flight_destination", length = 25, nullable = false)
	private String destination;
	
	@Min(value=1, message = "minimum capacity should be greater than zero")
	@Column(name = "maximum_seats", length = 3, nullable = false)
	private Integer capacity;
	
	@Min(value=1, message = "minimum cost should start greater then zero")
	@Column(name = "ticket_cost", length = 6, nullable = false)
	private Integer ticketCost;
	
	@Min(value=1, message = "minimum rating should atleast be 1")
	@Max(value=9, message = "maximum rating is 9")
	@Column(name = "flight_rating", length = 1)
	private Integer flightRating;
	
	@Min(value = 1, message = "available seats cannot be less than one or zero")
	@Column(name = "flight_available_seats")
	private Integer availableSeats;

	@JsonIgnore
	@OneToMany(mappedBy="flight", cascade = CascadeType.ALL, orphanRemoval = true)
//	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Feedback> feedback;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "company_owner_id")
	private Company ownerCompanyName;
	
	@ManyToOne
	@JoinColumn(name = "airport_id")
	private AirPort airport;
	
	@JsonIgnore
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<FlightBooking> booking = new ArrayList<FlightBooking>();
	
	public Integer getRecordNumber() {
		return recordNumber;
	}

	public Boolean getIsCommercial() {
		return isCommercial;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public String getFlightName() {
		return flightName;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public Integer getTicketCost() {
		return ticketCost;
	}

	public Integer getFlightRating() {
		return flightRating;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public Company getOwnerCompanyName() {
		return ownerCompanyName;
	}

	public AirPort getAirport() {
		return airport;
	}

	public List<FlightBooking> getBooking() {
		return booking;
	}

	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}

	public void setIsCommercial(Boolean isCommercial) {
		this.isCommercial = isCommercial;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setTicketCost(Integer ticketCost) {
		this.ticketCost = ticketCost;
	}

	public void setFlightRating(Integer flightRating) {
		this.flightRating = flightRating;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public void setOwnerCompanyName(Company ownerCompanyName) {
		this.ownerCompanyName = ownerCompanyName;
	}

	public void setAirport(AirPort airport) {
		this.airport = airport;
	}

	public void setBooking(List<FlightBooking> booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "FlightDetails [recordNumber=" + recordNumber + ", isCommercial=" + isCommercial + ", flightCode="
				+ flightCode + ", flightName=" + flightName + ", source=" + source + ", destination=" + destination
				+ ", capacity=" + capacity + ", ticketCost=" + ticketCost + ", flightRating=" + flightRating
				+ ", availableSeats=" + availableSeats + ", ownerCompanyName="
				+ ownerCompanyName + ", airport=" + airport + "]";
	}
}
