package com.qa.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.connect.Connect;
import com.qa.data.OrderData;

public class AccessOrder {

	Connection conn = null;
	Statement stmt = null;

// Connect to database
	public AccessOrder() {
		try {
			conn = new Connect().call();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//-------------------------------------FROM HERE ONWARDS: NEEDS TO BE FIXED UP----------------------
	
//	private int orderID;           -------will delete
//	private int orderCustID;  ---need to make equal to customerID
//	private int orderItemID;  ---need to make equal to itemID
//	private int orderQuant;   ---need to subtract orderQuant from item stock
//	private float totalCost;   ---need to calculate based on item price and orderQuant
	
// Create a new order
	public void createI(OrderData data) {
		String create = "INSERT INTO orders(customerID,itemID,orderQuant,totalCost) VALUES(" + data.getOrderCustID() +
				"," + data.getPrice() + "," + data.getStock() + ")";
		try {
			stmt.executeUpdate(create);
			System.out.println("New order record added!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// View all orders table data
	public void readAlli() {
		String readall = "SELECT * from items";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(readall);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int IDitem = rs.getInt("itemID");
				String Iname = rs.getString("itemName");
				float price = rs.getFloat("price");
				int stock = rs.getInt("stock");
				System.out.println("Item ID: " + IDitem + " \nItem Name: " + Iname + ", Price: " + price + ", Stock: "
						+ stock + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Update orders records
	public void updateName(OrderData data2) {
		String update = "UPDATE items SET itemName='" + data2.getItemName() + "' WHERE itemID=" + data2.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item name updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePrice(OrderData data2a) {
		String update = "UPDATE items SET price=" + data2a.getPrice() + " WHERE itemID=" + data2a.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item price updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStock(OrderData data2b) {
		String update = "UPDATE items SET stock=" + data2b.getStock() + " WHERE itemID=" + data2b.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item stock updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Delete an orders record
	public void deleteI(OrderData data3) {
		String delete = "DELETE FROM items WHERE itemID=" + data3.getItemID();
		try {
			stmt.executeUpdate(delete);
			System.out.println("Item record removed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
