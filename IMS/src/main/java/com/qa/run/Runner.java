package com.qa.run;

import com.qa.connect.Connect;

public class Runner {
	public static void main(String[] args) {

		ScanIn user = new ScanIn();
		Connect invCon = new Connect("inventory");
		Selection tester = new Selection(invCon, user);
		tester.table();
		
	}
}