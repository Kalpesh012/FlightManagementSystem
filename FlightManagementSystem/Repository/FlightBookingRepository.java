package FlightManagementSystem.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import FlightManagementSystem.Model.FlightBooking;
import FlightManagementSystem.Model.User;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer>{

	@Modifying
	@Query(value = "DELETE FROM flight_booking WHERE journey_date BETWEEN ?1 AND ?2",nativeQuery = true  )
	void deleteByDateBetween(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "SELECT * FROM flight_booking WHERE user_object = ?1",nativeQuery = true  )
	List<FlightBooking> getBookingDetails(int userId);
}
