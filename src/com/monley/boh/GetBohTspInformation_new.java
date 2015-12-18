package com.monley.boh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabSelectionHandler;

import com.google.gson.Gson;
import com.monley.bean.BohTspInformation;
import com.monley.bean.SingleBohTspRelation;
import com.monley.db.Daodb;
import com.monley.db.Tab_boh;
import com.monley.db.Tab_tspserver_boh_relation;

import javafx.scene.control.Tab;

/**
 * Servlet implementation class GetBohTspInformation
 */
@WebServlet("/getBohTspInformation_new")
public class GetBohTspInformation_new extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBohTspInformation_new() {
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
		BohTspInformation allBohTspInformation = new BohTspInformation();
		
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		int offset=((page-1)*rows)>=0?((page-1)*rows):1;
		
		Daodb  db = new Daodb();
		
		//get boh
		try {
			
			ResultSet rs = db.query("select ID,Name,BohName,BohMethod,BohRoutePath,BohParameter,ParameterDecode,ReturnDecode,SampleTxt from mobcenter.Boh limit ?,?",offset,rows);
			ArrayList<SingleBohTspRelation> arrSingleBohTspRelation= new ArrayList<>();
			
			while(rs.next()){
				SingleBohTspRelation bohTmp=new SingleBohTspRelation();
				bohTmp.setID(rs.getInt("ID"));
				bohTmp.setName(rs.getString("name"));
				bohTmp.setBohName(rs.getString("bohName"));
				bohTmp.setBohMethod(rs.getString("BohMethod"));
				bohTmp.setBohRoutePath(rs.getString("BohRoutePath"));
				bohTmp.setBohParameter(rs.getString("BohParameter"));
				bohTmp.setParameterDecode(rs.getInt("ParameterDecode"));
				bohTmp.setReturnDecode(rs.getInt("ReturnDecode"));
				bohTmp.setSampleTxt(rs.getString("SampleTxt"));
				
				ResultSet rs_tsp=db.query("select mobcenter.Tsp_server.ID as ID, Name , ServiceHost , NoteInformation from mobcenter.Tsp_server join mobcenter.TspServer_Boh_relation on mobcenter.Tsp_server.ID = mobcenter.TspServer_Boh_relation.Tsp_server_ID where mobcenter.TspServer_Boh_relation.Boh_ID=?", rs.getInt("ID"));
				ArrayList<TspBohRelationObj> arrTsp= new ArrayList<>();
				while(rs_tsp.next()){
					TspBohRelationObj tbr=new TspBohRelationObj();
					tbr.setTsp_server_ID(rs_tsp.getInt("ID"));
					tbr.setTsp_server_name(rs_tsp.getString("Name"));
					tbr.setTsp_ServiceHost(rs_tsp.getString("ServiceHost"));
					arrTsp.add(tbr);
				}
				bohTmp.setArrtsp(arrTsp);
				arrSingleBohTspRelation.add(bohTmp);
			}
			

			rs=db.query("select count(1) from mobcenter.Boh");
			if(rs.next()){
				allBohTspInformation.setTotal(rs.getInt(1));
			}
			allBohTspInformation.setRows(arrSingleBohTspRelation);
			Gson gson=new Gson();
			String strJson=gson.toJson(allBohTspInformation);
			out.print(strJson);
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
