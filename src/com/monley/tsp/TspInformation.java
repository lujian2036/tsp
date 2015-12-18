package com.monley.tsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.GapContent;

import com.google.gson.Gson;
import com.monley.bean.TspServerInformation;
import com.monley.db.Daodb;
import com.monley.db.Tab_tspserver;

/**
 * Servlet implementation class TspInformation
 */
@WebServlet("/tspinformation")
public class TspInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TspInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out =response.getWriter();
		TspServerInformation totalTspInformation = new TspServerInformation();
		
		
		
		
		Daodb  db = new Daodb();
		try {
			ResultSet rs=db.query("select count(1) from mobcenter.Tsp_server");
			if(rs.next()){
				totalTspInformation.setTotal(rs.getInt(1));
			}
			
			rs = db.query("Select ID,Name,NoteInformation from mobcenter.Tsp_server ");
			ArrayList<Tab_tspserver> listTsp=new ArrayList<>();
			while(rs.next()){
				Tab_tspserver tsp=new Tab_tspserver();
				tsp.setId(rs.getInt("ID"));
				tsp.setName(rs.getString("Name"));
				tsp.setNoteInformation(rs.getString("NoteInformation"));
				listTsp.add(tsp);
			}
			totalTspInformation.setRows(listTsp);
			Gson json = new Gson();
			String strTsp=json.toJson(totalTspInformation);
			out.print(strTsp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
