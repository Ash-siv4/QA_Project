package com.qa.access;

import java.sql.*;

import com.qa.connect.Connect;
import com.qa.data.ItemData;

public class AccessItem {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

//------------------------------------- CONNECT TO DATABASE -------------------------------------

	public AccessItem(Connect connect) {
		Connect connection = connect;
		try {
			conn = connection.call();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("ERROR: cannot connect to database");
			e.printStackTrace();
		}
	}

//------------------------------------- CREATE A NEW ITEM -------------------------------------

	public void createI(ItemData data) {
		String create = "INSERT INTO items(itemName,price,stock) VALUES('" + data.getItemName() + "'," + data.getPrice()
				+ "," + data.getStock() + ")";
		try {
			stmt.executeUpdate(create);
			System.out.println("New item record added!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot create new item, check entered values and try again");
			e.printStackTrace();
		}
		String readID = "SELECT itemID FROM items WHERE itemName='" + data.getItemName() + "'";
		try {
			rs = stmt.executeQuery(readID);
			while (rs.next()) {
				int IDitem = rs.getInt("itemID");
				System.out.println("\nItem ID number is: " + IDitem
						+ " - Please note it down, you may need it for other services");
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get item ID from database");
			e.printStackTrace();
		}
	}

//------------------------------------- VIEW ALL DATA IN ITEMS TABLE -------------------------------------

	public void readAlli() {
		String readall = "SELECT * from items";
		try {
			rs = stmt.executeQuery(readall);
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get item table data from database");
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int IDitem = rs.getInt("itemID");
				String Iname = rs.getString("itemName");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				System.out.println("Item ID: " + IDitem + " \nItem Name: " + Iname + ", Price: " + price + ", Stock: "
						+ stock + "\n");
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get item table data from database");
			e.printStackTrace();
		}
	}

//------------------------------------- UPDATE AN ITEMS RECORD -------------------------------------

	public void updateName(ItemData data2) {
		String update = "UPDATE items SET itemName='" + data2.getItemName() + "' WHERE itemID=" + data2.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item name updated!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot update item, enter a valid item ID and name");
			e.printStackTrace();
		}
	}

	public void updatePrice(ItemData data2a) {
		String update = "UPDATE items SET price=" + data2a.getPrice() + " WHERE itemID=" + data2a.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item price updated!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot update item, enter a valid item ID and price in digits");
			e.printStackTrace();
		}
	}

	public void updateStock(ItemData data2b) {
		String update = "UPDATE items SET stock=" + data2b.getStock() + " WHERE itemID=" + data2b.getItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Item stock updated!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot update item, enter a valid item ID and stock in digits");
			e.printStackTrace();
		}
	}

//------------------------------------- DELETE AN ITEMS RECORD -------------------------------------

	public void deleteI(ItemData data3) {
		String delete = "DELETE FROM items WHERE itemID=" + data3.getItemID();
		try {
			stmt.executeUpdate(delete);
			System.out.println("Item record removed!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot delete item, enter a valid item ID");
			e.printStackTrace();
		}
	}

}
