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

import com.qa.access.AccessItem;
import com.qa.connect.Connect;
import com.qa.data.ItemData;

public class AccessItemTest {

	static Connection conn = null;
	static Statement stmt = null;
	ResultSet rs = null;
	static Connect connection = null;

	ItemData dataI = mock(ItemData.class);
	AccessItem accessI = new AccessItem(connection);

//------------------------------------- START CONNECTION -------------------------------------

	@BeforeClass
	public static void startConnect() {
		connection = new Connect("inventorytest");
		conn = connection.call();
		stmt = connection.getstmt();
	}

//------------------------------------- CREATE Test -------------------------------------

	@Before
	public void initCreate() {
		when(dataI.getItemID()).thenReturn(0);
		when(dataI.getItemName()).thenReturn("Hoodie");
		when(dataI.getPrice()).thenReturn(16.50);
		when(dataI.getStock()).thenReturn(145);
	}

	@Test
	public void testCreate() {
		accessI.createI(dataI);
		verify(dataI, never()).getItemID();
		verify(dataI, times(2)).getItemName();
		verify(dataI, times(1)).getPrice();
		verify(dataI, times(1)).getStock();
		String readall = "SELECT * from items WHERE itemName='Hoodie'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String Iname = rs.getString("itemName");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				assertEquals(dataI.getItemName(), Iname);
				assertEquals(dataI.getPrice(), price, 0);
				assertEquals(dataI.getStock(), stock);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyCreate() {
		String deleteEntry = "DELETE FROM items WHERE itemName='Hoodie'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- READ Test -------------------------------------

	@Before
	public void initRead() {
		when(dataI.getItemID()).thenReturn(0);
		when(dataI.getItemName()).thenReturn("Shirt");
		when(dataI.getPrice()).thenReturn(10.99);
		when(dataI.getStock()).thenReturn(86);
	}

	@Test
	public void testRead() {
		String create = "INSERT INTO items(itemName,price,stock) VALUES('" + dataI.getItemName() + "',"
				+ dataI.getPrice() + "," + dataI.getStock() + ")";
		try {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		accessI.readAlli();
		String readall = "SELECT * from customers WHERE itemName='Shirt'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String Iname = rs.getString("itemName");
				double price = rs.getDouble("price");
				int stock = rs.getInt("stock");
				assertEquals(dataI.getItemName(), Iname);
				assertEquals(dataI.getPrice(), price, 0);
				assertEquals(dataI.getStock(), stock);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyRead() {
		String deleteEntry = "DELETE FROM items WHERE itemName='Shirt'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- UPDATE Tests -------------------------------------
// Update item name test	
	@Before
	public void initUpdate1() {
		when(dataI.getItemID()).thenReturn(0);
		when(dataI.getItemName()).thenReturn("Pepsi");
	}

	@Test
	public void testUpdate1() {
		accessI.updateName(dataI);
		verify(dataI, times(1)).getItemID();
		verify(dataI, times(1)).getItemName();
		verify(dataI, never()).getPrice();
		verify(dataI, never()).getStock();
		String readall = "SELECT itemName from items WHERE itemName='Pepsi'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String Iname = rs.getString("itemName");
				assertEquals(dataI.getItemName(), Iname);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate1() {
		String deleteEntry = "DELETE FROM items WHERE itemName='Pepsi'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update item price test
	@Before
	public void initUpdate2() {
		when(dataI.getItemID()).thenReturn(0);
		when(dataI.getPrice()).thenReturn(4.99);
	}

	@Test
	public void testUpdate2() {
		accessI.updatePrice(dataI);
		verify(dataI, times(1)).getItemID();
		verify(dataI, never()).getItemName();
		verify(dataI, times(1)).getPrice();
		verify(dataI, never()).getStock();
		String readall = "SELECT price from items WHERE price=4.99";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				double price = rs.getDouble("price");
				assertEquals(dataI.getPrice(), price, 0);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate2() {
		String deleteEntry = "DELETE FROM items WHERE price=4.99";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update item stock test
	@Before
	public void initUpdate3() {
		when(dataI.getItemID()).thenReturn(0);
		when(dataI.getStock()).thenReturn(35);
	}

	@Test
	public void testUpdate3() {
		accessI.updateStock(dataI);
		verify(dataI, times(1)).getItemID();
		verify(dataI, never()).getItemName();
		verify(dataI, never()).getPrice();
		verify(dataI, times(1)).getStock();
		String readall = "SELECT stock from items WHERE stock=35";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int stock = rs.getInt("stock");
				assertEquals(dataI.getStock(), stock);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate3() {
		String deleteEntry = "DELETE FROM items WHERE stock=35";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- DELETE Test -------------------------------------

	@Before
	public void initDelete() {
		when(dataI.getItemID()).thenReturn(0);
		when(dataI.getItemName()).thenReturn("Xbox");
	}

	@Test
	public void testDelete() {
		accessI.deleteI(dataI);
		verify(dataI, times(1)).getItemID();
		verify(dataI, never()).getItemName();
		verify(dataI, never()).getPrice();
		verify(dataI, never()).getStock();
		String readall = "SELECT itemName from items WHERE itemName='" + dataI.getItemName() + "'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String Iname = rs.getString("itemName");
				assertEquals(null, Iname);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyDelete() {
		String deleteEntry = "DELETE FROM items";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- Exception Testing -------------------------------------

//	ItemData x = mock(ItemData.class);
//	AccessItem y = new AccessItem(connection);
//
//	@Before
//	public void excepInit() {
//
//	}

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
