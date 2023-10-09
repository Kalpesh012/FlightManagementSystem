package FlightManagementSystem.Service;

import java.util.List;


import FlightManagementSystem.Model.FlightDetails;
//This interface consists of blueprint of methods implemented in business logic class
public interface FlightDetailService {

	FlightDetails saveFlight(FlightDetails flightObj);

	List<FlightDetails> getFlightDetailsFromDB(String flightName);

	FlightDetails getFlightDetailsByRecordNumber(int recordNumber);

	FlightDetails updateFlightDetailsByRecordNumber(FlightDetails flightObj, int recordNumber);

	void deleteFlightDetailByRecordNumber(int recordNumber);

	void deleteAllFlightDetails();

}
