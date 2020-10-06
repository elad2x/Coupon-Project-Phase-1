package com.johnbryce;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Coupon;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.exception.NotExistException;
import com.johnbryce.facade.CompanyFacade;

public class CouponExpirationDailyJob implements Runnable {
	private CouponsDAO couponsDAO;
	private boolean quit;

	public CouponExpirationDailyJob(CouponsDAO couponsDAO) {
		this.couponsDAO = couponsDAO;
		this.quit = false;
	}

	public CouponExpirationDailyJob() {
	}

	public boolean isQuit() {
		return quit;
	}

	@Override
	public void run() {
		while (!quit) {
			List<Coupon>coupons = couponsDAO.getAllCoupons();
			for (Coupon coupon :coupons) {
				if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
					CompanyFacade companyFacade = new CompanyFacade();
					companyFacade.deleteCoupon(coupon);
				}
			}
			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		this.quit = !quit;
	}

}
