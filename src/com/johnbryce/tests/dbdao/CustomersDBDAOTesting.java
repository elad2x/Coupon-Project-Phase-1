package com.johnbryce.tests.dbdao;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.dbdao.CustomersDBDAO;

public class CustomersDBDAOTesting {

	public static void main(String[] args) {
		CustomersDAO customersDAO = new CustomersDBDAO();
		List<Coupon> coupons = new ArrayList<Coupon>();
		Customer customer1 = new Customer(1, "Elad", "Nissim", "elad@gmail.com", "123", coupons);

		System.out.println("Add comapny...");
		customersDAO.addCustomer(customer1);

		System.out.println("Customer is exit");
		System.out.println(customersDAO.isCustomerExists("elad@gmail.com", "123"));

		System.out.println("Customer is not exit");
		System.out.println(customersDAO.isCustomerExists("Avi@gmail.com", "267"));

		System.out.println("Customer update");
		customer1.setFirstName("Dan");
		customersDAO.updateCustomer(customer1);

		System.out.println("Get one customer");
		System.out.println(customersDAO.getOneCustomer(customer1.getId()));

		System.out.println("Get all customers");
		System.out.println(customersDAO.getAllCustomers());

		System.out.println("Delete customer");
		customersDAO.deleteCustomer(customer1.getId());

	}

}
