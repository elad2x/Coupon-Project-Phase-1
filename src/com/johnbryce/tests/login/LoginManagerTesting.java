package com.johnbryce.tests.login;

import com.johnbryce.facade.AdministratorFacade;
import com.johnbryce.facade.CompanyFacade;
import com.johnbryce.facade.CustomerFacade;
import com.johnbryce.security.ClientType;
import com.johnbryce.security.LoginManager;

public class LoginManagerTesting {

	public static AdministratorFacade adminLog() {
		AdministratorFacade administratorFacade = null;
		try {
			administratorFacade = (AdministratorFacade) LoginManager.login("admin@admin.com", "admin",
					ClientType.Administrator);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (administratorFacade != null) {
			System.out.println();
			System.out.println("Admin Login Succesfully");
			System.out.println();
			return administratorFacade;
		} else {
			return null;
		}
	}

	public static CompanyFacade companyLog() {
		CompanyFacade companyFacade = null;
		try {
			companyFacade = (CompanyFacade) LoginManager.login("bezek111@gmail.com", "123", ClientType.Company);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (companyFacade != null) {
			System.out.println(companyFacade.getCompanyDetails().getName() + " Company Login Succesfully");
			System.out.println();
			return companyFacade;
		} else {
			return null;
		}
	}

	public static CustomerFacade customerLog() {
		CustomerFacade customerFacade = null;
		try {
			customerFacade = (CustomerFacade) LoginManager.login("eladnissim@gmail.com", "123", ClientType.Customer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (customerFacade != null) {
			System.out.println(customerFacade.getCustomerDetails().getFirstName() + " Login Succesfully");
			System.out.println();
			return customerFacade;
		} else {
			return null;
		}
	}
}
