package FlightManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FlightManagementSystem.Model.FlightDetails;


@Repository
/*
 * This is an interface which interacts with database
 * JpaRepository is also a interface which provides few in-built methods blueprint 
 * so that we don't need to create each and every method declaration 
 */
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, Integer>{

	List<FlightDetails> findByIsCommercial(Boolean booleanObj);

	List<FlightDetails> findByFlightNameContainingIgnoreCase(String flightName);
	
	List<FlightDetails> findBySourceAndDestination(String source, String destination);
}
