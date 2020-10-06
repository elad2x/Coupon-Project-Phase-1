package com.johnbryce;

import com.johnbryce.db.DataBaseManager;
import com.johnbryce.facade.AdministratorFacade;
import com.johnbryce.facade.CompanyFacade;
import com.johnbryce.facade.CustomerFacade;
import com.johnbryce.tests.facade.AdministratorFacadeTesting;
import com.johnbryce.tests.facade.CompanyFacadeTesting;
import com.johnbryce.tests.facade.CustomerFacadeTesting;
import com.johnbryce.tests.login.LoginManagerTesting;
import com.johnbryce.utils.ConnectionPool;

public class Test {

	public static void TestAll() {
		System.out.println("START");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DataBaseManager.dropAndBuildTables();
			CouponExpirationDailyJob dailyJob = new CouponExpirationDailyJob();
			Thread t1 = new Thread(dailyJob);
			t1.start();
			AdministratorFacade admin = LoginManagerTesting.adminLog();
			if (admin != null) {
				AdministratorFacadeTesting admin1 = new AdministratorFacadeTesting();
				admin1.test();
			}
			CompanyFacade company = LoginManagerTesting.companyLog();
			if (company != null) {
				CompanyFacadeTesting company1 = new CompanyFacadeTesting();
				company1.test();
			}
			CustomerFacade customer = LoginManagerTesting.customerLog();
			if (customer != null) {
				CustomerFacadeTesting customer1 = new CustomerFacadeTesting();
				customer1.test();
			}

			dailyJob.stop();
			ConnectionPool.getInstance().closeAllConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("END");

	}

}
