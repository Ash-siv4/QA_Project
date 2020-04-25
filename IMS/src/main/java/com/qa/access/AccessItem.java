package com.qa.access;

import java.sql.*;

import com.qa.connect.Connect;
import com.qa.data.ItemData;

public class AccessItem {
	
	Connection conn = null;
	Statement stmt = null;
	
// Connect to database
	public AccessItem() {
		try {
			conn = new Connect().call();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Create a new item
	public void createI(ItemData data) {
		String create = "INSERT INTO items(itemName,price,stock) VALUES('" + data.getItemName() + 
				"'," + data.getPrice() + "," + data.getStock() + ")";
		try {
			stmt.executeUpdate(create);
			System.out.println("New item record added!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// View all items table data
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
				System.out.println("Item ID: " + IDitem + " \nItem Name: " + Iname + ", Price: " + price + 
						", Stock: " + stock + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Update item records
	public void updateName(ItemData data2) {
		String update = "UPDATE items SET itemName='" + data2.getItemName() + "' WHERE itemID=" + data2.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item name updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePrice(ItemData data2a) {
		String update = "UPDATE items SET price=" + data2a.getPrice() + " WHERE itemID=" + data2a.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item price updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStock(ItemData data2b) {
		String update = "UPDATE items SET stock=" + data2b.getStock() + " WHERE itemID=" + data2b.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item stock updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Delete an items record
	public void deleteI(ItemData data3) {
		String delete = "DELETE FROM items WHERE itemID=" + data3.getItemID();
		try {
			stmt.executeUpdate(delete);
			System.out.println("Item record removed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
