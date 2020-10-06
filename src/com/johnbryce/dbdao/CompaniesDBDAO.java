package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.johnbryce.beans.Company;
import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.utils.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {

	private static final String QUERY_IS_EXIST = "SELECT * FROM cs125.companies WHERE (`email` = ?) AND (`password` = ?);";
	private static final String QUERY_INSERT = "INSERT INTO `cs125`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);";
	private static final String QUERY_UPDATE = "UPDATE `cs125`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
	private static final String QUERY_DELETE = "DELETE FROM `cs125`.`companies` WHERE (`id` = ?);";
	private static final String QUERY_GET_ONE = "SELECT * FROM `cs125`.`companies`WHERE (`id` = ?)";
	private static final String QUERY_GET_ALL = "SELECT * FROM `cs125`.`companies`";

	@Override
	public boolean isCompanyExists(String email, String password) {
		List<Company> results = new ArrayList<>();
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_EXIST);
			statement.setString(1, email);
			statement.setString(2, password);
			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getInt(1));
				company.setName(resultSet.getString(2));
				company.setEmail(resultSet.getString(3));
				company.setPassword(resultSet.getString(4));
				results.add(company);
			}

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);
		if (results.size() >= 1) {
			return true;
		}

		return false;

	}

	@Override
	public void addCompany(Company company) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void updateCompany(Company company) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, company.getId());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCompany(int companyID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, companyID);
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> results = new ArrayList<>();
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);

			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getInt(1));
				company.setName(resultSet.getString(2));
				company.setEmail(resultSet.getString(3));
				company.setPassword(resultSet.getString(4));
				results.add(company);
			}

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

		return results;
	}

	@Override
	public Company getOneCompany(int companyID) {
		Company result = null;
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
			statement.setInt(1, companyID);
			statement.executeQuery();
			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			Company company = new Company();
			company.setId(resultSet.getInt(1));
			company.setName(resultSet.getString(2));
			company.setEmail(resultSet.getString(3));
			company.setPassword(resultSet.getString(4));
			result = company;

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);
		return result;
	}

	@Override
	public int getCompanyID(String email, String password) {
		List<Company> results = new ArrayList<>();
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_EXIST);
			statement.setString(1, email);
			statement.setString(2, password);
			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getInt(1));
				company.setName(resultSet.getString(2));
				company.setEmail(resultSet.getString(3));
				company.setPassword(resultSet.getString(4));
				results.add(company);
			}

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);
		if (results.size() == 1) {
			return results.get(0).getId();
		}
		return 0;
	}

}
