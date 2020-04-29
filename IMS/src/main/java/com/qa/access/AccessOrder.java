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
	ResultSet rs = null;

//------------------------------------- CONNECT TO DATABASE -------------------------------------

	public AccessOrder(Connect connect) {
		Connect connection = connect;
		try {
			conn = connection.call();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("ERROR: cannot connect to database");
			e.printStackTrace();
		}
	}

//------------------------------------- CREATE A NEW ORDER AND ADD ITEMS -------------------------------------

	public void createO(OrderData data) {
		String create = "INSERT INTO orders(customerID) VALUES(" + data.getOrderCustID() + ")";
		try {
			stmt.executeUpdate(create);
			System.out.println("Checking customer ID valid...");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot create new order, enter valid customerID");
			e.printStackTrace();
		}
			String readID = "SELECT orderID FROM orders WHERE customerID=" + data.getOrderCustID();
		try {
			rs = stmt.executeQuery(readID);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				System.out.println("\nCustomer ID valid! \nYour order ID number is: " + IDorder
						+ " - Please note it down, you may need it for other services");
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get order ID from database");
			e.printStackTrace();
		}
	}

	public void addItem(OrderData data) {
		data.setTotalCost(0);
		String create = "INSERT INTO orderline(orderID,itemID,orderQuant,totalCost) VALUES(" + data.getOrderID() + ","
				+ data.getOrderItemID() + "," + data.getOrderQuant() + 	"," + data.getTotalCost() + ")";
		try {
			stmt.executeUpdate(create);
			System.out.println("New item added to order!");
		} catch (SQLException e) {
			System.out.println("ERROR: check order exists");
			e.printStackTrace();
		}
		
		String readPrice = "SELECT price FROM items WHERE itemID=" + data.getOrderItemID();
		try {
			rs = stmt.executeQuery(readPrice);
			while (rs.next()) {
				double unitPrice = rs.getDouble("price");
//				System.out.println("Price: " + price);
				data.setTotalCost(unitPrice);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get item table data from database");
			e.printStackTrace();
		}
		String readQuant = "SELECT orderQuant FROM orderline WHERE orderID=" + data.getOrderID() 
		+ " AND itemID=" + data.getOrderItemID();
		try {
			rs = stmt.executeQuery(readQuant);
			while (rs.next()) {
				int quant = rs.getInt("orderQuant");
//				System.out.println("Quantity: " + quant);
				data.setOrderQuant(quant);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get item table data from database");
			e.printStackTrace();
		}
		data.setTotalCost(data.getOrderQuant()*data.getTotalCost());
		
		String updateCost = "UPDATE orderline SET totalCost=" + data.getTotalCost() 
		+ " WHERE orderID=" + data.getOrderID() + " AND itemID=" + data.getOrderItemID();
		try {
			stmt.executeUpdate(updateCost);
//			System.out.println("Order quantity updated!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot update quantity, check entered values");
			e.printStackTrace();
		}
	}

//------------------------------------- VIEW ALL DATA IN ORDERS/ORDERLINE TABLE -------------------------------------

	public void readAllOrd() {
		String readall = "SELECT ol.orderID, o.customerID, SUM(ol.totalCost) AS totalCost FROM orderline ol\r\n" + 
				"INNER JOIN orders o ON ol.orderID=o.orderID GROUP BY orderID;";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				int IDcust = rs.getInt("customerID");
				float totalCost = rs.getFloat("totalCost");
				System.out.println(
						"Order ID: " + IDorder + ", Customer ID: " + IDcust + ", Total Cost: " + totalCost + "\n");
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get order table data from database");
			e.printStackTrace();
		}
	}

	public void readAllOrdI() {
		String readall = "SELECT o.orderID, o.customerID, c.firstname, ol.itemID, i.itemName, ol.orderQuant, ol.totalCost \r\n" + 
				"FROM orders o INNER JOIN orderline ol ON ol.orderID=o.orderID \r\n" + 
				"INNER JOIN items i ON ol.itemID=i.itemID \r\n" + 
				"INNER JOIN customers c ON o.customerID=c.customerID;";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				int IDcust = rs.getInt("customerID");
				String firstN = rs.getString("firstname");
				int IDitem = rs.getInt("itemID");
				String itemN = rs.getString("itemName");
				int orderQ = rs.getInt("orderQuant");
				double cost = rs.getDouble("totalCost");
				System.out.println("Order ID: " + IDorder + " \nCustomer ID: " + IDcust + ", Firstname: " + firstN
						+ " \nItem ID: " + IDitem + ", Item Name: " + itemN + ", Order quantity: " + orderQ 
						+ ", Total Cost of item: " + cost +"\n");
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get order table data from database");
			e.printStackTrace();
		}
	}

//------------------------------------- UPDATE AN ORDERS RECORD -------------------------------------

	public void updateQuant(OrderData data2) {
		String update = "UPDATE orderline SET orderQuant=" + data2.getOrderQuant() + " WHERE orderID="
				+ data2.getOrderID() + " AND itemID=" + data2.getOrderItemID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Order quantity updated!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot update quantity, check entered values");
			e.printStackTrace();
		}
		String readPrice = "SELECT price FROM items WHERE itemID=" + data2.getOrderItemID();
		try {
			rs = stmt.executeQuery(readPrice);
			while (rs.next()) {
				double price = rs.getDouble("price");
//				System.out.println("Price: " + price);
				data2.setTotalCost(price);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get item table data from database");
			e.printStackTrace();
		}
		String readQuant = "SELECT orderQuant FROM orderline WHERE orderID=" + data2.getOrderID() 
		+ " AND itemID=" + data2.getOrderItemID();
		try {
			rs = stmt.executeQuery(readQuant);
			while (rs.next()) {
				int quant = rs.getInt("orderQuant");
//				System.out.println("Quantity: " + quant);
				data2.setOrderQuant(quant);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get item table data from database");
			e.printStackTrace();
		}
		data2.setTotalCost(data2.getOrderQuant()*data2.getTotalCost());
		
		String updateCost = "UPDATE orderline SET totalCost=" + data2.getTotalCost() 
		+ " WHERE orderID=" + data2.getOrderID() + " AND itemID=" + data2.getOrderItemID();
		try {
			stmt.executeUpdate(updateCost);
//			System.out.println("Order quantity updated!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot update quantity, check entered values");
			e.printStackTrace();
		}
	}

//------------------------------------- DELETE AN ORDER OR ORDER ITEM RECORD -------------------------------------

	public void deleteOrdI(OrderData data3) {
		String delete = "DELETE FROM orderline WHERE itemID=" + data3.getOrderItemID() + " AND orderID="
				+ data3.getOrderID();
		try {
			stmt.executeUpdate(delete);
			System.out.println("Item removed from order!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot delete order item, enter valid item and order IDs");
			e.printStackTrace();
		}
	}

	public void deleteO(OrderData data3) {
		String deleteChild = "DELETE FROM orderline WHERE orderID=" + data3.getOrderID();
		try {
			stmt.executeUpdate(deleteChild);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteParent = "DELETE FROM orders WHERE orderID=" + data3.getOrderID();
		try {
			stmt.executeUpdate(deleteParent);
			System.out.println("Order record removed!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot delete order, enter a valid order ID");
			e.printStackTrace();
		}
	}
}
