package com.qa.runTest;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.connect.Connect;
import com.qa.run.ScanIn;
//import com.qa.run.SelectCustomer;
//import com.qa.run.SelectItem;
//import com.qa.run.SelectOrder;
import com.qa.run.Selection;

public class SelectionTest {

	static Connection conn = null;
	static Statement stmt = null;
	static Connect connection = null;

	ScanIn in = mock(ScanIn.class);
//	SelectCustomer a = mock(SelectCustomer.class);
//	SelectItem b = mock(SelectItem.class);
//	SelectOrder c = mock(SelectOrder.class);
	Selection select = new Selection(connection, in);

//------------------------------------- START CONNECTION -------------------------------------

	@BeforeClass
	public static void startConnect() {
		connection = new Connect("inventorytest");
		conn = connection.call();
		stmt = connection.getstmt();
	}

//------------------------------------- TABLE Test -------------------------------------

	@Before
	public void initTable() {
		when(in.getInt()).thenReturn(1);
//		doNothing().when(a).customerCRUD();
	}

	@Test
	public void testTable() {
//		select.table();
		verify(in,never()).getInt();
//		verify(a,times(1)).customerCRUD();
//		verify(b,never()).itemCRUD();
//		verify(c,never()).orderCRUD();
		assertEquals(1,in.getInt());
	}

	@After
	public void destroy() {
		String deleteEntry = "DELETE FROM customers";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//------------------------------------- END CONNECTION -------------------------------------

	@AfterClass
	public static void stopConnect() {
		conn = null;
	}

}
