package com.johnbryce.security;

import com.johnbryce.facade.AdministratorFacade;
import com.johnbryce.facade.ClientFacade;
import com.johnbryce.facade.CompanyFacade;
import com.johnbryce.facade.CustomerFacade;

public class LoginManager {
	private static LoginManager instance = null;

	private LoginManager() {
		// TODO Auto-generated constructor stub
	}

	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

	public static ClientFacade login(String email, String password, ClientType clienttype) {
		switch (clienttype) {
		case Administrator:
			if (email.equals("admin@admin.com") && password.equals("admin")) {
				return new AdministratorFacade();
			}
			break;
		case Company:
			CompanyFacade companyFacade = new CompanyFacade();
			if (companyFacade.login(email, password)) {
				companyFacade.setCompanyID(companyFacade.getIdFromDB(email, password));
				return companyFacade;
			}
			break;

		case Customer:
			CustomerFacade customerFacade = new CustomerFacade();
			if (customerFacade.login(email, password)) {
				customerFacade.setCustomerID(customerFacade.getIdFromDB(email, password));

			}
			break;

		default:
			break;
		}
		return null;
	}
}
