package com.johnbryce.dao;

import java.util.List;

import com.johnbryce.beans.Company;

public interface CompaniesDAO {
	boolean isCompanyExists(String email, String password);

	void addCompany(Company company);

	void updateCompany(Company company);

	void deleteCompany(int companyID);

	List<Company> getAllCompanies();

	Company getOneCompany(int companyID);
	
	int getCompanyID(String email, String password);

}
