package com.qa.run;

import com.qa.access.AccessItem;
import com.qa.connect.Connect;
import com.qa.data.ItemData;

public class SelectItem {
	
	static Connect start;
	static ScanIn in;
	
	SelectItem(Connect startPassed, ScanIn inPassed) {
		start = startPassed;
		in = inPassed;
	}
	
	// Choosing item CRUD function to do and calling it:
	public void itemCRUD() {
		ItemData itemIn = new ItemData();
//			itemIn.setItemID(0);
		System.out.println("Please enter:\n " + "1: To CREATE a new item\n " + "2: To VIEW all existing items\n "
				+ "3: To UPDATE existing items name\n " + "4: To UPDATE existing items price\n "
				+ "5: To UPDATE existing items stock quantity\n " + "6: To DELETE an items record from the system");
		int itemCrud = in.getInt();
		System.out.println();
		in.getString();
		switch (itemCrud) {
		case 1:
//				ITEM CREATE FUNCTION
			System.out.println("Please enter the items name: ");
			itemIn.setItemName(in.getString());
			System.out.println("\nPlease enter the items price: ");
			itemIn.setPrice(in.getFloat());
			System.out.println("\nPlease enter the amount of stock: ");
			itemIn.setStock(in.getInt());
			new AccessItem(start).createI(itemIn);
			break;
		case 2:
//				ITEM READ FUNCTION
			new AccessItem(start).readAlli();
			break;
		case 3:
//				ITEM UPDATE FUNCTION
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(in.getInt());
			System.out.println("\nPlease enter the new item name: ");
			in.getString(); // to allow user to input new value else field will be empty
			itemIn.setItemName(in.getString());
			new AccessItem(start).updateName(itemIn);
			break;
		case 4:
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(in.getInt());
			System.out.println("\nPlease enter the new item price: ");
			in.getString(); // to allow user to input new value else field will be empty
			itemIn.setPrice(in.getFloat());
			new AccessItem(start).updatePrice(itemIn);
			break;
		case 5:
			System.out.println("Please enter item ID of record to be updated: ");
			itemIn.setItemID(in.getInt());
			System.out.println("\nPlease enter the new item stock quantity: ");
			in.getString(); // to allow user to input new value else field will be empty
			itemIn.setStock(in.getInt());
			new AccessItem(start).updateStock(itemIn);
			break;
		case 6:
//				ITEM DELETE FUNCTION
			System.out.println("Please enter item ID of record to be deleted: ");
			itemIn.setItemID(in.getInt());
			new AccessItem(start).deleteI(itemIn);
			break;
		}
	}
}
