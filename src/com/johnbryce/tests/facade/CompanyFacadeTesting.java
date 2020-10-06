package com.johnbryce.tests.facade;

import java.sql.Date;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Coupon;
import com.johnbryce.exception.AlreadyExistException;
import com.johnbryce.exception.unableToUpdateException;
import com.johnbryce.facade.CompanyFacade;

public class CompanyFacadeTesting {

	public void test() {
		CompanyFacade companyFacade = new CompanyFacade();
		Date date1 = Date.valueOf("2020-07-31");
		Date date2 = Date.valueOf("2022-10-22");
		Coupon coupon = new Coupon(1, 1, Category.VACATION, "aaa", "vvv", date1, date2, 10, 100.0, "dsa");
		Coupon coupon2 = new Coupon(2, 1, Category.ELECTRICITY, "rrrr", "yyyy", date1, date2, 5, 200, "asd");
		companyFacade.setCompanyID(1);
		System.out.println("Add coupon");
		try {
			companyFacade.addCoupon(coupon);
			companyFacade.addCoupon(coupon2);
		} catch (AlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();

		System.out.println("Update coupon");
		coupon.setAmount(20);
		try {
			companyFacade.updateCoupon(coupon);
		} catch (unableToUpdateException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();

		System.out.println("Get company's coupons");
		System.out.println(companyFacade.getCompanyCoupons());
		System.out.println();

		System.out.println("Get company's coupons by category");
		System.out.println(companyFacade.getCompanyCoupons(Category.VACATION));
		System.out.println();

		System.out.println("Get company's coupons by max price");
		System.out.println(companyFacade.getCompanyCoupons(100));
		System.out.println();

		System.out.println("Get company's details");
		System.out.println(companyFacade.getCompanyDetails());

		System.out.println("Delete coupon");
		companyFacade.deleteCoupon(coupon);
		companyFacade.deleteCoupon(coupon2);

	}

}
