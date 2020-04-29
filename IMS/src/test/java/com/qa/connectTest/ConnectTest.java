package com.qa.connectTest;

import static org.junit.Assert.assertNotEquals;

//import java.sql.SQLException;

import org.junit.Test;

import com.qa.connect.Connect;

public class ConnectTest {
	
	Connect check = new Connect("inventoryTest");
	
	@Test
	public void CheckCon() {
		assertNotEquals(null, check.call());
	}
	
//	@Test (expected = SQLException.class)
//	public void exceptionCheck() {
//		Connect checkEx1 = null;
//		checkEx1.call();
//	}
//	
//	@Test (expected = ClassNotFoundException.class)
//	public void exception2Check() {
//		check.call();
//	}
}
