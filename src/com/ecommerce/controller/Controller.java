package com.ecommerce.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ecommerce.connection.BetterConnectionManager;
import com.ecommerce.model.Customer;






public class Controller {
	
	
	public static void writeToCharFile(File file, Customer customer) {

		try {
			    FileWriter fileWriter = new FileWriter(file, true); // boolean para is to append to File and not overwrite
				BufferedWriter buffWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriter = new PrintWriter(buffWriter) ;
				
				printWriter.println();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	           
	}
	
	public static void readFromCharFile(File file) {
		
		try (FileReader fileReader = new FileReader(file); 
				BufferedReader reader = new BufferedReader(fileReader)) {

			String line;
			// while there are more lines
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void writeObjToFile(File file, Customer customer) {

		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file))) {

			writer.writeObject(customer);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public static Customer readObjFromFile(File file) {

		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
			 Customer customer = (Customer) reader.readObject();
    		 System.out.println("first object " + customer);
		   
    		 return customer;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
//	Connection connection=BetterConnectionManager.getConnection();
//public boolean createAccount(Customer customer) {
//		
//		try {
//			PreparedStatement pstmt=connection.prepareStatement("insert into customers values (?,?)");
//		
//			pstmt.setString(1, customer.getUserName());
//			pstmt.setString(2, customer.getPassword());
//			
//			
//			int result=pstmt.executeUpdate();
//			if (result>0) {
//				return true;
//			  }
//			pstmt.close();
//		    } catch (SQLException e) {
//			e.printStackTrace();
//			
//		    }
//		return false;
//   }
//
//public Customer loginCustomer(String userName,String password) {
//	
//	Customer cust=null;
//	
//	try(PreparedStatement pstmt=connection.prepareStatement("select * from customers where userId = ? and customerPassword = ?")) {
//		
//		pstmt.setString(1, userName);
//		pstmt.setString(2, password);
//		
//		ResultSet rs=pstmt.executeQuery();
//		
//		if (rs.next()) {
//			   String usrName=rs.getString(1);
//			   String psword=rs.getString(2);
//			  
//			   cust=new Customer(usrName, psword);
//
//		}
//		
//	} catch (Exception e) {
//	
//	}
//	return cust;	
//}

	

	
	
	
	
}

