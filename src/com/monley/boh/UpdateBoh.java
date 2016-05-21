package com.monley.boh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;

import com.google.gson.Gson;
import com.monley.db.*;
import com.monley.result.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateBoh
 */
@WebServlet("/updateboh")
public class UpdateBoh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		boolean debug=true;
		PrintWriter out= response.getWriter();
		ArrayList<Tab_tspserver_boh_relation> tspRelationList= new ArrayList<>();
		ResultSummary result = new ResultSummary();
		Gson gson = new Gson();
		
		int id= Integer.parseInt(request.getParameter("id"));
		//get post boh data
		int treeviewid = Integer.parseInt(request.getParameter("treeviewid"));
		String name = request.getParameter("Name").trim();
		String bohName = request.getParameter("BohName");
		String bohMethod = request.getParameter("BohMethod");
		String bohRoutePath = request.getParameter("BohRoutePath").trim();
		String bohParameter = request.getParameter("BohParameter");
		String sampleTxt = request.getParameter("SampleTxt");
		
		//get tsp parameter
		Enumeration<String> e= request.getParameterNames();
		while(e.hasMoreElements()){
			String strTmp=e.nextElement();
			if(strTmp.startsWith("tsp_")){
				Tab_tspserver_boh_relation tspRel=new Tab_tspserver_boh_relation();
				tspRel.setTsp_server_ID(Integer.parseInt(strTmp.substring(4)));
				tspRel.setTsp_server_name(request.getParameter(strTmp));
				tspRelationList.add(tspRel);
			}
		}
		
		if(debug){
			for(Tab_tspserver_boh_relation tsp :tspRelationList){
				System.out.println(tsp.getTsp_server_ID() +" : "+ tsp.getTsp_server_name());
			}
		}
		
		Daodb db = new Daodb();
		
		try {
			db.modify("update mobcenter.Boh set Name=?,BohName=?,BohMethod=?,BohRoutePath=?,BohParameter=?,SampleTxt=? ,TreeViewID = ? where ID=?", name,bohName,bohMethod,bohRoutePath,bohParameter,sampleTxt,treeviewid,id);
			
			for(int i=0;i<tspRelationList.size();i++){
				//judge if boh tsp relation already exist, fix bug for boh tsp relation exist ,but add tsp again
				ResultSet rs=db.query("select count(1) from  mobcenter.TspServer_Boh_relation where  Boh_ID=? and Tsp_server_ID =?",  id,tspRelationList.get(i).getTsp_server_ID()); 
				rs.next();
				
				if(0==rs.getInt(1)){//not exist
					db.insert("insert into mobcenter.TspServer_Boh_relation(Boh_ID,Tsp_server_ID,ServiceHost) values (?,?,?)", 
																				id,tspRelationList.get(i).getTsp_server_ID(),tspRelationList.get(i).getTsp_server_name());
				}else{
					db.modify("update mobcenter.TspServer_Boh_relation set ServiceHost=? where Boh_ID=? and Tsp_server_ID =?", tspRelationList.get(i).getTsp_server_name(),id,tspRelationList.get(i).getTsp_server_ID());
				}
				
			}
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
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
