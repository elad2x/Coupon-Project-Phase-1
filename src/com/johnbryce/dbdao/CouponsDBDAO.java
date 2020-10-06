package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.beans.Category;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.CustomerVsCoupon;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.utils.ConnectionPool;

public class CouponsDBDAO implements CouponsDAO {

	private static final String QUERY_INSERT = "INSERT INTO `cs125`.`coupons` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String QUERY_UPDATE = "UPDATE `cs125`.`coupons` SET `company_id` = ?, `category_id` = ?, `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);";
	private static final String QUERY_DELETE = "DELETE FROM `cs125`.`coupons` WHERE (`id` = ?);";
	private static final String QUERY_DELETE_V2 = "DELETE FROM `cs125`.`coupons` WHERE (`company_id` = ?);";
	private static final String QUERY_GET_ALL = "SELECT * FROM `cs125`.`coupons`";
	private static final String QUERY_INSERT_COUPON_PURCHASE = "INSERT INTO `cs125`.`customers_vs_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?);";
	private static final String QUERY_GET_ALL_COUPON_PURCHASE = "SELECT * FROM `cs125`.`customers_vs_coupons`";
	private static final String QUERY_DELETE_COUPON_PURCHASE = "DELETE FROM `cs125`.`customers_vs_coupons` WHERE (`customer_id` = ?) AND (`coupon_id` = ?);";
	private static final String QUERY_DELETE_COUPON_PURCHASE_V2 = "DELETE FROM `cs125`.`customers_vs_coupons` WHERE (`coupon_id` = ?);";
	private static final String QUERY_DELETE_COUPON_PURCHASE_V3 = "DELETE FROM `cs125`.`customers_vs_coupons` WHERE (`customer_id` = ?);";

	@Override
	public void addCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setInt(1, coupon.getCompanyID());
			statement.setObject(2, coupon.getCategory().ordinal());
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, coupon.getStartDate());
			statement.setDate(6, coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void updateCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE);
			statement.setInt(1, coupon.getCompanyID());
			statement.setObject(2, coupon.getCategory().ordinal());
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, (Date) coupon.getStartDate());
			statement.setDate(6, (Date) coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.setInt(10, coupon.getId());
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCoupon(int couponID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, couponID);
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCouponV2(int companyID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_V2);
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
	public List<Coupon> getAllCoupons() {
		List<Coupon> results = new ArrayList<>();
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL);

			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				coupon.setCompanyID(resultSet.getInt(2));
				coupon.setCategory2(resultSet.getInt(3));
				coupon.setTitle(resultSet.getString(4));
				coupon.setDescription(resultSet.getString(5));
				coupon.setStartDate(resultSet.getDate(6));
				coupon.setEndDate(resultSet.getDate(7));
				coupon.setAmount(resultSet.getInt(8));
				coupon.setPrice(resultSet.getDouble(9));
				coupon.setImage(resultSet.getString(10));
				results.add(coupon);
			}

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

		return results;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT_COUPON_PURCHASE);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_COUPON_PURCHASE);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCouponPurchaseV2(int couponID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_COUPON_PURCHASE_V2);
			statement.setInt(1, couponID);
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCouponPurchaseV3(int customerID) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_COUPON_PURCHASE_V3);
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
	public List<CustomerVsCoupon> getAllCouponPurchase() {
		List<CustomerVsCoupon> results = new ArrayList<>();
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = ConnectionPool.getInstance().getConnection();
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL_COUPON_PURCHASE);

			// STEP 4 - Optional only for results
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CustomerVsCoupon customerVsCoupon = new CustomerVsCoupon();
				customerVsCoupon.setCustomerID(resultSet.getInt(1));
				customerVsCoupon.setCouponID(resultSet.getInt(2));
				results.add(customerVsCoupon);
			}

		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		// STEP 5 - Close
		ConnectionPool.getInstance().returnConnection(conn);

		return results;
	}

}
