package com.johnbryce.facade;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.beans.CustomerVsCoupon;
import com.johnbryce.exception.AlreadyExistException;
import com.johnbryce.exception.NotExistException;

public class CustomerFacade extends ClientFacade {

	private int customerID;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public boolean login(String email, String password) {
		return this.customersDAO.isCustomerExists(email, password);
	}

	public void purchaseCoupon(Coupon coupon) throws NotExistException, AlreadyExistException {
		List<CustomerVsCoupon> customerVsCoupons = couponsDAO.getAllCouponPurchase();
		for (CustomerVsCoupon cusVsCoup : customerVsCoupons) {
			if (cusVsCoup.getCustomerID() == customerID && cusVsCoup.getCouponID() == coupon.getId()) {
				throw new AlreadyExistException("Can not purchase the same coupon more than once");
			}
		}
		Date currentDate = new Date(System.currentTimeMillis());
		if (coupon.getAmount() < 0) {
			throw new NotExistException("This coupns is currently unavailable");
		}
		if (coupon.getEndDate().before(currentDate)) {
			throw new NotExistException("The coupon is expired");
		}

		this.couponsDAO.addCouponPurchase(customerID, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);
		this.couponsDAO.updateCoupon(coupon);

	}

	public List<Coupon> getCustomerCoupons() {
		List<Coupon> coupons = this.couponsDAO.getAllCoupons();
		List<Coupon> customerCouponsResult = new ArrayList<>();
		List<CustomerVsCoupon> customerVsCoupons = this.couponsDAO.getAllCouponPurchase();
		List<Integer> coupIdList = new ArrayList<>();
		for (int i = 0; i < customerVsCoupons.size(); i++) {
			if (customerVsCoupons.get(i).getCustomerID() == customerID) {
				coupIdList.add(customerVsCoupons.get(i).getCouponID());
			}
		}
		for (int i = 0; i < coupIdList.size(); i++) {
			for (int j = 0; j < coupons.size(); j++) {
				if (coupIdList.get(i) == coupons.get(j).getId()) {
					customerCouponsResult.add(coupons.get(j));
				}
			}
		}
		return customerCouponsResult;
	}

	public ArrayList<Coupon> getCustomerCoupons(Category category) {
		List<Coupon> coupons = getCustomerCoupons();
		List<Coupon> customerCouponsResult = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCategory().equals(category)) {
				customerCouponsResult.add(coup);
			}
		}

		return (ArrayList<Coupon>) customerCouponsResult;
	}

	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> coupons = getCustomerCoupons();
		List<Coupon> customerCouponsResult = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getPrice() <= maxPrice) {
				customerCouponsResult.add(coup);
			}
		}
		return (ArrayList<Coupon>) customerCouponsResult;
	}

	public Customer getCustomerDetails() {
		return this.customersDAO.getOneCustomer(customerID);

	}

	public int getIdFromDB(String email, String password) {
		return this.customersDAO.getCustomerId(email,password);
	}

}
