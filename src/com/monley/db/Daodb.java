package com.monley.db;

import java.sql.*;

/**
 * Description:
 * <br/>锟斤拷站: <a href="http://www.crazyit.org">锟斤拷锟絁ava锟斤拷锟斤拷</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class Daodb
{
	private Connection conn;
	private String driver="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/mobcenter";
	private String username="root";
	private String pass="security";
	public Daodb()
	{
	}
	public Daodb(String driver , String url 
		, String username , String pass)
	{
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pass = pass; 
	}
	//锟斤拷锟斤拷锟角革拷锟斤拷锟斤拷员锟斤拷锟皆碉拷setter锟斤拷getter锟斤拷锟斤拷
	public void setDriver(String driver) {
		this.driver = driver; 
	}
	public void setUrl(String url) {
		this.url = url; 
	}
	public void setUsername(String username) {
		this.username = username; 
	}
	public void setPass(String pass) {
		this.pass = pass; 
	}
	public String getDriver() {
		return (this.driver); 
	}
	public String getUrl() {
		return (this.url); 
	}
	public String getUsername() {
		return (this.username); 
	}
	public String getPass() {
		return (this.pass); 
	}
	//锟斤拷取锟斤拷锟捷匡拷锟斤拷锟斤拷
	public Connection getConnection() throws Exception
	{
		if (conn == null)
		{
			Class.forName(this.driver);
			conn = DriverManager.getConnection(url,username,
				this. pass);
		}
		return conn;
	}
	//锟斤拷锟斤拷锟铰�
	public int insert(String sql , Object... args)
		throws Exception
	{
		int key=-1;
		ResultSet rs=null;
		PreparedStatement pstmt = getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		if (pstmt.executeUpdate() != 1)
		{
			return key;
		}
		rs=pstmt.getGeneratedKeys();
		if(rs.next()){
			key=rs.getInt(1);
		}
		pstmt.close();
		return key;
	}
	//执锟叫诧拷询
	public ResultSet query(String sql , Object... args)
		throws Exception
	{
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		return pstmt.executeQuery();
	}
	//执锟斤拷锟睫革拷
	public void modify(String sql , Object... args)
		throws Exception
	{
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		pstmt.executeUpdate();
		pstmt.close();
	}
	//count sum
/*	public int count(String sql){
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.execute();
		pstmt.get
	}*/
	
	//锟截憋拷锟斤拷锟捷匡拷锟斤拷锟接的凤拷锟斤拷
	public void closeConn()
		throws Exception
	{
		if (conn != null && !conn.isClosed())
		{
			conn.close();
		}
	}
}