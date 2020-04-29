package com.qa.runTest;

import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.access.AccessCustomer;
import com.qa.access.AccessItem;
import com.qa.access.AccessOrder;
import com.qa.connect.Connect;
//import com.qa.data.CustomerData;
//import com.qa.data.CustomerItem;
//import com.qa.data.CustomerOrder;
import com.qa.run.Selection;

public class SelectionTest {

	static Connection conn = null;
	static Statement stmt = null;
	ResultSet rs = null;
	static Connect connection = null;

//	CustomerData dataC = mock(CustomerData.class);
//	CustomerItem dataI = mock(CustomerItem.class);
//	CustomerOrder dataO = mock(CustomerOrder.class);
	AccessCustomer accessC = mock(AccessCustomer.class);
	AccessItem accessI = mock(AccessItem.class);
	AccessOrder accessO = mock(AccessOrder.class);
	Selection select = new Selection(connection);

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
//		when(accessC.)
	}

	@Test
	public void testTable() {

	}

	@After
	public void destroyTable() {

	}

//------------------------------------- CUSTOMER CRUD Test -------------------------------------

	@Before
	public void initCustomer() {

	}

	@Test
	public void testCustomer() {

	}

	@After
	public void destroyCustomer() {

	}

//------------------------------------- ITEM CRUD Test -------------------------------------

	@Before
	public void initItem() {

	}

	@Test
	public void testItem() {

	}

	@After
	public void destroyItem() {

	}

//------------------------------------- ORDER CRUD Test -------------------------------------

	@Before
	public void initOrder() {

	}

	@Test
	public void testOrder() {

	}

	@After
	public void destroyOrder() {

	}

//------------------------------------- END CONNECTION -------------------------------------

	@AfterClass
	public static void stopConnect() {
		conn = null;
	}
}
