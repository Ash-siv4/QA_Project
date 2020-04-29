package com.qa.run;

import com.qa.connect.Connect;

public class Selection {
		
	Connect start = null;
	SelectCustomer x;
	SelectItem y;
	SelectOrder z;
	ScanIn in;
	
//	Initialise connection to chosen database
	public Selection(Connect connection, ScanIn input) {
		start = connection;
		in = input;
		start.call();
		x = new SelectCustomer(start, in);
		y = new SelectItem(start, in);
		z = new SelectOrder(start, in);
	}
		
// Choosing the table to access:
	public void table() {
		boolean exit = false;
		do {
			exit = false;
			System.out.println("Please enter the number of the table you want to access:\n " + "1: Customers\n " 
					+ "2: Items\n "
					+ "3: Orders\n" 
					+ "ENTER 4 TO EXIT"); 
			int table = in.getInt();
			System.out.println();
			in.getString();
			switch (table) {
			case 1:
				x.customerCRUD();
				exit = true;
				break;
			case 2:
				y.itemCRUD();
				exit = true;
				break;
			case 3:
				z.orderCRUD();
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
}
