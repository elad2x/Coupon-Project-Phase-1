package com.johnbryce.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManager {
	private static final String URL = "jdbc:mysql://localhost:3306/cats?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "123456";

	private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `cs125`.`companies` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `cs125`.`customers` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `first_name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `last_name` VARCHAR(45) NOT NULL,\r\n" + "  `email` VARCHAR(45) NOT NULL,\r\n"
			+ "  `password` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `cs125`.`categories` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";
	private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `cs125`.`coupons` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `company_id` INT NOT NULL,\r\n"
			+ "  `category_id` INT NOT NULL,\r\n" + "  `title` VARCHAR(45) NOT NULL,\r\n"
			+ "  `description` VARCHAR(45) NOT NULL,\r\n" + "  `start_date` DATE NOT NULL,\r\n"
			+ "  `end_date` DATE NOT NULL,\r\n" + "  `amount` INT NOT NULL,\r\n" + "  `price` DOUBLE NOT NULL,\r\n"
			+ "  `image` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`),\r\n"
			+ "  INDEX `companty_id_idx` (`company_id` ASC) VISIBLE,\r\n"
			+ "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `companty_id`\r\n"
			+ "    FOREIGN KEY (`company_id`)\r\n" + "    REFERENCES `cs125`.`companies` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `category_id`\r\n"
			+ "    FOREIGN KEY (`category_id`)\r\n" + "    REFERENCES `cs125`.`categories` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";
	private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `cs125`.`customers_vs_coupons` (\r\n"
			+ "  `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n"
			+ "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `customer_id`\r\n"
			+ "    FOREIGN KEY (`customer_id`)\r\n" + "    REFERENCES `cs125`.`customers` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `coupon_id`\r\n"
			+ "    FOREIGN KEY (`coupon_id`)\r\n" + "    REFERENCES `cs125`.`coupons` (`id`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";

	private static final String DROP_TABLE_COMPANIES = "DROP TABLE `cs125`.`companies`";
	private static final String DROP_TABLE_CUSTOMERS = "DROP TABLE `cs125`.`customers`";
	private static final String DROP_TABLE_CATEGORIES = "DROP TABLE `cs125`.`categories`";
	private static final String DROP_TABLE_COUPONS = "DROP TABLE `cs125`.`coupons`";
	private static final String DROP_TABLE_CUSTOMERS_VS_COUPONS = "DROP TABLE `cs125`.`customers_vs_coupons`";
	
	private static final String CATEGORY_FOOD = "INSERT INTO `cs125`.`categories` (name) VALUES ('Food')";
	private static final String CATEGORY_ELECTRICITY = "INSERT INTO `cs125`.`categories` (name) VALUES ('Electticity')";
	private static final String CATEGORY_RESTURANT = "INSERT INTO `cs125`.`categories` (name) VALUES ('Resturant')";
	private static final String CATEGORY_VACATION = "INSERT INTO `cs125`.`categories` (name) VALUES ('Vacation')";

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPass() {
		return PASS;
	}

	public static void runQuery(String sql) {
		Connection conn = null;
		try {
			// STEP 2 - Open connection
			conn = DriverManager.getConnection(DataBaseManager.getUrl(), DataBaseManager.getUser(),
					DataBaseManager.getPass());
			// STEP 3 - Run script
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			// STEP 4 - Optional only for results
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5 - Close
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void dropAndBuildTables() {
		runQuery(DROP_TABLE_CUSTOMERS_VS_COUPONS);
		runQuery(DROP_TABLE_COUPONS);
		runQuery(DROP_TABLE_CATEGORIES);
		runQuery(DROP_TABLE_CUSTOMERS);
		runQuery(DROP_TABLE_COMPANIES);
		runQuery(CREATE_TABLE_COMPANIES);
		runQuery(CREATE_TABLE_CUSTOMERS);
		runQuery(CREATE_TABLE_CATEGORIES);
		runQuery(CREATE_TABLE_COUPONS);
		runQuery(CREATE_TABLE_CUSTOMERS_VS_COUPONS);
		runQuery(CATEGORY_FOOD);
		runQuery(CATEGORY_ELECTRICITY);
		runQuery(CATEGORY_RESTURANT);
		runQuery(CATEGORY_VACATION);

	}

}
