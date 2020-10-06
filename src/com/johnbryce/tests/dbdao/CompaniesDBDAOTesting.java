package com.johnbryce.tests.dbdao;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.dbdao.CompaniesDBDAO;

public class CompaniesDBDAOTesting {

	public static void main(String[] args) {
		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		List<Coupon> coupons = new ArrayList<Coupon>();
		Company company1 = new Company(1,"Bezek", "bezek@gmail.com", "123", coupons);

		System.out.println("Add companies...");
		companiesDAO.addCompany(company1);
		
		System.out.println("Company is exist");
		System.out.println(companiesDAO.isCompanyExists("bezek@gmail.com", "123"));

		System.out.println("Company is not exist");
		System.out.println(companiesDAO.isCompanyExists("Hot@gmail.com", "345"));

		System.out.println("Update companies...");
		company1.setName("moshe");
		System.out.println(company1.getName());
		companiesDAO.updateCompany(company1);

		System.out.println("Get one company...");
		System.out.println(companiesDAO.getOneCompany(company1.getId()));

		System.out.println("Get all companies before delete...");
		System.out.println(companiesDAO.getAllCompanies());

		System.out.println("Delete companies...");
		companiesDAO.deleteCompany(company1.getId());

		System.out.println("Get all companies after delete...");
		System.out.println(companiesDAO.getAllCompanies());

	}

}
