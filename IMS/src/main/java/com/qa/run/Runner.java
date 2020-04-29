package com.qa.run;

import com.qa.connect.Connect;

public class Runner {
	public static void main(String[] args) {

		Connect invCon = new Connect("inventory");
		Selection tester = new Selection(invCon);
		tester.table();
	}
}