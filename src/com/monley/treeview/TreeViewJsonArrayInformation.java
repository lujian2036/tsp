package com.monley.treeview;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.monley.bean.TreeViewInformationBean;
import com.monley.db.Daodb;
import com.monley.db.Tab_TreeView;

/**
 * Servlet implementation class TreeViewJsonArrayInformation
 */
@WebServlet("/treeviewJsonarrayinformation")
public class TreeViewJsonArrayInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				PrintWriter out =response.getWriter();

				Daodb  db = new Daodb();
				try {
					
					ResultSet rs = db.query("Select ID,Name,Description from mobcenter.TreeView ");
					ArrayList<Tab_TreeView> listTsp=new ArrayList<>();
					while(rs.next()){
						Tab_TreeView tsp=new Tab_TreeView();
						tsp.setId(rs.getInt("ID"));
						tsp.setName(rs.getString("Name"));
						tsp.setDesc(rs.getString("Description"));
						listTsp.add(tsp);
					}
					
					Gson json = new Gson();
					String strTsp=json.toJson(listTsp);
					out.print(strTsp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						db.closeConn();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	}

}
