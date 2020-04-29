package com.qa.dataTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.qa.data.CustomerData;

public class CustomerDataTest {
	
	CustomerData test = new CustomerData();
	
	@Before
	public void SetValues() {
		test.setCustomerID(0); //--- already is 0
		test.setFirstname("Tony");
		test.setSurname("Stark");
		test.setEmail("TonyStark@Avengers.com");
		test.setMobile("07430003000");
		test.setAddress("1 Avengers Road, New York, NY1 3TS");
		test.setCardType("Visa");
		test.setCardNo("4321432143214321");
		test.setExpiryMonth(04);
		test.setExpiryYear(2019);
		test.setCardCVC(300);
	}
	
	@Test
	public void EqualsTest() {
		assertEquals(0, test.getCustomerID());
		assertEquals("Tony", test.getFirstname());
		assertEquals("Stark", test.getSurname());;
		assertEquals("TonyStark@Avengers.com", test.getEmail());
		assertEquals("07430003000", test.getMobile());
		assertEquals("1 Avengers Road, New York, NY1 3TS", test.getAddress());
		assertEquals("Visa", test.getCardType());
		assertEquals("4321432143214321", test.getCardNo());
		assertEquals(04, test.getExpiryMonth());
		assertEquals(2019, test.getExpiryYear());
		assertEquals(300, test.getCardCVC());
	}
	
	@Test
	public void NotEqualsTest() {
		assertNotEquals(3, test.getCustomerID());
		assertNotEquals("A", test.getFirstname());
		assertNotEquals("B", test.getSurname());;
		assertNotEquals("C@D.com", test.getEmail());
		assertNotEquals("01020304050", test.getMobile());
		assertNotEquals("BFG", test.getAddress());
		assertNotEquals("HI", test.getCardType());
		assertNotEquals("1111222233334444", test.getCardNo());
		assertNotEquals(10, test.getExpiryMonth());
		assertNotEquals(1000, test.getExpiryYear());
		assertNotEquals(100, test.getCardCVC());
	}
	
	@Test
	public void NotNullTest() {
		//Data entered must not be null so "not null" check
		assertNotNull(test.getCustomerID());
//		System.out.println(test.getCustomerID());
		assertNotNull(test.getFirstname());
		assertNotNull(test.getSurname());
		assertNotNull(test.getEmail());
		assertNotNull(test.getMobile());
		assertNotNull(test.getAddress());
		assertNotNull(test.getCardType());
		assertNotNull(test.getCardNo());
		assertNotNull(test.getExpiryMonth());
		assertNotNull(test.getExpiryYear());
		assertNotNull(test.getCardCVC());
	}
	
	@Test
	public void NullTest() {
		//A passed parameter must be null, so "null" check --- checking the strings
//		test.setCustomerID((Integer) null);
//		assertNull(test.getCustomerID());
		test.setFirstname(null);
		assertNull(test.getFirstname());
		test.setSurname(null);
		assertNull(test.getSurname());
		test.setEmail(null);
		assertNull(test.getEmail());
		test.setMobile(null);
		assertNull(test.getMobile());
		test.setAddress(null);
		assertNull(test.getAddress());
		test.setCardType(null);
		assertNull(test.getCardType());
		test.setCardNo(null);
		assertNull(test.getCardNo());
//		test.setExpiryMonth((Integer) null);
//		assertNull(test.getExpiryMonth());
//		test.setExpiryYear((Integer) null);
//		assertNull(test.getExpiryYear());
//		test.setCardCVC((Integer) null);
//		assertNull(test.getCardCVC());
	}
	
	@Test
	public void noCust() {
		assertFalse(test.equals(null));
	}
	
	@Test
	public void diffCust() {
		assertFalse(test.equals(new Object()));
	}
	
	@Test
	public void isCust() {
		assertTrue(test.equals(test));
	}
	
}
