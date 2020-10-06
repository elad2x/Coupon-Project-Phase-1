package com.johnbryce.facade;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.beans.CustomerVsCoupon;
import com.johnbryce.exception.AlreadyExistException;
import com.johnbryce.exception.unableToUpdateException;

public class AdministratorFacade extends ClientFacade {

	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}

	public void addCompany(Company company) throws AlreadyExistException {
		List<Company> companies = getAllCompany();
		for (Company comp : companies) {
			if (comp.getName().equals(company.getName()) && comp.getEmail().equals(company.getEmail())) {
				throw new AlreadyExistException("Company's name or email already exist");
			}
		}
		this.companiesDAO.addCompany(company);

	}

	public void updateCompany(Company company) throws unableToUpdateException {
		List<Company> companies = getAllCompany();
		for (Company comp : companies) {
			if (comp.getId() == company.getId() && comp.getName().equals(company.getName())) {
				this.companiesDAO.updateCompany(company);
			}
		}
		throw new unableToUpdateException("Company's id or name can not be updated");
	}

	public void deleteCompany(int companyID) {
		List<Coupon> AllCompanycoupons = this.couponsDAO.getAllCoupons();
		List<Coupon> companyCoupns = new ArrayList<>();
		for (Coupon coup : AllCompanycoupons) {
			if (coup.getCompanyID() == companyID) {
				companyCoupns.add(coup);
			}
		}
		List<CustomerVsCoupon> customerVsCoupons = this.couponsDAO.getAllCouponPurchase();
		for (CustomerVsCoupon cusVsCoup : customerVsCoupons) {
			for (int i = 0; i < companyCoupns.size(); i++) {
				if (cusVsCoup.getCouponID() == companyCoupns.get(i).getId()) {
					this.couponsDAO.deleteCouponPurchaseV2(companyCoupns.get(i).getId());
				}
			}
		}
		this.couponsDAO.deleteCouponV2(companyID);
		this.companiesDAO.deleteCompany(companyID);
	}

	public ArrayList<Company> getAllCompany() {
		return (ArrayList<Company>) this.companiesDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyID) {
		List<Company> companies = companiesDAO.getAllCompanies();
		for (Company comp : companies) {
			if (comp.getId()==companyID) {
				return comp;
			}
		}
		return null;
	}

	public void addCustomer(Customer customer) throws AlreadyExistException {
		List<Customer> customers = this.customersDAO.getAllCustomers();
		for (Customer cust : customers) {
			if (cust.getEmail().equals(customer.getEmail())) {
				throw new AlreadyExistException("This customer's email already exist");
			}
		}
		this.customersDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws unableToUpdateException {
		List<Customer> customers = this.customersDAO.getAllCustomers();
		for (Customer cust : customers) {
			if (cust.getId() != customer.getId()) {
				throw new unableToUpdateException("Customer's id can not be updated");
			}
		}
		this.customersDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int customerID) {
		List<CustomerVsCoupon> customerVsCoupons = this.couponsDAO.getAllCouponPurchase();
		for (CustomerVsCoupon custVsCoup : customerVsCoupons) {
			if (custVsCoup.getCustomerID() == customerID) {
				this.couponsDAO.deleteCouponPurchaseV3(customerID);
			}
		}
		this.customersDAO.deleteCustomer(customerID);
	}

	public ArrayList<Customer> getAllCustomers() {
		return (ArrayList<Customer>) this.customersDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerID) {
		
		return this.customersDAO.getOneCustomer(customerID);

	}

}
