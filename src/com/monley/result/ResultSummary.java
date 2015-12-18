package com.monley.result;

public class ResultSummary {
	private boolean success;
	private String info;
	
	public ResultSummary() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultSummary(boolean success,String info){
		this.success=success;
		this.info=info;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	

}
