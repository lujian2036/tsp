package com.monley.tsp;

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
 * Servlet implementation class AddTsp
 */
@WebServlet("/addtsp")
public class AddTsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTsp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();

		ResultSummary returnRes = new ResultSummary();
		
		String name=request.getParameter("name");
		String noteInformation=request.getParameter("noteInformation");

		Daodb  db = new Daodb();
		try {
			
			db.insert("insert into mobcenter.Tsp_server  values (null,?,?)", name,noteInformation);
			returnRes.setSuccess(true);
			returnRes.setInfo("success insert tsp data");
			Gson gson = new Gson();
			String strJson=gson.toJson(returnRes);
			out.print(strJson);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnRes.setSuccess(false);
			returnRes.setInfo("insert tsp data fail.");
			Gson gson = new Gson();
			String strJson=gson.toJson(returnRes);
			out.print(strJson);
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
