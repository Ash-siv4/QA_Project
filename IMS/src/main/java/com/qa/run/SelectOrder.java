package com.qa.run;

import com.qa.access.AccessItem;
import com.qa.access.AccessOrder;
import com.qa.connect.Connect;
import com.qa.data.OrderData;

public class SelectOrder {

	static Connect start;
	static ScanIn in;

	SelectOrder(Connect startPassed, ScanIn inPassed) {
		start = startPassed;
		in = inPassed;
	}

	// Choosing order CRUD function to do and calling it:
	public void orderCRUD() {
		OrderData orderIn = new OrderData();
//			orderIn.setOrderID(0);
		System.out.println("Please enter:\n " + "1: To CREATE a new order\n " + "2: To ADD another item to the order\n "
				+ "3: To VIEW all existing orders and total costs\n " + "4: To VIEW all items in orders (orderline)\n "
				+ "5: To UPDATE the quantity of an item in the order\n " + "6: To DELETE an item from an order\n "
				+ "7: To DELETE an order from the system");
		int orderCrud = in.getInt();
		System.out.println();
		in.getString();
		switch (orderCrud) {
		case 1:
//				ORDER CREATE FUNCTION
			System.out.println("Please enter your customer ID number: ");
			orderIn.setOrderCustID(in.getInt());
			new AccessOrder(start).createO(orderIn);
			System.out.println("\nYou can now add an item to your order!");
			System.out.println("\nPlease enter your order ID number: ");
			orderIn.setOrderID(in.getInt());
			System.out.println("\nBelow are all the current items, note down the itemID of items you want to add");
			new AccessItem(start).readAlli(); // view all items to choose which to add to order and get item ID
			System.out.println("\nPlease enter the ID number of the item you want to add to the order: ");
			orderIn.setOrderItemID(in.getInt());
			System.out.println("\nPlease enter the quantity you require of the item: ");
			orderIn.setOrderQuant(in.getInt());
//				new AccessOrder().TotalCost();
			new AccessOrder(start).addItem(orderIn);
			break;
		case 2:
//				ORDER ITEM CREATE FUNCTION
			System.out.println(
					"Below are all the current items, note down the itemID of items you want to add to your order");
			new AccessItem(start).readAlli(); // view all items to choose which to add to order and get item ID
			System.out.println("\nPlease enter your order ID number: ");
			orderIn.setOrderID(in.getInt());
			System.out.println("\nPlease enter the ID number of the item you want to add to the order: ");
			orderIn.setOrderItemID(in.getInt());
			System.out.println("\nPlease enter the quantity you require of the item: ");
			orderIn.setOrderQuant(in.getInt());
			new AccessOrder(start).addItem(orderIn);
			break;
		case 3:
//				ORDER ITEM READ FUNCTION
			new AccessOrder(start).readAllOrd();
			break;
		case 4:
//				ORDER READ FUNCTION
			new AccessOrder(start).readAllOrdI();
			break;
		case 5:
//				ITEM UPDATE FUNCTION
			System.out.println("Please enter your order ID: ");
			orderIn.setOrderID(in.getInt());
			System.out.println("\nPlease enter ID of item to be updated: ");
			orderIn.setOrderItemID(in.getInt());
			System.out.println("\nPlease enter new quantity: ");
			orderIn.setOrderQuant(in.getInt());
			new AccessOrder(start).updateQuant(orderIn);
			break;
		case 6:
//				ORDER ITEM DELETE FUNCTION
			System.out.println("Please enter your order ID: ");
			orderIn.setOrderID(in.getInt());
			System.out.println("\nPlease enter ID of item to be removed from order: ");
			orderIn.setOrderItemID(in.getInt());
			new AccessOrder(start).deleteOrdI(orderIn);
			break;
		case 7:
//				ORDER DELETE FUNCTION
			System.out.println("Please enter order ID of record to be deleted: ");
			orderIn.setOrderID(in.getInt());
			new AccessOrder(start).deleteO(orderIn);
			break;
		}
	}
}
