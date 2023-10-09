package FlightManagementSystem.Service;

import java.util.List;

import FlightManagementSystem.Model.AirPort;

public interface AirportService {
	
	AirPort saveAirport(AirPort airportObj);
	
	AirPort getAirPortById(Integer airportId);
	
	List<AirPort> getallAirPorts(String airportName);
	
	void removeAnAirport(Integer airportId);
}
