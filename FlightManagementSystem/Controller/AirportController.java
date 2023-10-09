package FlightManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FlightManagementSystem.FlightDetailsServiceImpl.AirportServiceImpl;
import FlightManagementSystem.Model.AirPort;

@RestController
@RequestMapping("AirportServ")
@CrossOrigin
public class AirportController {
	
	@Autowired
	private AirportServiceImpl airportService;
	
	@PostMapping("/saveAirport")
	public ResponseEntity<AirPort> saveAirports(@RequestBody AirPort airportObj){
			try {
				return new ResponseEntity<>(airportService.saveAirport(airportObj), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
	@GetMapping("/readairportDetail/{airportId}")
	public ResponseEntity<AirPort> getFlightDetailsByRecordNumber(@PathVariable("airportId") int airportId ){
		try {
			return new ResponseEntity<AirPort>(airportService.getAirPortById(airportId), HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/readAllAirportDetails")
	public ResponseEntity<List<AirPort>> getAllFlightDetails(@RequestParam(required = false) String airportName){
		try {
			return new ResponseEntity<>(airportService.getallAirPorts(airportName), HttpStatus.OK);
		} 

		catch (Exception exc) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("viewAllFlights")
	public ResponseEntity<List<AirPort>> getAllFlightDetailsFromAirport(@RequestParam Iterable<Integer> airportId){
		try {
			return new ResponseEntity<>(airportService.getallAirPortsFlight(airportId), HttpStatus.OK);
		} 

		catch (Exception exc) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
