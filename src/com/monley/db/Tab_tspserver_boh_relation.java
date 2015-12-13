package com.monley.db;

public class Tab_tspserver_boh_relation {
	private int ID;
	private int Boh_ID;
	private int Tsp_server_ID;
	private String Tsp_server_name="";
	public String getTsp_server_name() {
		return Tsp_server_name;
	}
	public void setTsp_server_name(String tsp_server_name) {
		Tsp_server_name = tsp_server_name;
	}
	private String ServiceHost;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getBoh_ID() {
		return Boh_ID;
	}
	public void setBoh_ID(int boh_ID) {
		Boh_ID = boh_ID;
	}
	public int getTsp_server_ID() {
		return Tsp_server_ID;
	}
	public void setTsp_server_ID(int tsp_server_ID) {
		Tsp_server_ID = tsp_server_ID;
	}
	public  String getServiceHost() {
		return ServiceHost;
	}
	public void setServiceHost( String serviceHost) {
		ServiceHost = serviceHost;
	}

}
