package FlightManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import FlightManagementSystem.FlightDetailsServiceImpl.CompanyServiceImpl;
import FlightManagementSystem.Model.Company;

@RestController
@RequestMapping("/CompanyServ")
@CrossOrigin
public class CompanyDetailController {

	@Autowired
	private CompanyServiceImpl companyService;
	
	public CompanyDetailController(CompanyServiceImpl companyService) {
		this.companyService = companyService;
	}
	
	@PostMapping("/saveCompany")
	public ResponseEntity<Company> saveFlight(@RequestBody Company companyObj){
		try {
			return new ResponseEntity<Company>(companyService.saveCompany(companyObj), HttpStatus.CREATED);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateCompany/{companyId}")
	public ResponseEntity<Company> updateFlightDetailsByRecordNumber(@PathVariable("companyId") Integer companyId,
			@RequestBody Company companyObj){
		try {
			return new ResponseEntity<Company>(companyService.updateCompanyDetailByCompanyId(companyObj, companyId), HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("readAllCompanyDetail")
	public ResponseEntity<List<Company>> getAllFlightDetails(@RequestParam(required = false) String companyName){
		try {
			return new ResponseEntity<>(companyService.getCompanyDetailsFromDB(companyName), HttpStatus.OK);
		} 

		catch (Exception exc) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/readCompany/{companyId}")
	public ResponseEntity<Company> getFlightDetailsByRecordNumber(@PathVariable("companyId") Integer companyId ){
		try {
			return new ResponseEntity<Company>(companyService.getCompanyDetailsByCompanyId(companyId), HttpStatus.OK);
		}
		catch(Exception exc) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteCompany/{companyId}")
	public void deleteFlightDetailByRecordNumber(@PathVariable("companyId") Integer companyId) {
		try {
			companyService.deleteCompanyDetailByCompanyId(companyId);
		}
		catch(Exception exc) {
			System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("deleteAllCompany")
	public void deleteAllFlightDetails() {
		try {
			companyService.deleteAllCompanyDetails();
		}
		catch(Exception exc) {
			System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
