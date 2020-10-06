package com.johnbryce.tests.facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.exception.AlreadyExistException;
import com.johnbryce.exception.NotExistException;
import com.johnbryce.facade.AdministratorFacade;
import com.johnbryce.facade.CompanyFacade;
import com.johnbryce.facade.CustomerFacade;

public class CustomerFacadeTesting {

	public void test() {
		CustomerFacade customerFacade = new CustomerFacade();
		customerFacade.setCustomerID(1);
		CompanyFacade companyFacade = new CompanyFacade();
		companyFacade.setCompanyID(1);
		Date date1 = Date.valueOf("2020-07-31");
		Date date2 = Date.valueOf("2022-10-22");
		Coupon coupon = new Coupon(1, 1, Category.VACATION, "aaa", "vvv", date1, date2, 10, 100.0, "dsa");
		Coupon coupon2 = new Coupon(2, 1, Category.ELECTRICITY, "rrrr", "yyyy", date1, date2, 5, 200, "asd");
		Coupon coupon3 = new Coupon(3, 1, Category.RESTURANT, "fff", "iiii", date1, date2, 12, 235, "hhh");
		AdministratorFacade administratorFacade = new AdministratorFacade();
		List<Coupon> coupons = new ArrayList<Coupon>();
		Company company = new Company(1, "Bezek", "bezek@gmail.com", "123", coupons);
		Customer customer = new Customer(1, "Elad", "Nissim", "elad@gmail.com", "123", coupons);
		
		//add customer
		try {
			administratorFacade.addCustomer(customer);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		//add company
		try {
			administratorFacade.addCompany(company);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		//add coupons
		try {
			companyFacade.addCoupon(coupon);
			companyFacade.addCoupon(coupon2);
			companyFacade.addCoupon(coupon3);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		space();

		System.out.println("Purchase coupon");
		try {
			customerFacade.purchaseCoupon(coupon);
			customerFacade.purchaseCoupon(coupon2);
			customerFacade.purchaseCoupon(coupon3);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		space();

		System.out.println("Get customer's coupon");
		System.out.println(customerFacade.getCustomerCoupons());
		space();
		
		System.out.println("Get customer's coupon by category");
		System.out.println(customerFacade.getCustomerCoupons(Category.ELECTRICITY));
		space();

		System.out.println("Get customer's coupon by  max price");
		System.out.println(customerFacade.getCustomerCoupons(200));
		space();

		customer.setCoupons(customerFacade.getCustomerCoupons());
		System.out.println("Get customer's details");
		System.out.println(customerFacade.getCustomerDetails());

	}

	public static void space() {
		System.out.println();
	}
}
