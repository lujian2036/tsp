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
import com.monley.bean.TspServerInformation;
import com.monley.db.Daodb;
import com.monley.db.Tab_TreeView;
import com.monley.db.Tab_tspserver;

/**
 * Servlet implementation class TreeViewInformation
 */
@WebServlet("/treeviewinformation")
public class TreeViewInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TreeViewInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		TreeViewInformationBean totalTspInformation = new TreeViewInformationBean();
		
		Daodb  db = new Daodb();
		try {
			ResultSet rs=db.query("select count(1) from mobcenter.TreeView");
			if(rs.next()){
				totalTspInformation.setTotal(rs.getInt(1));
			}
			
			rs = db.query("Select ID,Name,Description from mobcenter.TreeView ");
			ArrayList<Tab_TreeView> listTsp=new ArrayList<>();
			while(rs.next()){
				Tab_TreeView tsp=new Tab_TreeView();
				tsp.setId(rs.getInt("ID"));
				tsp.setName(rs.getString("Name"));
				tsp.setDesc(rs.getString("Description"));
				listTsp.add(tsp);
			}
			totalTspInformation.setRows(listTsp);
			Gson json = new Gson();
			String strTsp=json.toJson(totalTspInformation);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
