/****define tsp obj ***/
package com.monley.bean;

import java.util.ArrayList;
import com.monley.db.Tab_tspserver;

public class TspServerInformation {
	private int total;
	private ArrayList<Tab_tspserver> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<Tab_tspserver> getRows() {
		return rows;
	}
	public void setRows(ArrayList<Tab_tspserver> rows) {
		this.rows = rows;
	}
	
}
