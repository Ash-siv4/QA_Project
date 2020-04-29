package com.qa.dataTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.data.ItemData;

public class ItemDataTest {
	
	ItemData test = new ItemData();
	
	@Before
	public void SetValues() {
		test.setItemID(0); //--- already is 0
		test.setItemName("Book");
		test.setPrice(8.99);
		test.setStock(50);
	}
	
	@Test
	public void EqualsTest() {
		assertEquals(0, test.getItemID());
		assertEquals("Book", test.getItemName());
		assertEquals(8.99, test.getPrice(),0);
		assertEquals(50, test.getStock());
	}
	
	@Test
	public void NotEqualsTest() {
		assertNotEquals(11, test.getItemID());
		assertNotEquals("Awaken", test.getItemName());
		assertNotEquals(5.97, test.getPrice(),0);
		assertNotEquals(25, test.getStock());
	}
	
	@Test
	public void NotNullTest() {
		//Data entered must not be null so "not null" check
		assertNotNull(test.getItemID());
		assertNotNull(test.getItemName());
		assertNotNull(test.getPrice());
		assertNotNull(test.getStock());
	}
	
	public void NullTest() {
		//A passed parameter must be null, so "null" check --- checking the strings
//		test.setItemID((Integer) null);
//		assertNull(test.getItemID());
		test.setItemName(null);
		assertNull(test.getItemName());
//		test.setPrice((Double) null);
//		assertNull(test.getPrice());
//		test.setStock((Integer) null);
//		assertNull(test.getStock());
	}
	
	@Test
	public void noItem() {
		assertFalse(test.equals(null));
	}
	
	@Test
	public void diffItem() {
		assertFalse(test.equals(new Object()));
	}
	
	@Test
	public void isItem() {
		assertTrue(test.equals(test));
	}
	
}
