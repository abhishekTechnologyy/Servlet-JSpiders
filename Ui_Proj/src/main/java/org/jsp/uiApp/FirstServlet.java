package org.jsp.uiApp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/fs")
public class FirstServlet extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException 
	{
		//Fetch UI/Form data
		String name = req.getParameter("nm");
		String palce = req.getParameter("pl");
		
		
		//Presentation Logic
		PrintWriter out = resp.getWriter();
		out.println("<html><body bgcolor='yellow'>"
				+ "<h1>Student name is "+name+" from "+palce+"</h1>"
						+ "</body></html>");

		out.close();
	 }
	
}
