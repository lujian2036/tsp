package com.monley.boh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.monley.bean.BohTspMapInformation;
import com.monley.db.Daodb;


/**
 * Servlet implementation class GetBohTspInformation
 */
@WebServlet("/getBohTspInformation")
public class GetBohTspInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBohTspInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		BohTspMapInformation bohTspMapInformation = new BohTspMapInformation();
		ArrayList<HashMap<String, Object>> tspBohRelationAll = new ArrayList<>();
			
		
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		int offset=((page-1)*rows)>=0?((page-1)*rows):1;
		
		Daodb  db = new Daodb();
		
		//get boh
		try {
			ResultSet rs = db.query("select ID,Name,BohName,BohMethod,BohRoutePath,BohParameter,ParameterDecode,ReturnDecode,SampleTxt,TreeViewID from mobcenter.Boh order by id desc limit ?,? ",offset,rows);
			while(rs.next()){
				HashMap<String, Object> bohTmp=new HashMap<>();
				bohTmp.put("ID",rs.getInt("ID"));
				bohTmp.put("Name",rs.getString("Name"));
				bohTmp.put("BohName",rs.getString("BohName"));
				bohTmp.put("BohMethod",rs.getString("BohMethod"));
				bohTmp.put("BohRoutePath",rs.getString("BohRoutePath"));
				bohTmp.put("BohParameter",rs.getString("BohParameter"));
				bohTmp.put("ParameterDecode",rs.getInt("ParameterDecode"));
				bohTmp.put("ReturnDecode",rs.getInt("ReturnDecode"));
				bohTmp.put("SampleTxt",rs.getString("SampleTxt"));
				bohTmp.put("treeviewid", rs.getString("TreeViewID"));
				
				
				ResultSet rs_tsp=db.query("select mobcenter.Tsp_server.ID as ID, Name , ServiceHost , NoteInformation from mobcenter.Tsp_server join mobcenter.TspServer_Boh_relation on mobcenter.Tsp_server.ID = mobcenter.TspServer_Boh_relation.Tsp_server_ID where mobcenter.TspServer_Boh_relation.Boh_ID=?",rs.getInt("ID"));
				while(rs_tsp.next()){
					String tsp_id="tsp_"+rs_tsp.getInt("ID");
					String tsp_name_id="tsp_name_"+rs_tsp.getInt("ID");
					bohTmp.put(tsp_id,rs_tsp.getString("ServiceHost"));
					bohTmp.put(tsp_name_id, rs_tsp.getString("Name"));
					}
				tspBohRelationAll.add(bohTmp);
			}
			rs=db.query("select count(1) from mobcenter.Boh");
			if(rs.next()){
				bohTspMapInformation.setTotal(rs.getInt(1));
			}
			bohTspMapInformation.setRows(tspBohRelationAll);
			
			Gson gson=new Gson();
			String strJson=gson.toJson(bohTspMapInformation);
			out.print(strJson);
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
