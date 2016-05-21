package com.monley.treeview;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.monley.db.Daodb;
import com.monley.result.ResultSummary;

/**
 * Servlet implementation class UpdateTreeView
 */
@WebServlet("/updatetreeview")
public class UpdateTreeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTreeView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out= response.getWriter();
		ResultSummary result = new ResultSummary();
		Gson gson = new Gson();
		
		int id= Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String Description=request.getParameter("desc");
		
		Daodb db = new Daodb();
		
		try {
			db.modify("update mobcenter.TreeView set Name=?,Description=? where ID=?", name,Description,id);
			
			result.setSuccess(true);
			result.setInfo("modify success");
			String jsonStr=gson.toJson(result);
			out.print(jsonStr);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			result.setSuccess(false);
			result.setInfo("modify fail,fail to connect sql");
			String jsonStr=gson.toJson(result);
			out.print(jsonStr);
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
