package com.monley.boh;

import java.util.ArrayList;


import com.monley.db.Tab_boh;

public class TspBohRelation {
	private Tab_boh bohInfo;
	private ArrayList<TspBohRelationObj> tsp_boh_info;
	
	
	TspBohRelation(Tab_boh bohInfo, ArrayList<TspBohRelationObj> tsp_boh_info){
		this.bohInfo=bohInfo;
		this.tsp_boh_info=tsp_boh_info;
	}
	
	public Tab_boh getBohInfo() {
		return bohInfo;
	}
	public void setBohInfo(Tab_boh bohInfo) {
		this.bohInfo = bohInfo;
	}
	public ArrayList<TspBohRelationObj> getTsp_boh_info() {
		return tsp_boh_info;
	}
	public void setTsp_boh_info(ArrayList<TspBohRelationObj> tsp_boh_info) {
		this.tsp_boh_info = tsp_boh_info;
	}
	

}
