package com.johnbryce.facade;

import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.dbdao.CompaniesDBDAO;
import com.johnbryce.dbdao.CouponsDBDAO;
import com.johnbryce.dbdao.CustomersDBDAO;

public abstract class ClientFacade {
	protected CompaniesDAO companiesDAO;
	protected CustomersDAO customersDAO;
	protected CouponsDAO couponsDAO;

	public ClientFacade() {
		this.companiesDAO = new CompaniesDBDAO();
		this.customersDAO = new CustomersDBDAO();
		this.couponsDAO = new CouponsDBDAO();
	}

	public abstract boolean login(String email, String password);

}
