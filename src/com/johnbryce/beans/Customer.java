package com.johnbryce.beans;

import java.util.List;

public class Customer {
	private int id;
	private String firstName;
	private String lastname;
	private String email;
	private String password;
	private List<Coupon> coupons;

	public Customer(int id, String firstName, String lastname, String email, String password, List<Coupon> coupons) {
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", password=" + password
				+ ", coupons=" + coupons + "]";
	}

}
