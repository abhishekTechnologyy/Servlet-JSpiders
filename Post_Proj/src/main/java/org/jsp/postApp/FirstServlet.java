package org.jsp.postApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet 
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{		
		//Fetch UI/Form Data
		
		String sid   =  req.getParameter("i");      // "1"
		int id = Integer.parseInt(sid);
		String name  =  req.getParameter("nm");    //  "Guru"
		String dept  =  req.getParameter("dp");   //   "ECE"
		String sperc =  req.getParameter("pr");  //    "78.40"
		double perc = Double.parseDouble(sperc);
		
		//Presentation Logic  //Servlet Technology
		
		PrintWriter out = resp.getWriter();         // to print on browser we use getWriter()
		out.println("<html><body bgcolor='yellow'>"
				+ "<h1>Student name is "+name+" from "+dept+"</h1>"
						+ "</body></html>");
		out.close();
		
		//Persistence Logic //JDBC Technology
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin1234");
			pstmt = con.prepareStatement("insert into btm.student values(?,?,?,?)");
			
			//set the data for placeholder
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, dept);
			pstmt.setDouble(4, perc);
			
			//execute
			pstmt.executeUpdate();
			System.out.println("Record Inserted!!");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(pstmt != null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
