package FlightManagementSystem.Service;

import java.util.List;

import FlightManagementSystem.Model.Company;

public interface CompanyService {

	Company saveCompany(Company companyObj);

	List<Company> getCompanyDetailsFromDB(String companyName);

	Company getCompanyDetailsByCompanyId(Integer companyId);

	Company updateCompanyDetailByCompanyId(Company companyObj, Integer companyId);

	void deleteCompanyDetailByCompanyId(Integer companyId);

	void deleteAllCompanyDetails();
}
