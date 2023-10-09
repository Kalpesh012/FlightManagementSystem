package FlightManagementSystem.Model;

import java.util.Date;
import java.util.List;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User { //Bean validation
		
		User(){}
		public User(Integer userId) {
		
		this.userId = userId;
		}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer userId;
		
		@Column(length = 100, nullable = false)
		private String userName;
		
		@Column(length = 20, updatable = true)
		@Size(min = 8, max =20,message = "password must be between 8 to 20 characters long.")
		@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters including alphanumerics and special characters")
		private String userPassword;
		
		
		@Column(length = 50, updatable = true, unique = true)
		@Email(message = "Email id format is incorrect.")
		private String userEmail;
		
		@Column(updatable = true)
		private Date dob;
		
		@Column(length = 150, updatable = true)
		private String about;
		
		@Column(length = 20, nullable = false, updatable = true)
		private String role;
		
		@Column()
		private String phoneNumber;
		
		@JsonIgnore
		@OneToMany(mappedBy="user")
		private List<Feedback> feedback;
		
		@JsonIgnore
		@OneToMany(mappedBy = "user")
		private List<FlightBooking> booking = new ArrayList<FlightBooking>();

		public Integer getUserId() {
			return userId;
		}
		public String getUserName() {
			return userName;
		}
		public String getUserPassword() {
			return userPassword;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public Date getDob() {
			return dob;
		}
		public String getAbout() {
			return about;
		}
		public String getRole() {
			return role;
		}
		public List<Feedback> getFeedback() {
			return feedback;
		}
		public List<FlightBooking> getBooking() {
			return booking;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public void setAbout(String about) {
			this.about = about;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public void setFeedback(List<Feedback> feedback) {
			this.feedback = feedback;
		}
		public void setBooking(List<FlightBooking> booking) {
			this.booking = booking;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
					+ ", userEmail=" + userEmail + ", dob=" + dob + ", about=" + about + ", role=" + role
					+ "]";
		}
		
	}
