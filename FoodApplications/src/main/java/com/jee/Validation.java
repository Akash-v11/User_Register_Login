package com.jee;
 
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
@WebServlet("/validation")
public class Validation extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String Password = req.getParameter("password");
		String ConfirmPassword = req.getParameter("confirmPassword");

		PrintWriter pw = resp.getWriter();
		
		if(Password.equals(ConfirmPassword)==true)
		{
//			resp.sendRedirect("registerData");
			 
			pw.println("Password matches"); 
		    RequestDispatcher rd = req.getRequestDispatcher("registerData");
		    rd.include(req, resp); 
		    	
		}
		else
	    {
//	    	resp.sendRedirect("pwdmismatch.html");
			
			pw.println("Password do not match"); 
 	    }
	}
} 
