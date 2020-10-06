package com.ecommerce.controller;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.ecommerce.connection.BetterConnectionManager;
import com.ecommerce.model.Customer;







public class Controller {
	
	
	
//	
//	public static void writeObjToFile(File file, Customer customer) {
//
//		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {
//
//			writer.writeObject(customer);
//			
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//    
//	public static Customer readObjFromFile(File file) {
//
//		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
//			 Customer customer = (Customer) reader.readObject();
//    		 System.out.println("first object " + customer);
//		   
//    		 return customer;
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//		
//	}

	

	
	
	Connection connection=BetterConnectionManager.getConnection();
public boolean createAccount(Customer customer) {
		
		try {
			PreparedStatement pstmt=connection.prepareStatement("insert into customers values (?,?)");
		
			pstmt.setString(1, customer.getUserName());
			pstmt.setString(2, customer.getPassword());
			
			
			int result=pstmt.executeUpdate();
			if (result>0) {
				return true;
			  }
			pstmt.close();
		    } catch (SQLException e) {
			e.printStackTrace();
			
		    }
		return false;
   }

public Customer loginCustomer(String userName,String password) {
	
	Customer cust=null;
	
	try(PreparedStatement pstmt=connection.prepareStatement("select * from customers where userName = ? and password = ?")) {
		
		pstmt.setString(1, userName);
		pstmt.setString(2, password);
		
		ResultSet rs=pstmt.executeQuery();
		
		if (rs.next()) {
			   String usrName=rs.getString(1);
			   String psword=rs.getString(2);
			  
			   cust=new Customer(usrName, psword);

		}
		
	} catch (Exception e) {
	
	}
	return cust;	
}

	
public void saveTransactions(Customer cust,int invoiceNumber,LocalDate time)  {
	java.sql.Date sqlDate = java.sql.Date.valueOf(time);
	try {
		PreparedStatement pStatement=connection.prepareStatement("insert into transactions values(?, ?, ?)");
		pStatement.setInt(1,invoiceNumber);
		pStatement.setDate(2, sqlDate);
		pStatement.setString(3, cust.getUserName());
		
		
		int result=pStatement.executeUpdate();
		if (result>0) {
			System.out.println("Transaction is approved ");
			
		}
		pStatement.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
	
	public boolean checkInvoice(Customer cust,int invoiceNumber,String returnTime) {
		try {
			PreparedStatement pStatement=connection.prepareStatement("select * from transactions where user_name = ? and invoiceNumber = ? ");
			pStatement.setString(1, cust.getUserName());
			pStatement.setInt(2, invoiceNumber);
			
			ResultSet rSet=pStatement.executeQuery();
			Date timeDate=Date.valueOf("2020-9-1");
			

			DateTimeFormatter formatter =DateTimeFormatter.ofPattern("MM/d/yyyy");
			LocalDate returnTime2=LocalDate.parse(returnTime,formatter);
			LocalDate start =timeDate.toLocalDate();
			LocalDate end = returnTime2;
			
			
			
			if (rSet.next()) {
				//String usrName=rSet.getString(3);
				timeDate=rSet.getDate(2);
			//	int number=rSet.getInt(1);
				
				if (Period.between(start, end).getDays() <= 15 && Period.between(start, end).getDays() > 0 ) {
					return true;
				}
			}
			else {
				System.out.println("there is no such invoice number");
				return false;
			}
	
			System.out.println(Period.between(start, end).getDays());
			
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteTransactionAfterRefund(int invoiceNumber) {
		
		try {
			PreparedStatement preparedStatement =connection.prepareStatement("delete from transactions where invoiceNumber = ?");
		
		    preparedStatement.setInt(1, invoiceNumber); 
		    
		    int number=preparedStatement.executeUpdate();
		    System.out.println("deleted number of rows: " + number);
		
		     preparedStatement.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

