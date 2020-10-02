package com.ecommerce.model;

import java.io.Serializable;

public class Customer implements Serializable{
	
	String userName;
	String password;
	
	public Customer() {
		this("N/A", "N/A");
	}
	
	public Customer(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [userName=" + userName + ", password=" + password + "]";
	}
	
	
	

}
