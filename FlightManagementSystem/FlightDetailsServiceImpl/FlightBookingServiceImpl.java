package FlightManagementSystem.FlightDetailsServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import FlightManagementSystem.Model.FlightBooking;
import FlightManagementSystem.Model.FlightDetails;
import FlightManagementSystem.Model.User;
import FlightManagementSystem.Repository.FlightBookingRepository;
import FlightManagementSystem.Repository.FlightDetailsRepository;
import FlightManagementSystem.Repository.UserRepository;

@Service
public class FlightBookingServiceImpl {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FlightBookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FlightDetailsRepository flightDetailRepository;
	
	//user, from , to , flight, date, no of seats
	public FlightBooking saveBookFlight(FlightBooking bookingObj, int userId, int flightId ) throws Exception {
		User bookingUser = userRepo.findById(userId).get();
		
		FlightDetails bookingFlight =  flightDetailRepository.findById(flightId).get();
		//FlightDetails bookingFLight = (FlightDetails) flightDetailRepository.findBySourceAndDestination(bookingObj.getSource(),bookingObj.getDestination());
	if(bookingObj.getSeatsBooked() <= bookingFlight.getAvailableSeats() && bookingObj.getJourneyDate().isAfter(LocalDate.now())) {
	
		int confirmedSeats = bookingObj.getSeatsBooked();//179-3
		int presentAvailableSeats = bookingFlight.getAvailableSeats() - bookingObj.getSeatsBooked();
		bookingFlight.setAvailableSeats(presentAvailableSeats); //176
		FlightBooking confirmedBooking = new FlightBooking();
		confirmedBooking.setBookingCost(bookingFlight.getTicketCost() * bookingObj.getSeatsBooked() );//4500*3
		confirmedBooking.setFlight(bookingFlight);
		confirmedBooking.setUser(bookingUser);
		confirmedBooking.setSeatsBooked(confirmedSeats);
		confirmedBooking.setBookingDate(LocalDate.now());
		confirmedBooking.setSource(bookingFlight.getSource());
		confirmedBooking.setDestination(bookingFlight.getDestination());
		confirmedBooking.setJourneyDate(bookingObj.getJourneyDate());
		String content = "Flight Details : " + confirmedBooking.getFlight();
		emailService.sendEmail(bookingUser.getUserEmail(), "Your Booking Is Confirmed!!!", content);
		return bookingRepository.save(confirmedBooking);	}
	else 
		return null;
	}
	
	public FlightBooking viewBookingDetailsByBookingId(Integer bookingId) {
		Optional<FlightBooking> bookingObj = bookingRepository.findById(bookingId);
		if(bookingObj.isEmpty()) {
			return null;
		}
		else
			return bookingObj.get();
	}
	
	public List<FlightBooking> viewAllBookings(){ 
		return bookingRepository.findAll();
	}
	
	public void removeDataBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
		bookingRepository.deleteByDateBetween(startDate, endDate);
	}
	
	public String getOtp(int userId) {
		User bookingUser = userRepo.findById(userId).get();
		String otpNumber = OTPGeneratorImpl.generateOtp();
		emailService.sendEmail(bookingUser.getUserEmail(), "Your Booking OTP Number Is",otpNumber);
		return otpNumber;
	}
	
	public String generatingOtpForMobile(Integer userId) {
		User bookingUser = userRepo.findById(userId).get();
		String str = OTPGeneratorImpl.generateOtp();
		PhoneNumber to = new PhoneNumber("+919603114630");
		PhoneNumber from = new PhoneNumber("+12065576244");
		String otpMessage = "Dear Customer , Your OTP is  " + str + " for sending sms through Spring boot application. Thank You.";
		Message message = Message.creator(to, from,otpMessage).create();
		return str;
	}
}
