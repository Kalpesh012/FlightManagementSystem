package FlightManagementSystem.Model;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="flight_booking")
@DynamicUpdate
public class FlightBooking {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@Column(name = "source")
	private String source;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "current_date")
	private LocalDate bookingDate;
	
	@Column(name = "journey_date")
	private LocalDate journeyDate;
	
	@Min(value = 1)
	@Column(name="booking_cost")
	private Integer bookingCost;
	
	@Min(value=1)
	@Column(name="seats_booked")
	private Integer seatsBooked;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="flight_object")
	private FlightDetails flight;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_object")
	private User user;

	public Integer getBookingId() {
		return bookingId;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public Integer getBookingCost() {
		return bookingCost;
	}

	public Integer getSeatsBooked() {
		return seatsBooked;
	}

	public FlightDetails getFlight() {
		return flight;
	}

	public User getUser() {
		return user;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public void setBookingCost(Integer bookingCost) {
		this.bookingCost = bookingCost;
	}

	public void setSeatsBooked(Integer seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public void setFlight(FlightDetails flight) {
		this.flight = flight;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "FlightBooking [bookingId=" + bookingId + ", source=" + source + ", destination=" + destination
				+ ", bookingDate=" + bookingDate + ", journeyDate=" + journeyDate + ", bookingCost=" + bookingCost
				+ ", seatsBooked=" + seatsBooked + ", flight=" + flight + ", user=" + user + "]";
	}
	
}
