package FlightManagementSystem.FlightDetailsServiceImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import FlightManagementSystem.Model.FlightDetails;
import FlightManagementSystem.Repository.FlightDetailsRepository;
import FlightManagementSystem.Service.FlightDetailService;

/*
 * This class implements the service interface
 * This class have methods on Business logic
 */
@Service
public class FlightDetailsServiceImpl implements FlightDetailService{

	@Autowired
	private FlightDetailsRepository flightDetailRepository;

	public FlightDetailsServiceImpl(FlightDetailsRepository flightDetailRepository) {
		this.flightDetailRepository = flightDetailRepository;
	}

	@Override
	//This method saves the inserted record
	public FlightDetails saveFlight(FlightDetails flightObj) {
		return flightDetailRepository.saveAndFlush(flightObj);
	}

	@Override
	//This method is used to fetch all the records or searched records from database 
	public List<FlightDetails> getFlightDetailsFromDB(String flightName) { 
		List<FlightDetails> flightsObj = new ArrayList<FlightDetails>();
		
		if(flightName == null)
			flightDetailRepository.findAll().forEach(flightsObj::add);
		else
			 flightDetailRepository.findByFlightNameContainingIgnoreCase(flightName).forEach(flightsObj::add);
		
		return flightsObj;
	}

	@Override
	//This method is used to fetch a particular record from database
	public FlightDetails getFlightDetailsByRecordNumber(int recordNumber) {
		Optional<FlightDetails> flightObj = flightDetailRepository.findById(recordNumber);
		if(flightObj.isPresent()) {
			return flightObj.get();
		}
		else
			return null;
		}

	@Override
	//This method is for deleting a particular record from database
	public void deleteFlightDetailByRecordNumber(int recordNumber) {
		Optional<FlightDetails> flightObj = flightDetailRepository.findById(recordNumber);
		if(flightObj.isPresent()) {
			flightDetailRepository.deleteById(recordNumber);
		}
	}

	@Override
	//This method is used for deleting all the records from the database
	public void deleteAllFlightDetails() {
		flightDetailRepository.deleteAll();		
	}

	@Override
	//This method is used to update the existing record in database
	public FlightDetails updateFlightDetailsByRecordNumber(FlightDetails flightObj, int recordNumber) {
		Optional<FlightDetails> flightDetailsFromDB = flightDetailRepository.findById(recordNumber);
		if(flightDetailsFromDB.isPresent()) {
			FlightDetails existingFlight = flightDetailsFromDB.get();

			existingFlight.setSource(flightObj.getSource());
			existingFlight.setDestination(flightObj.getDestination());
			existingFlight.setTicketCost(flightObj.getTicketCost());
			existingFlight.setCapacity(flightObj.getCapacity());
			existingFlight.setFlightCode(flightObj.getFlightCode());
			existingFlight.setFlightName(flightObj.getFlightName());
			existingFlight.setIsCommercial(flightObj.getIsCommercial());
			existingFlight.setFlightRating(flightObj.getFlightRating());

			return flightDetailRepository.save(existingFlight);
		}
		else
			return null;
	}

	//This is Query method for fetching the commercial flight details
	public List<FlightDetails> CheckIfFlightIsCommercial(Boolean booleanObj){
		List<FlightDetails> flightObj = flightDetailRepository.findByIsCommercial(true);
		return flightObj;
	}

	//This is Query method for fetching the non-commercial flight details
	public List<FlightDetails> CheckIfFlightIsNonCommercial(Boolean booleanObj){
		List<FlightDetails> flightObj = flightDetailRepository.findByIsCommercial(false);
		return flightObj;
	}
	
	public List<FlightDetails> fetchingRecordsBySourceAndDestination(String source, String destination){
		List<FlightDetails> flightObj = flightDetailRepository.findBySourceAndDestination(source, destination);
		return flightObj;
	}
	
	public List<FlightDetails> SortByTicketCostAsc(String direction, String fieldName){
		List<FlightDetails> flightObj = new ArrayList<FlightDetails>();
		flightObj = flightDetailRepository.findAll(Sort.by(getSortDirection(direction), fieldName));
		return flightObj;
	}
	
	public List<FlightDetails> SortByRatingAsc(String direction, String fieldName){
		List<FlightDetails> flightObj = new ArrayList<FlightDetails>();
		flightObj = flightDetailRepository.findAll(Sort.by(getSortDirection(direction), fieldName));
		return flightObj;
	}
	
	private Sort.Direction getSortDirection(String direction) {
	    if (direction.equalsIgnoreCase("asc")) {
	      return Sort.Direction.ASC;
	    } else if (direction.equalsIgnoreCase("desc")) {
	      return Sort.Direction.DESC;
	    }
	    return Sort.DEFAULT_DIRECTION;
	  }
}
