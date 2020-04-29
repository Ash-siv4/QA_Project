package com.qa.run;

import java.util.Scanner;

import com.qa.access.AccessCustomer;
import com.qa.access.AccessItem;
import com.qa.access.AccessOrder;
import com.qa.connect.Connect;
import com.qa.data.CustomerData;
import com.qa.data.ItemData;
import com.qa.data.OrderData;

public class Selection {
		
	Connect start = null;
	
//	Initialise connection to chosen database
	public Selection(Connect connection) {
		start = connection;
		start.call();
	}
	
		Scanner sc = new Scanner(System.in);
	
// Choosing the table to access:
	public void table() {
		boolean exit = false;
		do {
			exit = false;
			System.out.println("Please enter the number of the table you want to access:\n " + "1: Customers\n " 
					+ "2: Items\n "
					+ "3: Orders\n" 
					+ "ENTER 4 TO EXIT"); 
			int table = sc.nextInt();
			System.out.println();
			sc.nextLine();
			switch (table) {
			case 1:
				customerCRUD();
				exit = true;
				break;
			case 2:
				itemCRUD();
				exit = true;
				break;
			case 3:
				orderCRUD();
				exit = true;
				break;
			case 4:
				exit = false;
				break;
			default:
				exit = true;
				System.out.println("Please enter 1, 2, 3 or 4...\n");
			}
		}while((exit));
	}
	
//----------------------------------- CUSTOMER CRUD -----------------------------------------------
	
// Choosing customer CRUD function to do and calling it:
	public void customerCRUD() {
		CustomerData custIn = new CustomerData();
//		custIn.setCustomerID(0);
		System.out.println("Please enter:\n "
				+ "1: To CREATE a new customer\n "
				+ "2: To VIEW all existing customers\n "
				+ "3: To UPDATE an existing customers FIRSTNAME\n " 
				+ "4: To UPDATE an existing customers SURNAME\n " 
				+ "5: To UPDATE an existing customers EMAIL\n " 
				+ "6: To UPDATE an existing customers MOBILE\n " 
				+ "7: To UPDATE an existing customers ADDRESS\n " 
				+ "8: To UPDATE an existing customers CARD TYPE\n " 
				+ "9: To UPDATE an existing customers CARD NUMBER\n " 
				+ "10: To UPDATE an existing customers CARD EXPIRY MONTH\n " 
				+ "11: To UPDATE an existing customers CARD EXPIRY YEAR\n " 
				+ "12: To UPDATE an existing customers CARD CVC\n " 
				+ "13: To DELETE a customer record from the system");
		int custCrud = sc.nextInt();
		System.out.println();
		sc.nextLine();
		switch (custCrud) {
		case 1:
//			CUSTOMER CREATE FUNCTION
			System.out.println("Please enter your firstname: ");
			custIn.setFirstname(sc.nextLine());
			System.out.println("\nPlease enter your surname: ");
			custIn.setSurname(sc.nextLine());
			System.out.println("\nPlease enter your email: ");
			custIn.setEmail(sc.nextLine());
			System.out.println("\nPlease enter your mobile(11 digits): ");
			custIn.setMobile(sc.nextLine());
			System.out.println("\nPlease enter your address: ");
			custIn.setAddress(sc.nextLine());
			System.out.println("\nPlease enter your payment method(i.e. visa, debit): ");
			custIn.setCardType(sc.nextLine());
			System.out.println("\nPlease enter your 16 digit card number: ");
			custIn.setCardNo(sc.nextLine());
			System.out.println("\nPlease enter your card expiry month in digits(mm): ");
			custIn.setExpiryMonth(sc.nextInt());
			System.out.println("\nPlease enter your card expiry year(yyyy): ");
			custIn.setExpiryYear(sc.nextInt());
			System.out.println("\nPlease enter the CVC number on your card(3 digits): ");
			custIn.setCardCVC(sc.nextInt());
			new AccessCustomer(start).createC(custIn);
			break;
		case 2:
//			CUSTOMER READ FUNCTION
			new AccessCustomer(start).readAllC();
			break;
		case 3:
//			CUSTOMER UPDATE FUNCTION
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new firstname: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setFirstname(sc.nextLine());
			new AccessCustomer(start).update1stName(custIn);
			break;
		case 4:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new surname: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setSurname(sc.nextLine());
			new AccessCustomer(start).update2ndName(custIn);
			break;
		case 5:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new email: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setEmail(sc.nextLine());
			new AccessCustomer(start).updateEmail(custIn);
			break;
		case 6:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new mobile number(11 digits): ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setMobile(sc.nextLine());
			new AccessCustomer(start).updateMobile(custIn);
			break;
		case 7:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new address: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setAddress(sc.nextLine());
			new AccessCustomer(start).updateAddr(custIn);
			break;
		case 8:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new card type(i.e. visa, debit, mastercard): ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setCardType(sc.nextLine());
			new AccessCustomer(start).updateCtype(custIn);
			break;
		case 9:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new card number(16 digits): ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setCardNo(sc.nextLine());
			new AccessCustomer(start).updateCno(custIn);
			break;
		case 10:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new card expiry month in digits(mm): ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setExpiryMonth(sc.nextInt());
			new AccessCustomer(start).updateExpM(custIn);
			break;
		case 11:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new card expiry year(yyyy): ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setExpiryYear(sc.nextInt());
			new AccessCustomer(start).updateExpY(custIn);
			break;
		case 12:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(sc.nextInt());
			System.out.println("\nPlease enter the new card CVC(3 digits): ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			custIn.setCardCVC(sc.nextInt());
			new AccessCustomer(start).updateCVC(custIn);
			break;
		case 13:
//			CUSTOMER DELETE FUNCTION
			System.out.println("Please enter customer ID of record to be deleted: ");
			custIn.setCustomerID(sc.nextInt());
			new AccessCustomer(start).deleteC(custIn);
			break;
		}
	}
	
//----------------------------------- ITEM CRUD -----------------------------------------------
	
// Choosing item CRUD function to do and calling it:
	public void itemCRUD() {
		ItemData itemIn = new ItemData();
//		itemIn.setItemID(0);
		System.out.println("Please enter:\n "
				+ "1: To CREATE a new item\n "
				+ "2: To VIEW all existing items\n "
				+ "3: To UPDATE existing items name\n " 
				+ "4: To UPDATE existing items price\n " 
				+ "5: To UPDATE existing items stock quantity\n " 
				+ "6: To DELETE an items record from the system");
		int itemCrud = sc.nextInt();
		System.out.println();
		sc.nextLine();
		switch (itemCrud) {
		case 1:
//			ITEM CREATE FUNCTION
			System.out.println("Please enter the items name: ");
			itemIn.setItemName(sc.nextLine());
			System.out.println("\nPlease enter the items price: ");
			itemIn.setPrice(sc.nextFloat());
			System.out.println("\nPlease enter the amount of stock: ");
			itemIn.setStock(sc.nextInt());
			new AccessItem(start).createI(itemIn);
			break;
		case 2:
//			ITEM READ FUNCTION
			new AccessItem(start).readAlli();
			break;
		case 3:
//			ITEM UPDATE FUNCTION
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(sc.nextInt());
			System.out.println("\nPlease enter the new item name: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			itemIn.setItemName(sc.nextLine());
			new AccessItem(start).updateName(itemIn);
			break;
		case 4:
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(sc.nextInt());
			System.out.println("\nPlease enter the new item price: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			itemIn.setPrice(sc.nextFloat());
			new AccessItem(start).updatePrice(itemIn);
			break;
		case 5:
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(sc.nextInt());
			System.out.println("\nPlease enter the new item stock quantity: ");
			sc.nextLine(); // to allow user to input new value else field will be empty
			itemIn.setStock(sc.nextInt());
			new AccessItem(start).updateStock(itemIn);
			break;
		case 6:
//			ITEM DELETE FUNCTION
			System.out.println("Please enter item ID of record to be deleted: ");
			itemIn.setItemID(sc.nextInt());
			new AccessItem(start).deleteI(itemIn);
			break;
		}
	}
	
//----------------------------------- ORDER CRUD -----------------------------------------------
	
// Choosing order CRUD function to do and calling it:
	public void orderCRUD() {
		OrderData orderIn = new OrderData();
//		orderIn.setOrderID(0);
		System.out.println("Please enter:\n "
				+ "1: To CREATE a new order\n "
				+ "2: To ADD another item to the order\n "
				+ "3: To VIEW all existing orders and total costs\n " 
				+ "4: To VIEW all items in orders (orderline)\n " 
				+ "5: To UPDATE the quantity of an item in the order\n " 
				+ "6: To DELETE an item from an order\n " 
				+ "7: To DELETE an order from the system");
		int orderCrud = sc.nextInt();
		System.out.println();
		sc.nextLine();
		switch (orderCrud) {
		case 1:
//			ORDER CREATE FUNCTION
			System.out.println("Please enter your customer ID number: ");
			orderIn.setOrderCustID(sc.nextInt());
			new AccessOrder(start).createO(orderIn);
			System.out.println("\nYou can now add an item to your order!");
			System.out.println("\nPlease enter your order ID number: ");
			orderIn.setOrderID(sc.nextInt());
			System.out.println("\nBelow are all the current items, note down the itemID of items you want to add");
			new AccessItem(start).readAlli(); //view all items to choose which to add to order and get item ID
			System.out.println("\nPlease enter the ID number of the item you want to add to the order: ");
			orderIn.setOrderItemID(sc.nextInt());
			System.out.println("\nPlease enter the quantity you require of the item: ");
			orderIn.setOrderQuant(sc.nextInt());
//			new AccessOrder().TotalCost();
			new AccessOrder(start).addItem(orderIn);
			break;
		case 2:
//			ORDER ITEM CREATE FUNCTION
			System.out.println("Below are all the current items, note down the itemID of items you want to add to your order");
			new AccessItem(start).readAlli(); //view all items to choose which to add to order and get item ID
			System.out.println("\nPlease enter your order ID number: ");
			orderIn.setOrderID(sc.nextInt());
			System.out.println("\nPlease enter the ID number of the item you want to add to the order: ");
			orderIn.setOrderItemID(sc.nextInt());
			System.out.println("\nPlease enter the quantity you require of the item: ");
			orderIn.setOrderQuant(sc.nextInt());
			new AccessOrder(start).addItem(orderIn);
			break;
		case 3:
//			ORDER ITEM READ FUNCTION
			new AccessOrder(start).readAllOrd();
			break;
		case 4:
//			ORDER READ FUNCTION
			new AccessOrder(start).readAllOrdI();
			break;
		case 5:
//			ITEM UPDATE FUNCTION
			System.out.println("Please enter your order ID: ");
			orderIn.setOrderID(sc.nextInt());
			System.out.println("\nPlease enter ID of item to be updated: ");
			orderIn.setOrderItemID(sc.nextInt());
			System.out.println("\nPlease enter new quantity: ");
			orderIn.setOrderQuant(sc.nextInt());
			new AccessOrder(start).updateQuant(orderIn);
			break;
		case 6:
//			ORDER ITEM DELETE FUNCTION
			System.out.println("Please enter your order ID: ");
			orderIn.setOrderID(sc.nextInt());
			System.out.println("\nPlease enter ID of item to be removed from order: ");
			orderIn.setOrderItemID(sc.nextInt());
			new AccessOrder(start).deleteOrdI(orderIn);
			break;
		case 7:
//			ORDER DELETE FUNCTION
			System.out.println("Please enter order ID of record to be deleted: ");
			orderIn.setOrderID(sc.nextInt());
			new AccessOrder(start).deleteO(orderIn);
			break;
		}
	}
}
