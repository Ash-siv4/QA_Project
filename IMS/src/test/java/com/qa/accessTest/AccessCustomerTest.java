package com.qa.accessTest;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.doThrow;
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

import com.qa.access.AccessCustomer;
import com.qa.connect.Connect;
import com.qa.data.CustomerData;

public class AccessCustomerTest {

	static Connection conn = null;
	static Statement stmt = null;
	ResultSet rs = null;
	static Connect connection = null;
	
	CustomerData dataC = mock(CustomerData.class);
	AccessCustomer accessC = new AccessCustomer(connection);

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
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getFirstname()).thenReturn("The");
		when(dataC.getSurname()).thenReturn("Doctor");
		when(dataC.getEmail()).thenReturn("dw@tardis.com");
		when(dataC.getMobile()).thenReturn("07497510550");
		when(dataC.getAddress()).thenReturn("65 blue lane, london, s69 l12");
		when(dataC.getCardType()).thenReturn("Debit");
		when(dataC.getCardNo()).thenReturn("4040303020201010");
		when(dataC.getExpiryMonth()).thenReturn(03);
		when(dataC.getExpiryYear()).thenReturn(2025);
		when(dataC.getCardCVC()).thenReturn(684);
	}

	@Test
	public void testCreate() {
		accessC.createC(dataC);
		verify(dataC, never()).getCustomerID();
		verify(dataC, times(2)).getFirstname();
		verify(dataC, times(1)).getSurname();
		verify(dataC, times(1)).getEmail();
		verify(dataC, times(1)).getMobile();
		verify(dataC, times(1)).getAddress();
		verify(dataC, times(1)).getCardType();
		verify(dataC, times(1)).getCardNo();
		verify(dataC, times(1)).getExpiryMonth();
		verify(dataC, times(1)).getExpiryYear();
		verify(dataC, times(1)).getCardCVC();
		String readall = "SELECT * from customers WHERE firstname='The'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
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
				assertEquals(dataC.getFirstname(), name1);
				assertEquals(dataC.getSurname(), name2);
				assertEquals(dataC.getEmail(), mail);
				assertEquals(dataC.getMobile(), phone);
				assertEquals(dataC.getAddress(), addr);
				assertEquals(dataC.getCardType(), cardT);
				assertEquals(dataC.getCardNo(), cardN);
				assertEquals(dataC.getExpiryMonth(), expM);
				assertEquals(dataC.getExpiryYear(), expY);
				assertEquals(dataC.getCardCVC(), CVC);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
//		doThrow(new SQLException()).when(dataC).accessC.createC(null);
	}

	@After
	public void destroyCreate() {
		String deleteEntry = "DELETE FROM customers WHERE firstname='The'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- READ Test -------------------------------------

	@Before
	public void initRead() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getFirstname()).thenReturn("Peeta");
		when(dataC.getSurname()).thenReturn("Mellark");
		when(dataC.getEmail()).thenReturn("orange@HG.com");
		when(dataC.getMobile()).thenReturn("07347264832");
		when(dataC.getAddress()).thenReturn("543 frends lane, glasgow, G43 5MF");
		when(dataC.getCardType()).thenReturn("Visa");
		when(dataC.getCardNo()).thenReturn("4857394804958374");
		when(dataC.getExpiryMonth()).thenReturn(06);
		when(dataC.getExpiryYear()).thenReturn(2057);
		when(dataC.getCardCVC()).thenReturn(147);
	}

	@Test
	public void testRead() {
		String create = "INSERT INTO customers(firstname,surname,email,mobile,address,cardType,cardNo,expiryMonth,"
				+ "expiryYear,cardCVC) VALUES('" + dataC.getFirstname() + "','" + dataC.getSurname() + "','"
				+ dataC.getEmail() + "','" + dataC.getMobile() + "','" + dataC.getAddress() + "','" + dataC.getCardType()
				+ "','" + dataC.getCardNo() + "'," + dataC.getExpiryMonth() + "," + dataC.getExpiryYear() + ","
				+ dataC.getCardCVC() + ")";
		try {
			stmt.executeUpdate(create);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		accessC.readAllC();
		String readall = "SELECT * from customers WHERE firstname='Peeta'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
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
				assertEquals(dataC.getFirstname(), name1);
				assertEquals(dataC.getSurname(), name2);
				assertEquals(dataC.getEmail(), mail);
				assertEquals(dataC.getMobile(), phone);
				assertEquals(dataC.getAddress(), addr);
				assertEquals(dataC.getCardType(), cardT);
				assertEquals(dataC.getCardNo(), cardN);
				assertEquals(dataC.getExpiryMonth(), expM);
				assertEquals(dataC.getExpiryYear(), expY);
				assertEquals(dataC.getCardCVC(), CVC);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyRead() {
		String deleteEntry = "DELETE FROM customers WHERE firstname='Peeta'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- UPDATE Tests -------------------------------------
// Update first name test	
	@Before
	public void initUpdate1() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getFirstname()).thenReturn("Potassium");
	}

	@Test
	public void testUpdate1() {
		accessC.update1stName(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT firstname from customers WHERE firstname='Potassium'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String name1 = rs.getString("firstname");
				assertEquals(dataC.getFirstname(), name1);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate1() {
		String deleteEntry = "DELETE FROM customers WHERE firstname='Potassium'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update surname test
	@Before
	public void initUpdate2() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getSurname()).thenReturn("Argon");
	}

	@Test
	public void testUpdate2() {
		accessC.update2ndName(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getSurname();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT surname from customers WHERE surname='Argon'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String name2 = rs.getString("surname");
				assertEquals(dataC.getSurname(), name2);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate2() {
		String deleteEntry = "DELETE FROM customers WHERE surname='Argon'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update email test
	@Before
	public void initUpdate3() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getEmail()).thenReturn("HI@bye.com");
	}

	@Test
	public void testUpdate3() {
		accessC.updateEmail(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getEmail();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT email from customers WHERE email='HI@bye.com'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String mail = rs.getString("email");
				assertEquals(dataC.getEmail(), mail);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate3() {
		String deleteEntry = "DELETE FROM customers WHERE email='HI@bye.com'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update mobile test
	@Before
	public void initUpdate4() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getMobile()).thenReturn("09876543210");
	}

	@Test
	public void testUpdate4() {
		accessC.updateMobile(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getMobile();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT mobile from customers WHERE mobile='09876543210'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String phone = rs.getString("mobile");
				assertEquals(dataC.getMobile(), phone);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate4() {
		String deleteEntry = "DELETE FROM customers WHERE mobile='09876543210'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update address test
	@Before
	public void initUpdate5() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getAddress()).thenReturn("99 Grange Road, Sheffield, SU7 8SK");
	}

	@Test
	public void testUpdate5() {
		accessC.updateAddr(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getAddress();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT address from customers WHERE address='99 Grange Road, Sheffield, SU7 8SK'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String addr = rs.getString("address");
				assertEquals(dataC.getEmail(), addr);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate5() {
		String deleteEntry = "DELETE FROM customers WHERE address='99 Grange Road, Sheffield, SU7 8SK'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update card type test
	@Before
	public void initUpdate6() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getCardType()).thenReturn("Visa debit");
	}

	@Test
	public void testUpdate6() {
		accessC.updateCtype(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getCardType();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT cardType from customers WHERE cardType='Visa debit'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String cardT = rs.getString("cardType");
				assertEquals(dataC.getCardType(), cardT);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate6() {
		String deleteEntry = "DELETE FROM customers WHERE cardType='Visa debit'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update card number test
	@Before
	public void initUpdate7() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getCardNo()).thenReturn("5040504050405040");
	}

	@Test
	public void testUpdate7() {
		accessC.updateCno(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getCardNo();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT cardNo from customers WHERE cardNo='5040504050405040'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String cardN = rs.getString("cardNo");
				assertEquals(dataC.getCardNo(), cardN);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate7() {
		String deleteEntry = "DELETE FROM customers WHERE cardNo='5040504050405040'";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update card expire month test
	@Before
	public void initUpdate8() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getExpiryMonth()).thenReturn(10);
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
	}

	@Test
	public void testUpdate8() {
		accessC.updateExpM(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getExpiryMonth();
		String readall = "SELECT expiryMonth from customers WHERE expiryMonth=10";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int expM = rs.getInt("expiryMonth");
				assertEquals(dataC.getExpiryMonth(), expM);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate8() {
		String deleteEntry = "DELETE FROM customers WHERE expiryMonth=10";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update card expire year test
	@Before
	public void initUpdate9() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getExpiryYear()).thenReturn(2050);
	}

	@Test
	public void testUpdate9() {
		accessC.updateExpY(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, times(1)).getExpiryYear();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT expiryYear from customers WHERE expiryYear=2050";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int expY = rs.getInt("expiryYear");
				assertEquals(dataC.getExpiryYear(), expY);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate9() {
		String deleteEntry = "DELETE FROM customers WHERE expiryYear=2050";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

// Update card CVC test
	@Before
	public void initUpdate10() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getCardCVC()).thenReturn(000);
	}

	@Test
	public void testUpdate10() {
		accessC.updateCVC(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, times(1)).getCardCVC();
		String readall = "SELECT cardCVC from customers WHERE cardCVC=" + dataC.getCardCVC();
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				int CVC = rs.getInt("cardCVC");
				assertEquals(dataC.getCardCVC(), CVC);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyUpdate10() {
		String deleteEntry = "DELETE FROM customers WHERE cardCVC=" + dataC.getCardCVC();
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- DELETE Test -------------------------------------

	@Before
	public void initDelete() {
		when(dataC.getCustomerID()).thenReturn(0);
		when(dataC.getFirstname()).thenReturn("bob");
	}

	@Test
	public void testDelete() {
		accessC.deleteC(dataC);
		verify(dataC, times(1)).getCustomerID();
		verify(dataC, never()).getFirstname();
		verify(dataC, never()).getSurname();
		verify(dataC, never()).getEmail();
		verify(dataC, never()).getMobile();
		verify(dataC, never()).getAddress();
		verify(dataC, never()).getCardType();
		verify(dataC, never()).getCardNo();
		verify(dataC, never()).getExpiryMonth();
		verify(dataC, never()).getExpiryYear();
		verify(dataC, never()).getCardCVC();
		String readall = "SELECT firstname from customers WHERE firstname='" + dataC.getFirstname() + "'";
		try {
			rs = stmt.executeQuery(readall);
			while (rs.next()) {
				String name1 = rs.getString("firstname");
				assertEquals(null, name1);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

	@After
	public void destroyDelete() {
		String deleteEntry = "DELETE FROM customers";
		try {
			stmt.executeUpdate(deleteEntry);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

//------------------------------------- Exception Testing -------------------------------------

//	CustomerData y = mock(CustomerData.class);
//	AccessCustomer x = new AccessCustomer(connection);
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