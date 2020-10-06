package com.johnbryce.tests.facade;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.exception.AlreadyExistException;
import com.johnbryce.exception.unableToUpdateException;
import com.johnbryce.facade.AdministratorFacade;

public class AdministratorFacadeTesting {

	public void test() {
		AdministratorFacade administratorFacade = new AdministratorFacade();
		List<Coupon> coupons = new ArrayList<Coupon>();
		Company company = new Company(1, "Bezek", "bezek@gmail.com", "123", coupons);

		System.out.println("Add company");
		try {
			administratorFacade.addCompany(company);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();

		System.out.println("Update company");
		company.setEmail("bezek111@gmail.com");
		try {
			administratorFacade.updateCompany(company);
		} catch (unableToUpdateException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();

		System.out.println("Get all companies");
		System.out.println(administratorFacade.getAllCompany());
		System.out.println();

		System.out.println("Get one company");
		System.out.println(administratorFacade.getOneCompany(company.getId()));
		System.out.println();

		System.out.println("Delete company");
		administratorFacade.deleteCompany(company.getId());

		System.out.println("Add customer");
		Customer customer = new Customer(1, "Elad", "Nissim", "elad@gmail.com", "123", coupons);
		try {
			administratorFacade.addCustomer(customer);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();

		System.out.println("Update customer");
		customer.setEmail("eladnissim@gmail.com");
		try {
			administratorFacade.updateCustomer(customer);
		} catch (unableToUpdateException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();

		System.out.println("Get all customers");
		System.out.println(administratorFacade.getAllCustomers());
		System.out.println();

		System.out.println("Get one customer");
		System.out.println(administratorFacade.getOneCustomer(customer.getId()));
		System.out.println();

		System.out.println("Delete customer");
		administratorFacade.deleteCustomer(customer.getId());
	}
		

}
