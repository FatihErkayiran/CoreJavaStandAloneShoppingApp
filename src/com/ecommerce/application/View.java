package com.ecommerce.application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ecommerce.controller.Controller;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Item;

public class View {
	
	
	
	public static void main(String[] args) {
		
		Controller controller =new Controller();
		int count=0;
		int counter=1;
		//File file =new File("resources/"+ counter + ".data");
		List<Item>items=new ArrayList<>();
		System.out.println("items " +items);
		boolean state=true;
	while(state) {
		try {
			
			System.out.println("ECommerce App Welcomes You ");
			Scanner scanner =new Scanner(System.in);
			System.out.println("1.Register\n2.Login");
			int choice=scanner.nextInt();
			scanner.nextLine();
				if (choice==1) {
					boolean case1=true;
				//  while(case1)
					System.out.println("Enter Details for the Account");
					System.out.println("User Name");
					String userName=scanner.nextLine();
					System.out.println("Password");
					String password=scanner.nextLine();
	                System.out.println("Confirm Password");
	                String conPassword=scanner.nextLine();
	//                if (password.equals(conPassword)) {
	//                	System.out.println("Passwords do not match");
	//                	case1=false;
	//				     }
//	                try {
//	    				file.createNewFile();
//	    			} catch (IOException e) {
//	    				// TODO Auto-generated catch block
//	    				e.printStackTrace();
//	    			}
	                
				Customer customer=new Customer(userName, conPassword);
			    
	//			Controller.writeObjToFile(file, customer);
				controller.createAccount(customer);
	//			counter++;
				}
			
				if (choice==2) {
				System.out.println(" Enter Login Details");
				System.out.println(" User ID");
				String userName=scanner.nextLine();
				System.out.println(" Password ");
				String password=scanner.nextLine();
	//			Customer cust=Controller.readObjFromFile(file);
				
			    Customer cust= controller.loginCustomer(userName, password);
				 System.out.println(cust);
				 if (cust!=null) {
						System.out.println(" Welcome " + cust.getUserName());
						System.out.println("3.Buy An Item\n4.Replace An Item\n5.Exit");
						int choiceAgain=scanner.nextInt();
					if (choiceAgain==3) {
						boolean state2=true;
						while(state2) {
						System.out.println("Items  Items Code   Price");
						System.out.println("1.Jacket Ja1  20$\n2.Jeans Je1  10$\n3.Shirt Se1 5$\n4.Check Out\n5.Sign Out");
						int itemChoice=scanner.nextInt();
						scanner.nextLine();
						 if (itemChoice==1) {
							Item jacket=new Item("Jacket", "Ja1", "20$");
							items.add(jacket);
						  }
						 else if(itemChoice==2) {
							 Item jeans=new Item("Jeans", "Je1", "10$");
							 items.add(jeans);
						  }
						 else if(itemChoice==3) {
							 Item shirt=new Item("Shirt", "Se1", "5$");
							 items.add(shirt);
						 }
						 else if(itemChoice==4) {
							 LocalDate time=LocalDate.now();
							 int invoiceNumber=(int) ((Math.random())*100);
							 System.out.println("Customer Name: " + cust.getUserName() + "\nTime: "+ LocalDateTime.now()+
									 "\nInvoice Number: " + invoiceNumber);
							 
							 for (int i = 0; i < items.size(); i++) {
								 System.out.println(items.get(i) + "\n");
								 String newPrice=items.get(i).getPrice().replace("$", "");
								 count+=Integer.parseInt(newPrice);
							}							 
							 System.out.println("Total = " + count + "$");
							 
								 controller.saveTransactions(cust, invoiceNumber, time);
							
						//	 System.out.println("Total = " + count);
							 
							 System.out.println("Thank you for shopping keep your invoce number for replacement");
							 state2=false;
							 state=false;
							
						 }
						 else if(itemChoice==5) {
							 System.out.println("see you again");
							 state2=false;
							 state=false;
						 }
						 else {
							System.out.println("choose numbers 1 thru 3");
						}
					}
				}
					
						
						
						else if(choiceAgain==4) {
							System.out.println("Please enter your invoice number");
							int num=scanner.nextInt();
							scanner.nextLine();
							System.out.println("Please enter today's date in the format of 'day/month/year'");
							String dateString=scanner.nextLine();
							boolean checkIfInvoiceEligible=controller.checkInvoice(cust, num, dateString);
							if (checkIfInvoiceEligible) {
								System.out.println("You can get your refund !!");
								controller.deleteTransactionAfterRefund(num);
							}
							else {
								System.out.println("I'm sorry more than 15 days has passed");
							}
							
							
						}		
						else if(choiceAgain==5) {
							System.out.println("bye bye");
							state=false;
						}
						
		}
				    
				    else {
				    	System.out.println("wrong password try again");
				    }
			}
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
		
	}	
		
	}

}
