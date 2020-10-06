package com.johnbryce.dao;

import java.util.List;

import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.CustomerVsCoupon;

public interface CouponsDAO {
	void addCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	void deleteCoupon(int couponID);
	
	void deleteCouponV2(int companyID);

	List<Coupon> getAllCoupons();

	void addCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchaseV2(int couponID);
	
	void deleteCouponPurchaseV3(int customerID);
	
	List<CustomerVsCoupon> getAllCouponPurchase();

}
