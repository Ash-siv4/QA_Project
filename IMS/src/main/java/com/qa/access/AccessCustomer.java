package com.qa.access;

import java.sql.*;

import com.qa.connect.Connect;
import com.qa.data.CustomerData;

public class AccessCustomer {

	Connection conn = null;
	Statement stmt = null;
	
// Connect to database
	public AccessCustomer() {
		try {
			conn = new Connect().call();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Create a new customer
	public void createC(CustomerData data) {
		String create = "INSERT INTO customers(firstname,surname,email,mobile,address,cardType,cardNo,expiryMonth,"
				+ "expiryYear,cardCVC) VALUES('" + data.getFirstname() + "','" + data.getSurname() + "','" 
				+ data.getEmail() + "','" + data.getMobile() + "','" + data.getAddress() + "','" 
				+ data.getCardType() + "','" + data.getCardNo() + "'," + data.getExpiryMonth() 
				+ "," + data.getExpiryYear() + "," + data.getCardCVC() + ")";
		try {
			stmt.executeUpdate(create);
			System.out.println("New customer record added!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// View all customers table data
	public void readAllC() {
		String readall = "SELECT * from customers";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(readall);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				int IDcust = rs.getInt("customerID");
				String name1 = rs.getString("firstname");
				String name2 = rs.getString("surname");
				String mail = rs.getString("email");
				String phone = rs.getString("mobile");
				String addr = rs.getString("address");
				String cardT = rs.getString("cardType");
				String cardN = rs.getString("cardNo");
				int expM = rs.getInt("expiryMonth");
				int expY = rs.getInt("expiryYear");
				int CVC = rs.getInt("cardCVC");
				System.out.println("Customer ID: " + IDcust + " \nFirstname: " + name1 + ", Surname: " + name2 + 
						", \nEmail: " + mail + ", Mobile: " + phone + ", Address: " + addr + ", \nCard Type: " + cardT +
						", Card Number: " + cardN + ", Card Expiry: " + expM + "/" + expY + ", Card CVC: " + CVC + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Update customer records
	public void update1stName(CustomerData data2) {
		String update = "UPDATE customers" + " SET firstname='" + data2.getFirstname() + "' WHERE customerID=" + data2.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer firstname updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update2ndName(CustomerData data2a) {
		String update = "UPDATE customers" + " SET surname='" + data2a.getSurname() + "' WHERE customerID=" + data2a.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer surname updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmail(CustomerData data2b) {
		String update = "UPDATE customers" + " SET surname='" + data2b.getEmail() + "' WHERE customerID=" + data2b.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer email updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// Delete a customers record
	public void deleteC(CustomerData data3) {
		String delete = "DELETE FROM customers WHERE customerID=" + data3.getCustomerID();
		try {
			stmt.executeUpdate(delete);
			System.out.println("Customer record removed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
