package com.qa.run;

import java.util.Scanner;

import com.qa.access.AccessCustomer;
import com.qa.access.AccessItem;
import com.qa.data.CustomerData;
import com.qa.data.ItemData;

public class Selection {
	
	Scanner sc = new Scanner(System.in);
	
// Choosing the table to access:
	public void table() {
				System.out.println("Please enter the number of the table you want to access:\n "
				+ "1 - Customers\n "
				+ "2 - Items\n "
				+ "3 - Orders"); 
		int table = sc.nextInt();
		System.out.println();
		sc.nextLine();
		switch (table) {
		case 1:
			customerCRUD();
			break;
		case 2:
			itemCRUD();
			break;
		case 3:
			orderCRUD();
			break;
		default:
			System.out.println("Please enter 1, 2 or 3...");
		}
	}
	
// Choosing customer CRUD function to do and calling it:
	public void customerCRUD() {
		CustomerData custIn = new CustomerData();
//		custIn.setCustomerID(0);
		System.out.println("Please enter:\n "
				+ "1 - To create a new customer\n "
				+ "2 - To view all existing customers\n "
				+ "3 - To update an existing customers firstname\n " 
				+ "4 - To update an existing customers surname\n " 
				+ "5 - To update an existing customers email\n " 
				+ "6 - To delete a customer record from the system");
		int custCrud = sc.nextInt();
		System.out.println();
		sc.nextLine();
		switch (custCrud) {
		case 1:
			System.out.println("Please enter your firstname: ");
			custIn.setFirstname(sc.nextLine());
			System.out.println("\nPlease enter your surname: ");
			custIn.setSurname(sc.nextLine());
			System.out.println("\nPlease enter your email: ");
			custIn.setEmail(sc.nextLine());
			System.out.println("\nPlease enter your mobile: ");
			custIn.setMobile(sc.nextLine());
			System.out.println("\nPlease enter your address: ");
			custIn.setAddress(sc.nextLine());
			System.out.println("\nPlease enter your payment method(i.e. visa, debit): ");
			custIn.setCardType(sc.nextLine());
			System.out.println("\nPlease enter your 16 digit card number: ");
			custIn.setCardNo(sc.nextLine());
			System.out.println("\nPlease enter your card expiry month in digits: ");
			custIn.setExpiryMonth(sc.nextInt());
			System.out.println("\nPlease enter your card expiry year: ");
			custIn.setExpiryYear(sc.nextInt());
			System.out.println("\nPlease enter the CVC number on your card: ");
			custIn.setCardCVC(sc.nextInt());
			new AccessCustomer().createC(custIn);
			break;
		case 2:
			new AccessCustomer().readAllC();
			break;
		case 3:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new firstname: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setFirstname(sc.nextLine());
			new AccessCustomer().update1stName(custIn);
			break;
		case 4:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new surname: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setSurname(sc.nextLine());
			new AccessCustomer().update2ndName(custIn);
			break;
		case 5:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new email: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setEmail(sc.nextLine());
			new AccessCustomer().updateEmail(custIn);
			break;
		case 6:
			System.out.println("Please enter customer ID of record to be deleted: ");
			custIn.setCustomerID(sc.nextInt());
			new AccessCustomer().deleteC(custIn);
			break;
		}
	}
	
// Choosing item CRUD function to do and calling it:
	public void itemCRUD() {
		ItemData itemIn = new ItemData();
//		itemIn.setItemID(0);
		System.out.println("Please enter:\n "
				+ "1 - To create a new item\n "
				+ "2 - To view all existing items\n "
				+ "3 - To update existing items name\n " 
				+ "4 - To update existing items price\n " 
				+ "5 - To update existing items stock quantity\n " 
				+ "6 - To delete an items record from the system");
		int itemCrud = sc.nextInt();
		System.out.println();
		sc.nextLine();
		switch (itemCrud) {
		case 1:
			System.out.println("Please enter the items name: ");
			itemIn.setItemName(sc.nextLine());
			System.out.println("\nPlease enter the items price: ");
			itemIn.setPrice(sc.nextFloat());
			System.out.println("\nPlease enter the amount of stock: ");
			itemIn.setStock(sc.nextInt());
			new AccessItem().createI(itemIn);
			break;
		case 2:
			new AccessItem().readAlli();
			break;
		case 3:
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(sc.nextInt());
			System.out.println("\nPlease enter the new item name: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			itemIn.setItemName(sc.nextLine());
			new AccessItem().updateName(itemIn);
			break;
		case 4:
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(sc.nextInt());
			System.out.println("\nPlease enter the new item price: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			itemIn.setPrice(sc.nextFloat());
			new AccessItem().updatePrice(itemIn);
			break;
		case 5:
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(sc.nextInt());
			System.out.println("\nPlease enter the new item stock quantity: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			itemIn.setStock(sc.nextInt());
			new AccessItem().updateStock(itemIn);
			break;
		case 6:
			System.out.println("Please enter item ID of record to be deleted: ");
			itemIn.setItemID(sc.nextInt());
			new AccessItem().deleteI(itemIn);
			break;
		}
	}
	
// Choosing order CRUD function to do and calling it:
	public void orderCRUD() {
		
	}
	
}
