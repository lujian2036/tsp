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
import com.monley.db.Daodb;
import com.monley.db.Tab_boh;
import com.monley.db.Tab_tspserver_boh_relation;

import javafx.scene.control.Tab;

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
		ArrayList<Tab_boh> bohList = new ArrayList<>();
		
		ArrayList<TspBohRelation> tspBohRelationAll = new ArrayList<>();
		
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		int offset=((page-1)*rows)>=0?((page-1)*rows):1;
		
		Daodb  db = new Daodb("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mobcenter", "root", "security");
		
		//get boh
		try {
			ResultSet rs = db.query("select ID,Name,BohName,BohMethod,BohRoutePath,BohParameter,ParameterDecode,ReturnDecode,SampleTxt from mobcenter.Boh limit ?,?",offset,rows);
			while(rs.next()){
				Tab_boh bohTmp=new Tab_boh();
				bohTmp.setID(rs.getInt("ID"));
				bohTmp.setName(rs.getString("name"));
				bohTmp.setBohName(rs.getString("bohName"));
				bohTmp.setBohMethod(rs.getString("BohMethod"));
				bohTmp.setBohRoutePath(rs.getString("BohRoutePath"));
				bohTmp.setBohParameter(rs.getString("BohParameter"));
				bohTmp.setParameterDecode(rs.getInt("ParameterDecode"));
				bohTmp.setReturnDecode(rs.getInt("ReturnDecode"));
				bohTmp.setSampleTxt(rs.getString("SampleTxt"));
				bohList.add(bohTmp);
			}
			
			//get tsp information join boh_id
			for (Tab_boh e : bohList) {
				ArrayList<TspBohRelationObj> tspBohRelation =new ArrayList<>();
				System.out.printf("the id is : %d \r\n" ,e.getID());
				rs=db.query("select mobcenter.Tsp_server.ID as ID, Name , ServiceHost , NoteInformation from mobcenter.Tsp_server join mobcenter.TspServer_Boh_relation on mobcenter.Tsp_server.ID = mobcenter.TspServer_Boh_relation.Tsp_server_ID where mobcenter.TspServer_Boh_relation.Boh_ID=?", e.getID());
				while(rs.next()){
					TspBohRelationObj tbr=new TspBohRelationObj();
					tbr.setTsp_server_ID(rs.getInt("ID"));
					tbr.setTsp_server_name(rs.getString("Name"));
					tbr.setTsp_ServiceHost(rs.getString("ServiceHost"));
					tspBohRelation.add(tbr);
					System.out.printf("ths tsp is %s \r\n", tbr.getTsp_server_ID()+tbr.getTsp_server_name()+tbr.getTsp_ServiceHost());
				}
				tspBohRelationAll.add(new TspBohRelation(e,tspBohRelation));
				
			}
			GetAllTspBohInfor getAllTspBohInfor = new GetAllTspBohInfor();
			rs=db.query("select count(1) from mobcenter.Boh");
			if(rs.next()){
				getAllTspBohInfor.setTotal(String.valueOf(rs.getInt(1)));
			}
			getAllTspBohInfor.setRows(tspBohRelationAll);
			Gson gson=new Gson();
			String strJson=gson.toJson(getAllTspBohInfor);
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
