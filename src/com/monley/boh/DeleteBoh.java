package com.monley.boh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.monley.db.Daodb;
import com.monley.db.Tab_tspserver_boh_relation;
import com.monley.result.ResultSummary;

/**
 * Servlet implementation class DeleteBoh
 */
@WebServlet("/deleteboh")
public class DeleteBoh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBoh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out= response.getWriter();
		
		ResultSummary result = new ResultSummary();
		Gson gson = new Gson();
		
		int id= Integer.parseInt(request.getParameter("id"));
		
		Daodb db = new Daodb();
		try {
			db.modify("delete from mobcenter.Boh where ID = ?", id);
			db.modify("delete from mobcenter.TspServer_Boh_relation where Boh_ID =?", id);
			result.setSuccess(true);
			result.setInfo("delete success");
			String jsonStr=gson.toJson(result);
			out.print(jsonStr);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			result.setSuccess(false);
			result.setInfo("delete fail,can not connect sql");
			String jsonStr=gson.toJson(result);
			out.print(jsonStr);
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
