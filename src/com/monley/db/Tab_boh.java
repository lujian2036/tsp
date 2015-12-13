package com.monley.db;

public class Tab_boh {
	private int ID;
	private String Name;
	private String BohName;
	private String BohMethod;
	private String BohRoutePath;
	private String BohParameter;
	private int ParameterDecode=1;
	private int ReturnDecode=1;
	private String SampleTxt;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBohName() {
		return BohName;
	}
	public void setBohName(String bohName) {
		BohName = bohName;
	}
	public String getBohMethod() {
		return BohMethod;
	}
	public void setBohMethod(String bohMethod) {
		BohMethod = bohMethod;
	}
	public String getBohRoutePath() {
		return BohRoutePath;
	}
	public void setBohRoutePath(String bohRoutePath) {
		BohRoutePath = bohRoutePath;
	}
	public String getBohParameter() {
		return BohParameter;
	}
	public void setBohParameter(String bohParameter) {
		BohParameter = bohParameter;
	}
	public int getParameterDecode() {
		return ParameterDecode;
	}
	public void setParameterDecode(int parameterDecode) {
		ParameterDecode = parameterDecode;
	}
	public int getReturnDecode() {
		return ReturnDecode;
	}
	public void setReturnDecode(int returnDecode) {
		ReturnDecode = returnDecode;
	}
	public String getSampleTxt() {
		return SampleTxt;
	}
	public void setSampleTxt(String sampleTxt) {
		SampleTxt = sampleTxt;
	}
	
}
