package com.monley.boh;

import java.util.ArrayList;

public class GetAllTspBohInfor {
	
	private String total;
	private ArrayList<TspBohRelation> rows = new ArrayList<>();
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public ArrayList<TspBohRelation> getRows() {
		return rows;
	}
	public void setRows(ArrayList<TspBohRelation> rows) {
		this.rows = rows;
	}


}
