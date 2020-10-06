package com.johnbryce.facade;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.CustomerVsCoupon;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.exception.AlreadyExistException;
import com.johnbryce.exception.unableToUpdateException;

public class CompanyFacade extends ClientFacade {

	private int companyID;

	public CompanyFacade() {
		super();
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public int getIdFromDB(String email, String password) {
		return this.companiesDAO.getCompanyID(email, password);
	}
	
	@Override
	public boolean login(String email, String password) {
		return this.companiesDAO.isCompanyExists(email, password);
	}

	public void addCoupon(Coupon coupon) throws AlreadyExistException {
		List<Coupon> coupons = getCompanyCoupons();
		for (Coupon coup : coupons) {
			if (coup.getTitle().equals(coupon.getTitle())) {
				throw new AlreadyExistException("Coupn title");
			}
		}

		this.couponsDAO.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) throws unableToUpdateException {
		List<Coupon> coupons = getCompanyCoupons();
		for (Coupon coup : coupons) {
			if (coupon.getId() == coup.getId() && coupon.getCompanyID() == coup.getCompanyID()) {
				this.couponsDAO.updateCoupon(coupon);
			} else {
				throw new unableToUpdateException("Coupon's id and coupon's company id can not be updated");
			}
		}
	}

	public void deleteCoupon(Coupon coupon) {
		List<CustomerVsCoupon> customerVscoupon = this.couponsDAO.getAllCouponPurchase();
		// the purpose of the loop is to check if the coupon purchase exist.
		for (CustomerVsCoupon cusVscoup : customerVscoupon) {
			if (cusVscoup.getCouponID() == coupon.getId()) {
				this.couponsDAO.deleteCouponPurchaseV2(coupon.getId());
			}
		}

		this.couponsDAO.deleteCoupon(coupon.getId());
	}

	public ArrayList<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = this.couponsDAO.getAllCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCompanyID() == this.companyID) {
				results.add(coup);
			}
		}

		return (ArrayList<Coupon>) results;

	}

	public ArrayList<Coupon> getCompanyCoupons(Category category) {
		List<Coupon> coupons = this.couponsDAO.getAllCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCompanyID() == this.companyID && coup.getCategory().equals(category)) {
				results.add(coup);
			}
		}
		return (ArrayList<Coupon>) results;

	}

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> coupons = this.couponsDAO.getAllCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCompanyID() == this.companyID && coup.getPrice() <= maxPrice) {
				results.add(coup);
			}
		}
		return (ArrayList<Coupon>) results;
	}

	public Company getCompanyDetails() {
		return this.companiesDAO.getOneCompany(companyID);
	}

}
