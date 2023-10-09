package FlightManagementSystem.FlightDetailsServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FlightManagementSystem.Model.AirPort;
import FlightManagementSystem.Repository.AirportRepository;
import FlightManagementSystem.Service.AirportService;

@Service
public class AirportServiceImpl implements AirportService{
	
	@Autowired
	private AirportRepository airportRepository;
	
	public AirportServiceImpl(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	@Override
	public AirPort getAirPortById(Integer airportId) {
		Optional<AirPort> airportObj = airportRepository.findById(airportId);
		if(airportObj.isPresent()) {
			return airportObj.get();
		}
		else
			return null;
	}

	@Override
	public List<AirPort> getallAirPorts(String airportName) {
		List<AirPort> airportObj = new ArrayList<AirPort>();
		
		if(airportName == null)
			airportRepository.findAll().forEach(airportObj::add);
		else
			airportRepository.findByAirportNameContainingIgnoreCase(airportName).forEach(airportObj::add);
		
		return airportObj;
	}

	
	public List<AirPort> getallAirPortsFlight(Iterable<Integer> airportId) {
		List<AirPort> airportObj = new ArrayList<AirPort>();
		
		
			airportRepository.findAllById(airportId).forEach(airportObj::add);
		
		return airportObj;
	}

	@Override
	public AirPort saveAirport(AirPort airportObj) {
		return airportRepository.save(airportObj);
	}

	@Override
	public void removeAnAirport(Integer airportId) {
		Optional<AirPort> airportObj = airportRepository.findById(airportId);
		
		if(airportObj.isPresent())
			airportRepository.deleteById(airportId);
	}

}
