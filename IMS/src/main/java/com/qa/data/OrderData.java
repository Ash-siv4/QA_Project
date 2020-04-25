package com.qa.data;

public class OrderData {
	
	//Declaring order table data:
	private int orderID;
	private int orderCustID;
	private int orderItemID;
	private int orderQuant;
	private float totalCost;
	
	//Generating the getters and setters for orders table data:
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getOrderCustID() {
		return orderCustID;
	}
	public void setOrderCustID(int orderCustID) {
		this.orderCustID = orderCustID;
	}
	public int getOrderItemID() {
		return orderItemID;
	}
	public void setOrderItemID(int orderItemID) {
		this.orderItemID = orderItemID;
	}
	public int getOrderQuant() {
		return orderQuant;
	}
	public void setOrderQuant(int orderQuant) {
		this.orderQuant = orderQuant;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
}
