package com.monley.bean;

import java.util.ArrayList;

import com.monley.db.Tab_TreeView;

public class TreeViewInformationBean {
	
	private int total;
	private ArrayList<Tab_TreeView> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<Tab_TreeView> getRows() {
		return rows;
	}
	public void setRows(ArrayList<Tab_TreeView> rows) {
		this.rows = rows;
	}

	

}
