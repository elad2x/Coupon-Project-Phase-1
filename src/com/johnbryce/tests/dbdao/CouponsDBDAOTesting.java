package com.johnbryce.tests.dbdao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dbdao.CouponsDBDAO;

public class CouponsDBDAOTesting {

	public static void main(String[] args) {
		CouponsDAO couponsDAO = new CouponsDBDAO();
		Date date1 = Date.valueOf("2020-07-31");
		Date date2 = Date.valueOf("2022-10-22");
		List<Coupon> coupons = new ArrayList<Coupon>();
		Coupon coupon1 = new Coupon(1, 1, Category.VACATION, "aaa", "vvv", date1, date2, 10, 100.0, "dsa");
		System.out.println("Add coupon...");
		couponsDAO.addCoupon(coupon1);
		System.out.println();
		System.out.println("Update coupon");
		coupon1.setPrice(200);
		couponsDAO.updateCoupon(coupon1);
		System.out.println();
		System.out.println("Get all coupons");
		System.out.println(couponsDAO.getAllCoupons());
		System.out.println();
		System.out.println("Add coupon purchase");
		Customer customer1 = new Customer(1, "Elad", "Nissim", "elad@gmail.com", "123", coupons);
		couponsDAO.addCouponPurchase(customer1.getId(), coupon1.getId());
		System.out.println();
		System.out.println("Get all coupons purchase");
		System.out.println(couponsDAO.getAllCouponPurchase());
		System.out.println();
//		System.out.println("Delete coupon purchase");
//		couponsDAO.deleteCouponPurchase(customer1.getId(), coupon1.getId());
//		System.out.println();
//		System.out.println("Delete coupon");
//		couponsDAO.deleteCoupon(coupon1.getId());
	}

}
