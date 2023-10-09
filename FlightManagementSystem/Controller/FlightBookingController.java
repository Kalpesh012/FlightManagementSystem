package FlightManagementSystem.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FlightManagementSystem.FlightDetailsServiceImpl.FlightBookingServiceImpl;
import FlightManagementSystem.Model.FlightBooking;

@RestController
@RequestMapping("/Booking")
@CrossOrigin(origins="*")
public class FlightBookingController {

	@Autowired
	private FlightBookingServiceImpl bookingService;
	
	@PostMapping("/saveBooking/{userId}/{flightId}" )
	public ResponseEntity<FlightBooking> saveBooking(@PathVariable("userId") int userId,@PathVariable("flightId") int flightId, @RequestBody FlightBooking bookingObj ){
		try {
			return new ResponseEntity<FlightBooking>(bookingService.saveBookFlight(bookingObj,userId, flightId), HttpStatus.CREATED);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/viewAllBooking")
	public ResponseEntity<List<FlightBooking>> getAllFlightDetails(){
	
		try {
			return new ResponseEntity<>(bookingService.viewAllBookings(), HttpStatus.OK);
		} 

		catch (Exception exc) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/viewBookingByOneId/{bookingId}")
	public ResponseEntity<FlightBooking> getBookingDetailByBookingId(@PathVariable("bookingId") int bookingId ){
		try {
			return new ResponseEntity<>(bookingService.viewBookingDetailsByBookingId(bookingId), HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteRecordBetweenDates")
	public void deleteBookingBetweenDates(@RequestParam("startDate")  LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
		try {
			bookingService.removeDataBetweenTwoDates(startDate, endDate);
		}
		catch(Exception exc){
			System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getOtp/{userId}")
	public String generatingOtp(@PathVariable("userId") int userId) {
		return bookingService.getOtp(userId);
	}
	
	@GetMapping("/getOtpForMobile/{userId}")
	 public String generatingOtpForMobileAsSMS(@PathVariable("userId")Integer userId)throws Exception {
		 return bookingService.generatingOtpForMobile(userId);
	 }
}
