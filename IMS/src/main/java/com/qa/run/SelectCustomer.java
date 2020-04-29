package com.qa.run;

import com.qa.access.AccessCustomer;
import com.qa.connect.Connect;
import com.qa.data.CustomerData;

public class SelectCustomer {
	
	static Connect start;
	static ScanIn in;
	
	SelectCustomer(Connect startPassed, ScanIn inPassed) {
		start = startPassed;
		in = inPassed;
	}
	
	// Choosing customer CRUD function to do and calling it:
	public void customerCRUD() {
		CustomerData custIn = new CustomerData();
		AccessCustomer ac = new AccessCustomer(start);
//			custIn.setCustomerID(0);
		System.out.println("Please enter:\n " + "1: To CREATE a new customer\n "
				+ "2: To VIEW all existing customers\n " + "3: To UPDATE an existing customers FIRSTNAME\n "
				+ "4: To UPDATE an existing customers SURNAME\n " + "5: To UPDATE an existing customers EMAIL\n "
				+ "6: To UPDATE an existing customers MOBILE\n " + "7: To UPDATE an existing customers ADDRESS\n "
				+ "8: To UPDATE an existing customers CARD TYPE\n "
				+ "9: To UPDATE an existing customers CARD NUMBER\n "
				+ "10: To UPDATE an existing customers CARD EXPIRY MONTH\n "
				+ "11: To UPDATE an existing customers CARD EXPIRY YEAR\n "
				+ "12: To UPDATE an existing customers CARD CVC\n "
				+ "13: To DELETE a customer record from the system");
		int custCrud = in.getInt();
		System.out.println();
		in.getString();
		switch (custCrud) {
		case 1:
//				CUSTOMER CREATE FUNCTION
			System.out.println("Please enter your firstname: ");
			custIn.setFirstname(in.getString());
			System.out.println("\nPlease enter your surname: ");
			custIn.setSurname(in.getString());
			System.out.println("\nPlease enter your email: ");
			custIn.setEmail(in.getString());
			System.out.println("\nPlease enter your mobile(11 digits): ");
			custIn.setMobile(in.getString());
			System.out.println("\nPlease enter your address: ");
			custIn.setAddress(in.getString());
			System.out.println("\nPlease enter your payment method(i.e. visa, debit): ");
			custIn.setCardType(in.getString());
			System.out.println("\nPlease enter your 16 digit card number: ");
			custIn.setCardNo(in.getString());
			System.out.println("\nPlease enter your card expiry month in digits(mm): ");
			custIn.setExpiryMonth(in.getInt());
			System.out.println("\nPlease enter your card expiry year(yyyy): ");
			custIn.setExpiryYear(in.getInt());
			System.out.println("\nPlease enter the CVC number on your card(3 digits): ");
			custIn.setCardCVC(in.getInt());
			ac.createC(custIn);
//			new AccessCustomer(start).createC(custIn);
			break;
		case 2:
//				CUSTOMER READ FUNCTION
			ac.readAllC();
//			new AccessCustomer(start).readAllC();
			break;
		case 3:
//				CUSTOMER UPDATE FUNCTION
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new firstname: ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setFirstname(in.getString());
			ac.update1stName(custIn);
//			new AccessCustomer(start).update1stName(custIn);
			break;
		case 4:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new surname: ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setSurname(in.getString());
			ac.update2ndName(custIn);
//			new AccessCustomer(start).update2ndName(custIn);
			break;
		case 5:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new email: ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setEmail(in.getString());
			ac.updateEmail(custIn);
//			new AccessCustomer(start).updateEmail(custIn);
			break;
		case 6:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new mobile number(11 digits): ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setMobile(in.getString());
			ac.updateMobile(custIn);
//			new AccessCustomer(start).updateMobile(custIn);
			break;
		case 7:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new address: ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setAddress(in.getString());
			ac.updateAddr(custIn);
//			new AccessCustomer(start).updateAddr(custIn);
			break;
		case 8:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new card type(i.e. visa, debit, mastercard): ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setCardType(in.getString());
			ac.updateCtype(custIn);
//			new AccessCustomer(start).updateCtype(custIn);
			break;
		case 9:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new card number(16 digits): ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setCardNo(in.getString());
			ac.updateCno(custIn);
//			new AccessCustomer(start).updateCno(custIn);
			break;
		case 10:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new card expiry month in digits(mm): ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setExpiryMonth(in.getInt());
			ac.updateExpM(custIn);
//			new AccessCustomer(start).updateExpM(custIn);
			break;
		case 11:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new card expiry year(yyyy): ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setExpiryYear(in.getInt());
			ac.updateExpY(custIn);
//			new AccessCustomer(start).updateExpY(custIn);
			break;
		case 12:
			System.out.println("Please enter customer ID of record to be updated: ");
			custIn.setCustomerID(in.getInt());
			System.out.println("\nPlease enter the new card CVC(3 digits): ");
			in.getString(); // to allow user to input new value else field will be empty
			custIn.setCardCVC(in.getInt());
			ac.updateCVC(custIn);
//			new AccessCustomer(start).updateCVC(custIn);
			break;
		case 13:
//				CUSTOMER DELETE FUNCTION
			System.out.println("Please enter customer ID of record to be deleted: ");
			custIn.setCustomerID(in.getInt());
			ac.deleteC(custIn);
//			new AccessCustomer(start).deleteC(custIn);
			break;
		}
	}
}
