package FlightManagementSystem.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="feedback_detail")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;
		
	@Min(value=1, message="Rating must be in range of 1-5")
	@Max(value=5, message="Rating must be in range of 1-5")
	private Integer flightRating;
	
	@Min(value=1, message="Rating must be in range of 1-5")
	@Max(value=5, message="Rating must be in range of 1-5")
	private Integer overallRating;
	
	private String comments;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="flight")
	private FlightDetails flight;

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public Integer getFlightRating() {
		return flightRating;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public String getComments() {
		return comments;
	}

	public User getUser() {
		return user;
	}

	public FlightDetails getFlight() {
		return flight;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public void setFlightRating(Integer flightRating) {
		this.flightRating = flightRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFlight(FlightDetails flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", flightRating=" + flightRating + ", overallRating="
				+ overallRating + ", comments=" + comments + ", user=" + user + ", flight=" + flight + "]";
	}
	
}
