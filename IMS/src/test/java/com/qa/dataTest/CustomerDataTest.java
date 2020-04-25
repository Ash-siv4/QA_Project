package com.qa.dataTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.qa.data.CustomerData;

public class CustomerDataTest {
	
	CustomerData test = new CustomerData();
	
	@Before
	public void SetValues() {
//		test.setCustomerID(0); //--- already is 0
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
//		test.setCustomerID(null);
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
//		test.setExpiryMonth(null);
//		assertNull(test.getExpiryMonth());
//		test.setExpiryYear(null);
//		assertNull(test.getExpiryYear());
//		test.setCardCVC(null);
//		assertNull(test.getCardCVC());
	}
}
