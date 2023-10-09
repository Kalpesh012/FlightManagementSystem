package FlightManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FlightManagementSystem.Model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	List<Company> findByCompanyNameContainingIgnoreCase(String companyName);
	
}
