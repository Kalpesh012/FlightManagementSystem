package FlightManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FlightManagementSystem.FlightDetailsServiceImpl.FlightDetailsServiceImpl;
import FlightManagementSystem.Model.AirPort;
import FlightManagementSystem.Model.Feedback;
import FlightManagementSystem.Model.FlightBooking;
import FlightManagementSystem.Model.FlightDetails;
import FlightManagementSystem.Model.User;
import FlightManagementSystem.Repository.AirportRepository;
import FlightManagementSystem.Repository.CompanyRepository;
import FlightManagementSystem.Repository.FeedbackRepository;
import FlightManagementSystem.Repository.FlightBookingRepository;
@RestController
@RequestMapping("FlightServ.com")
@CrossOrigin(origins="*")
/*
 * This is the controller class which handles all requests
 * @RestController is used so that this class can give responses to requestes along with taking requestes
 */


//http://localhost:8080/FlightBooking.com/%20readAllFlightDetails 
public class FlightDetailController {

	@Autowired
	private FlightDetailsServiceImpl flightService;

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private FlightBookingRepository bookingRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	public FlightDetailController(FlightDetailsServiceImpl flightService) {
		this.flightService = flightService;
	}

	@PostMapping("/saveFlight/{airportId}/{companyId}")
	//API method for inserting records
	public ResponseEntity<FlightDetails> saveFlight(@PathVariable("airportId") int airportId,@PathVariable("companyId") int companyId,@RequestBody FlightDetails flightObj){
		
			try {
				AirPort airportDetails = airportRepository.findById(airportId).get() ;
				if(airportDetails != null) {
					flightObj.setAirport(airportDetails);
				}
				companyRepository.findById(companyId).map(company -> {
					flightObj.setOwnerCompanyName(company);
					flightObj.setFlightName(flightObj.getOwnerCompanyName().getCompanyName());
					flightObj.setAvailableSeats(flightObj.getCapacity());
				return new ResponseEntity<FlightDetails>(flightService.saveFlight(flightObj), HttpStatus.CREATED);
				
				}).orElseThrow(() -> new Exception("Internal_Server_Error"));
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
}

	@PutMapping("/updateFlightDetails/{recordNumber}")
	
	//API method for updating record values
	public ResponseEntity<FlightDetails> updateFlightDetailsByRecordNumber(@PathVariable("recordNumber") int recordNumber,
			@RequestBody FlightDetails flightObj){
		try {
			return new ResponseEntity<FlightDetails>(flightService.updateFlightDetailsByRecordNumber(flightObj, recordNumber), HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/readAllFlightDetails")
	//API method for fetching all the records or searched records
	public ResponseEntity<List<FlightDetails>> getAllFlightDetails(@RequestParam(required = false) String flightName){
		try {
			return new ResponseEntity<>(flightService.getFlightDetailsFromDB(flightName), HttpStatus.OK);
		} 

		catch (Exception exc) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/readFlightDetail/{recordNumber}")
	//API method for showing a particular record
	public ResponseEntity<FlightDetails> getFlightDetailsByRecordNumber(@PathVariable("recordNumber") int recordNumber){
		try {
			FlightDetails flight =  flightService.getFlightDetailsByRecordNumber(recordNumber);
			System.out.println(flight);
			return new ResponseEntity<FlightDetails>(flightService.getFlightDetailsByRecordNumber(recordNumber), HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteFlightDetail/{recordNumber}")
	//API method for deleting a particular record
	public void deleteFlightDetailByRecordNumber(@PathVariable("recordNumber") int recordNumber) {
		try {
			//bookingRepository.deleteAllInBatch();
			FlightDetails flight =  flightService.getFlightDetailsByRecordNumber(recordNumber);
			flight.getFeedback().clear();
			flight.getBooking().clear();
			
			feedbackRepository.saveAll(flight.getFeedback());
				flightService.saveFlight(flight);
			flightService.deleteFlightDetailByRecordNumber( recordNumber);
		}
		catch(Exception exc) {
			System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteAllFlightDetails")
	//API method for deleting all the records
	public void deleteAllFlightDetails() {
		try {
			feedbackRepository.deleteAll();
			bookingRepository.deleteAll();
			flightService.deleteAllFlightDetails();
		}
		catch(Exception exc) {
			System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/FlightDetails/isCommercial")
	//API method to fetch all the commercial flight details
	public ResponseEntity<List<FlightDetails>> fetchCommercialFlight(){
		try {
			return new ResponseEntity<List<FlightDetails>>(flightService.CheckIfFlightIsCommercial(true), HttpStatus.OK);
		}
		catch(Exception exc){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/FlightDetails/isNonCommercial")
	//API method to fetch all the non-commercial flight details
	public ResponseEntity<List<FlightDetails>> fetchNonCommercialFlight(){
		try {
			return new ResponseEntity<List<FlightDetails>>(flightService.CheckIfFlightIsNonCommercial(false), HttpStatus.OK);
		}
		catch(Exception exc){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/FlightDetails/{source}/{destination}")
	public ResponseEntity<List<FlightDetails>> fetchFlightBySourceAndDestination(@PathVariable("source") String source,
												@PathVariable("destination") String destination){
		try {
			return new ResponseEntity<List<FlightDetails>>(flightService.fetchingRecordsBySourceAndDestination(source, destination), HttpStatus.OK);
		}
		catch(Exception exc){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/SortByTicketCost/{direction}")
	public ResponseEntity<List<FlightDetails>> SortByTicketCostAscOrder(@PathVariable("direction") String direction, 
			@RequestParam(required = true)String fieldName){
		try {
			return new ResponseEntity<List<FlightDetails>>(flightService.SortByTicketCostAsc(direction, fieldName), HttpStatus.OK);
		}
		catch(Exception exc){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/SortByRating/{direction}")
	public ResponseEntity<List<FlightDetails>> SortByRatingAscOrder(@PathVariable("direction") String direction, 
			@RequestParam(required = true)String fieldName){
		try {
				return new ResponseEntity<List<FlightDetails>>(flightService.SortByRatingAsc(direction, fieldName), HttpStatus.OK);
		}
		catch(Exception exc){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
