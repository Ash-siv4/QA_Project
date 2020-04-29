package com.qa.access;

import java.sql.*;

import com.qa.connect.Connect;
import com.qa.data.CustomerData;

public class AccessCustomer {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

//------------------------------------- CONNECT TO DATABASE -------------------------------------

	public AccessCustomer(Connect connect) {
		Connect connection = connect;
		try {
			conn = connection.call();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("ERROR: cannot connect to database");
			e.printStackTrace();
		}
	}

//------------------------------------- CREATE A NEW CUSTOMER -------------------------------------

	public void createC(CustomerData data) {
		String create = "INSERT INTO customers(firstname,surname,email,mobile,address,cardType,cardNo,expiryMonth,"
				+ "expiryYear,cardCVC) VALUES('" + data.getFirstname() + "','" + data.getSurname() + "','"
				+ data.getEmail() + "','" + data.getMobile() + "','" + data.getAddress() + "','" + data.getCardType()
				+ "','" + data.getCardNo() + "'," + data.getExpiryMonth() + "," + data.getExpiryYear() + ","
				+ data.getCardCVC() + ")";
		try {
			stmt.executeUpdate(create);
			System.out.println("New customer record added!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot create new customer, check entered values and try again");
			e.printStackTrace();
		}
		String readID = "SELECT customerID FROM customers WHERE firstname='" + data.getFirstname() + "'";
		try {
			rs = stmt.executeQuery(readID);
			while (rs.next()) {
				int IDcust = rs.getInt("customerID");
				System.out.println("\nYour customer ID number is: " + IDcust
						+ " - Please note it down, you will need it for other services");
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get customerID from database");
			e.printStackTrace();
		}
	}

//------------------------------------- VIEW ALL DATA IN CUSTOMERS TABLE -------------------------------------

	public void readAllC() {
		String readall = "SELECT * from customers";
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
				System.out.println("Customer ID: " + IDcust + " \nFirstname: " + name1 + ", Surname: " + name2
						+ ", \nEmail: " + mail + ", Mobile: " + phone + ", Address: " + addr + ", \nCard Type: " + cardT
						+ ", Card Number: " + cardN + ", Card Expiry: " + expM + "/" + expY + ", Card CVC: " + CVC
						+ "\n");
			}
		} catch (SQLException e) {
			System.out.println("ERROR: cannot get customer table data from database");
			e.printStackTrace();
		}
	}

//------------------------------------- UPDATE A CUSTOMERS RECORD -------------------------------------

	public void update1stName(CustomerData data2) {
		String update = "UPDATE customers" + " SET firstname='" + data2.getFirstname() + "' WHERE customerID="
				+ data2.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer firstname updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update2ndName(CustomerData data2a) {
		String update = "UPDATE customers" + " SET surname='" + data2a.getSurname() + "' WHERE customerID="
				+ data2a.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer surname updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmail(CustomerData data2b) {
		String update = "UPDATE customers" + " SET email='" + data2b.getEmail() + "' WHERE customerID="
				+ data2b.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer email updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateMobile(CustomerData data2c) {
		String update = "UPDATE customers" + " SET mobile='" + data2c.getMobile() + "' WHERE customerID="
				+ data2c.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer mobile number updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAddr(CustomerData data2d) {
		String update = "UPDATE customers" + " SET address='" + data2d.getAddress() + "' WHERE customerID="
				+ data2d.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer address updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCtype(CustomerData data2e) {
		String update = "UPDATE customers" + " SET cardType='" + data2e.getCardType() + "' WHERE customerID="
				+ data2e.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer card type updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCno(CustomerData data2f) {
		String update = "UPDATE customers" + " SET cardNo='" + data2f.getCardNo() + "' WHERE customerID="
				+ data2f.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer card number updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateExpM(CustomerData data2g) {
		String update = "UPDATE customers" + " SET expiryMonth='" + data2g.getExpiryMonth() + "' WHERE customerID="
				+ data2g.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer card expiry month updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateExpY(CustomerData data2h) {
		String update = "UPDATE customers" + " SET expiryYear='" + data2h.getExpiryYear() + "' WHERE customerID="
				+ data2h.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer card expiry year updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCVC(CustomerData data2i) {
		String update = "UPDATE customers" + " SET cardCVC='" + data2i.getCardCVC() + "' WHERE customerID="
				+ data2i.getCustomerID();
		try {
			stmt.executeUpdate(update);
			System.out.println("Customer card CVC updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//------------------------------------- DELETE A CUSTOMERS RECORD -------------------------------------

	public void deleteC(CustomerData data3) {
		String delete = "DELETE FROM customers WHERE customerID=" + data3.getCustomerID();
		try {
			stmt.executeUpdate(delete);
			System.out.println("Customer record removed!");
		} catch (SQLException e) {
			System.out.println("ERROR: cannot delete customer, enter a valid customer ID");
			e.printStackTrace();
		}
	}
}
