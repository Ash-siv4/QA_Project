package com.qa.dataTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.data.OrderData;

public class OrderDataTest {

	OrderData test = new OrderData();

	@Before
	public void SetValues() {
		test.setOrderID(0); // --- already is 0
		test.setOrderCustID(4);
		test.setOrderItemID(3);
		test.setOrderQuant(97);
		test.setTotalCost(5.43);
	}

	@Test
	public void EqualsTest() {
		assertEquals(0, test.getOrderID());
		assertEquals(4, test.getOrderCustID());
		assertEquals(3, test.getOrderItemID());
		assertEquals(97, test.getOrderQuant());
		assertEquals(5.43, test.getTotalCost(),0);
	}
	
	@Test
	public void NotEqualsTest() {
		assertNotEquals(1, test.getOrderID());
		assertNotEquals(5, test.getOrderCustID());
		assertNotEquals(4, test.getOrderItemID());
		assertNotEquals(23, test.getOrderQuant());
		assertNotEquals(23.32, test.getTotalCost(),0);
	}

	@Test
	public void NotNullTest() {
		// Data entered must not be null so "not null" check
		assertNotNull(test.getOrderID());
		assertNotNull(test.getOrderCustID());
		assertNotNull(test.getOrderItemID());
		assertNotNull(test.getOrderQuant());
		assertNotNull(test.getTotalCost());
	}

	@Test
	public void noOrd() {
		assertFalse(test.equals(null));
	}

	@Test
	public void diffOrd() {
		assertFalse(test.equals(new Object()));
	}

	@Test
	public void isOrd() {
		assertTrue(test.equals(test));
	}

}
