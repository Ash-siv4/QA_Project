package com.qa.run;

import java.util.Scanner;

public class ScanIn {

	public final Scanner sc = new Scanner(System.in);

	public String getString() {
		return sc.nextLine();
	}
	
	public int getInt() {
//		int num = Integer.parseInt(sc.nextLine());
		return sc.nextInt();
	}
	
	public float getFloat() {
		return sc.nextFloat();
	}	
}
