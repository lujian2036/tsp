package com.monley.bean;

import java.util.ArrayList;

public class BohTspInformation {
	private int total;
	private ArrayList<SingleBohTspRelation>  rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<SingleBohTspRelation> getRows() {
		return rows;
	}
	public void setRows(ArrayList<SingleBohTspRelation> rows) {
		this.rows = rows;
	}
	

}
