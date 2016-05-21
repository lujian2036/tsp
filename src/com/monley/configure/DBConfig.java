package com.monley.configure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.monley.bean.DBConfigureBean;

public class DBConfig {
	

	public void path(){
		String pathString =System.getProperty("user.dir");
		String str1 =this.getClass().getClassLoader().getResource("/").getPath();
		
	}
	
	public String getGsonDbConfString() {
		
		String dbConfPathString = Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
		/*System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch"));
		System.out.println(System.getProperty("os.version"));*/
		if (System.getProperty("os.name").contains("indows")) {
			dbConfPathString=dbConfPathString.substring(1);
		}
		//System.out.println(dbConfPathString);
		
		try {
			File file =new File(dbConfPathString + "db.conf");
			byte[] cbuf = new byte[new Long(file.length()).intValue()];
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(cbuf);
			inputStream.close();
			this.setGsonDbConfString(new String(cbuf));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return gsonDbConfString;
		
	}
	
	public DBConfigureBean getDBConfigure(){
		String gsonDbConfigString= this.getGsonDbConfString();
		DBConfigureBean dbConfigureBean = new DBConfigureBean();
		Gson gson = new Gson();
		JsonObject jsonObject =gson.fromJson(gsonDbConfigString, new TypeToken<JsonObject>(){}.getType());
		dbConfigureBean.setDataBase_IP(jsonObject.get("DataBase_IP").getAsString());
		dbConfigureBean.setDataBase_Port(jsonObject.get("DataBase_Port").getAsString());
		dbConfigureBean.setDataBase_Name(jsonObject.get("DataBase_Name").getAsString());
		dbConfigureBean.setDataBase_User(jsonObject.get("DataBase_User").getAsString());
		dbConfigureBean.setDataBase_Pwd(jsonObject.get("DataBase_Pwd").getAsString());
		
		return dbConfigureBean;
		
	}

	public void setGsonDbConfString(String gsonDbConfString) {
		this.gsonDbConfString = gsonDbConfString;
	}

	private String gsonDbConfString;
	
	

}
