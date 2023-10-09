package FlightManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FlightManagementSystem.Model.AirPort;

@Repository
public interface AirportRepository extends JpaRepository<AirPort, Integer>{

	List<AirPort> findByAirportNameContainingIgnoreCase(String airportName);

}
