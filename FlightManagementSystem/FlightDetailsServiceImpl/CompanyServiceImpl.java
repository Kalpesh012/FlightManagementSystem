package FlightManagementSystem.FlightDetailsServiceImpl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FlightManagementSystem.Model.Company;
import FlightManagementSystem.Model.FlightDetails;
import FlightManagementSystem.Repository.CompanyRepository;
import FlightManagementSystem.Service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public Company saveCompany(Company companyObj) {
		return companyRepository.save(companyObj);
	}

	@Override
	public List<Company> getCompanyDetailsFromDB(String companyName) {
		List<Company> companyObj = new ArrayList<Company>();
		
		if(companyName == null)
			companyRepository.findAll().forEach(companyObj :: add);
		else
			companyRepository.findByCompanyNameContainingIgnoreCase(companyName).forEach(companyObj :: add);
		
		return companyObj;
	}

	@Override
	public Company getCompanyDetailsByCompanyId(Integer companyId) {
		Optional<Company> companyObj = companyRepository.findById(companyId);
		if(companyObj.isPresent()) {
			return companyObj.get();
		}
		else
			return null;
	}

	@Override
	public Company updateCompanyDetailByCompanyId(Company companyObj, Integer companyId) {
		Optional<Company> companyDetailsFromDB = companyRepository.findById(companyId);
		if(companyDetailsFromDB.isPresent()) {
			Company existingCompany = companyDetailsFromDB.get();
			List<FlightDetails> flightObj = existingCompany.getFlightId();
			
			existingCompany.setCompanyName(companyObj.getCompanyName());
			existingCompany.setCompanyOwnerName(companyObj.getCompanyOwnerName());
			existingCompany.setYearsOfService(companyObj.getYearsOfService());
			
		
			
			return companyRepository.save(existingCompany);
		}
		else
			return null;
	}

	@Override
	public void deleteCompanyDetailByCompanyId(Integer companyId) {
		Optional<Company> companyObj = companyRepository.findById(companyId);
		if(companyObj.isPresent()) {
			companyRepository.deleteById(companyId);
		}
	}

	@Override
	public void deleteAllCompanyDetails() {
		companyRepository.deleteAll();
	}

}
