package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.johnbryce.beans.Customer;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.utils.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO {

	private static final String QUERY_IS_EXIST = "SELECT * FROM cs125.customers WHERE (`email` = ?) AND (`password` = ?);";
	private static final String QUERY_INSERT = "INSERT INTO `cs125`.`customers` (`first_name`, `last_name`, `email`, `password`) VALUES (?, ?, ?, ?);";
	private static final String QUERY_UPDATE = "UPDATE `cs125`.`customers` SET `first_name` = ?, `last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
	private static final String QUERY_DELETE = "DELETE FROM `cs125`.`customers` WHERE (`id` = ?);";
	private static final String QUERY_GET_ONE = "SELECT * FROM `cs125`.`customers`WHERE (`id` = ?)";
	private static final String QUERY_GET_ALL = "SELECT * FROM `cs125`.`customers`";

	@Override
	public boolean isCustomerExists(String email, String password) {
		List<Customer> results = new ArrayList<>();
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
				Customer company = new Customer();
				company.setId(resultSet.getInt(1));
				company.setFirstName(resultSet.getString(2));
				company.setLastname(resultSet.getString(3));
				company.setPassword(resultSet.getString(4));
				results.add(company);
			}

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);
		if (results.size() == 1) {
			return true;
		}

		return false;
	}

	@Override
	public void addCustomer(Customer customer) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastname());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void updateCustomer(Customer customer) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastname());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customer.getId());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCustomer(int customerID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, customerID);
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> results = new ArrayList<>();
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);

			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				customer.setFirstName(resultSet.getString(2));
				customer.setLastname(resultSet.getString(3));
				customer.setPassword(resultSet.getString(4));
				customer.setEmail(resultSet.getString(5));
				results.add(customer);
			}

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

		return results;
	}

	@Override
	public Customer getOneCustomer(int customerID) {
		Customer result = null;
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE);
			statement.setInt(1, customerID);
			statement.executeQuery();
			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			Customer customer = new Customer();
			customer.setId(resultSet.getInt(1));
			customer.setFirstName(resultSet.getString(2));
			customer.setLastname(resultSet.getString(3));
			customer.setPassword(resultSet.getString(4));
			customer.setEmail(resultSet.getString(5));
			result = customer;

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);
		return result;
	}

	@Override
	public int getCustomerId(String email, String password) {
		List<Customer> results = new ArrayList<>();
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
				Customer company = new Customer();
				company.setId(resultSet.getInt(1));
				company.setFirstName(resultSet.getString(2));
				company.setLastname(resultSet.getString(3));
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
