package com.qa.accessTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.access.AccessOrder;
import com.qa.connect.Connect;
import com.qa.data.OrderData;

public class AccessOrderTest {

	static Connection conn = null;
	static Statement stmt = null;
	ResultSet rs = null;
	static Connect connection = null;

	OrderData dataO = mock(OrderData.class);
	AccessOrder accessO = new AccessOrder(connection);

//------------------------------------- START CONNECTION -------------------------------------

	@BeforeClass
	public static void startConnect() {
		connection = new Connect("inventorytest");
		conn = connection.call();
		stmt = connection.getstmt();
	}

//------------------------------------- CREATE Tests -------------------------------------
// Create order test
	@Before
	public void initCreate1() {
		when(dataO.getOrderID()).thenReturn(0);
		when(dataO.getOrderCustID()).thenReturn(1);
	}

	@Test
	public void testCreate1() {
		accessO.createO(dataO);
		verify(dataO, never()).getOrderID();
		verify(dataO, times(2)).getOrderCustID();
		verify(dataO, never()).getOrderItemID();
		verify(dataO, never()).getOrderQuant();
		verify(dataO, never()).getTotalCost();
		String readall = "SELECT * from orders WHERE customerID=1";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDcust = rs.getInt("customerID");
				assertEquals(dataO.getOrderCustID(), IDcust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroyCreate1() {
		String deleteEntry = "DELETE FROM orderline";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteEntry1 = "DELETE FROM orders";
		try {
			stmt.executeUpdate(deleteEntry1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Create item in orders test
	@Before
	public void initCreate2() {
		when(dataO.getOrderID()).thenReturn(1);
		when(dataO.getOrderItemID()).thenReturn(1);
		when(dataO.getOrderQuant()).thenReturn(10);
		when(dataO.getTotalCost()).thenReturn(0.0);
	}

	@Test
	public void testCreate2() {
		accessO.addItem(dataO);
		verify(dataO, times(3)).getOrderID();
		verify(dataO, never()).getOrderCustID();
		verify(dataO, times(4)).getOrderItemID();
		verify(dataO, times(2)).getOrderQuant();
		verify(dataO, times(3)).getTotalCost();
		String readall = "SELECT * from orderline WHERE itemID=1";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				int IDitem = rs.getInt("itemID");
				int orderQ = rs.getInt("orderQuant");
				double cost = rs.getDouble("totalCost");
				assertEquals(dataO.getOrderID(), IDorder);
				assertEquals(dataO.getOrderItemID(), IDitem);
				assertEquals(dataO.getOrderQuant(), orderQ);
				assertEquals(dataO.getTotalCost(), cost, 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroyCreate2() {
		String deleteEntry = "DELETE FROM orderline";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteEntry1 = "DELETE FROM orders";
		try {
			stmt.executeUpdate(deleteEntry1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//------------------------------------- READ Tests -------------------------------------
// Read cost, orderID and customerID test
	@Before
	public void initRead1() {
		when(dataO.getOrderID()).thenReturn(10);
		when(dataO.getOrderCustID()).thenReturn(8);
		when(dataO.getOrderItemID()).thenReturn(4);
		when(dataO.getOrderQuant()).thenReturn(20);
		when(dataO.getTotalCost()).thenReturn(38.05);
	}

	@Test
	public void testRead1() {
		String create = "INSERT INTO orders(customerID) VALUES(" + dataO.getOrderCustID() + ")";
		try {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String create1 = "INSERT INTO orderline(orderID,itemID,orderQuant,totalCost) VALUES(" + dataO.getOrderID() + ","
				+ dataO.getOrderItemID() + "," + dataO.getOrderQuant() + 	"," + dataO.getTotalCost() + ")";
		try {
			stmt.executeUpdate(create1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		accessO.readAllOrd();
		String readall = "SELECT ol.orderID, o.customerID, SUM(ol.totalCost) AS totalCost FROM orderline ol "
				+ "INNER JOIN orders o ON ol.orderID=o.orderID GROUP BY orderID=10;";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				int IDcust = rs.getInt("customerID");
				int IDitem = rs.getInt("itemID");
				int orderQ = rs.getInt("orderQuant");
				double cost = rs.getDouble("totalCost");
				assertEquals(dataO.getOrderID(), IDorder);
				assertEquals(dataO.getOrderCustID(), IDcust);
				assertEquals(dataO.getOrderItemID(), IDitem);
				assertEquals(dataO.getOrderQuant(), orderQ);
				assertEquals(dataO.getTotalCost(), cost, 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroyRead1() {
		String deleteEntry = "DELETE FROM orderline";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteEntry1 = "DELETE FROM orders";
		try {
			stmt.executeUpdate(deleteEntry1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Read all details test
	@Before
	public void initRead2() {
		when(dataO.getOrderID()).thenReturn(6);
		when(dataO.getOrderCustID()).thenReturn(6);
		when(dataO.getOrderItemID()).thenReturn(6);
		when(dataO.getOrderQuant()).thenReturn(14);
		when(dataO.getTotalCost()).thenReturn(1.65);
	}

	@Test
	public void testRead2() {
		String create = "INSERT INTO orders(customerID) VALUES(" + dataO.getOrderCustID() + ")";
		try {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String create1 = "INSERT INTO orderline(orderID,itemID,orderQuant,totalCost) VALUES(" + dataO.getOrderID() + ","
				+ dataO.getOrderItemID() + "," + dataO.getOrderQuant() + 	"," + dataO.getTotalCost() + ")";
		try {
			stmt.executeUpdate(create1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		accessO.readAllOrdI();
		String readall = "SELECT * from orderline WHERE orderID=6";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				int IDcust = rs.getInt("customerID");
				int IDitem = rs.getInt("itemID");
				int orderQ = rs.getInt("orderQuant");
				double cost = rs.getDouble("totalCost");
				assertEquals(dataO.getOrderID(), IDorder);
				assertEquals(dataO.getOrderCustID(), IDcust);
				assertEquals(dataO.getOrderItemID(), IDitem);
				assertEquals(dataO.getOrderQuant(), orderQ);
				assertEquals(dataO.getTotalCost(), cost, 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroyRead2() {
		String deleteEntry = "DELETE FROM orderline";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteEntry1 = "DELETE FROM orders";
		try {
			stmt.executeUpdate(deleteEntry1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//------------------------------------- UPDATE Test -------------------------------------

	@Before
	public void initUpdate() {
		when(dataO.getOrderID()).thenReturn(3);
		when(dataO.getOrderItemID()).thenReturn(2);
		when(dataO.getOrderQuant()).thenReturn(5);
		when(dataO.getTotalCost()).thenReturn(10.50);
	}

	@Test
	public void testUpdate() {
		accessO.updateQuant(dataO);
		verify(dataO, times(3)).getOrderID();
		verify(dataO, never()).getOrderCustID();
		verify(dataO, times(4)).getOrderItemID();
		verify(dataO, times(2)).getOrderQuant();
		verify(dataO, times(2)).getTotalCost();
		String readall = "SELECT * from orderline WHERE itemID=2";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				int IDitem = rs.getInt("itemID");
				int orderQ = rs.getInt("orderQuant");
				double cost = rs.getDouble("totalCost");
				assertEquals(dataO.getOrderID(), IDorder);
				assertEquals(dataO.getOrderItemID(), IDitem);
				assertEquals(dataO.getOrderQuant(), orderQ);
				assertEquals(dataO.getTotalCost(), cost, 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate() {
		String deleteEntry = "DELETE FROM orderline";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteEntry1 = "DELETE FROM orders";
		try {
			stmt.executeUpdate(deleteEntry1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//------------------------------------- DELETE Test -------------------------------------
// Delete item test
	@Before
	public void initDelete1() {
		when(dataO.getOrderID()).thenReturn(5);
		when(dataO.getOrderItemID()).thenReturn(7);
	}

	@Test
	public void testDelete1() {
		accessO.deleteOrdI(dataO);
		verify(dataO, times(1)).getOrderID();
		verify(dataO, never()).getOrderCustID();
		verify(dataO, times(1)).getOrderItemID();
		verify(dataO, never()).getOrderQuant();
		verify(dataO, never()).getTotalCost();
		String readall = "SELECT * from orderline WHERE orderID=5 AND itemID=7";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				int IDitem = rs.getInt("itemID");
				assertEquals(null, IDorder);
				assertEquals(null, IDitem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroyDelete1() {
		String deleteEntry = "DELETE FROM orderline";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteEntry1 = "DELETE FROM orders";
		try {
			stmt.executeUpdate(deleteEntry1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// Delete order test
	@Before
	public void initDelete2() {
		when(dataO.getOrderID()).thenReturn(9);
	}

	@Test
	public void testDelete2() {
		accessO.deleteO(dataO);
		verify(dataO, times(2)).getOrderID();
		verify(dataO, never()).getOrderCustID();
		verify(dataO, never()).getOrderItemID();
		verify(dataO, never()).getOrderQuant();
		verify(dataO, never()).getTotalCost();
		String readall = "SELECT * from orders WHERE orderID=9";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int IDorder = rs.getInt("orderID");
				assertEquals(null, IDorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroyDelete2() {
		String deleteEntry = "DELETE FROM orderline";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String deleteEntry1 = "DELETE FROM orders";
		try {
			stmt.executeUpdate(deleteEntry1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//------------------------------------- Exception Testing -------------------------------------

	OrderData x = mock(OrderData.class);
	AccessOrder y = new AccessOrder(connection);

	@Before
	public void excepInit() {

	}

//	@Test(expected = SQLException.class)
//	public void excepTest() {
//		when(x.createC(y)).thenThrow(new SQLException());
//		when(x.readAllC()).thenThrow(new SQLException());
//		when(x.deleteC(y)).thenThrow(new SQLException());
//
//	}

//------------------------------------- END CONNECTION -------------------------------------

	@AfterClass
	public static void stopConnect() {
		conn = null;
	}
}
