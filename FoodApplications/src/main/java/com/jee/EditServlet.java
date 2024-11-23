package com.jee;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jee.dbutil.MyConnection;
 
 
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet 
{
	private Connection con;
	private PreparedStatement pstmt;
	private int status;
	private PrintWriter pw;
 
	private static final String EDIT_QUERY = "update `user` set mobile=? where email=? ";
	

	@Override
	public void init() throws ServletException 
	{
		con = MyConnection.connect();

	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		 pw = resp.getWriter();
		 
		 String mobile = req.getParameter("mobile");
		 String email = (String) req.getSession().getAttribute("email");
				 		 
//		 HttpSession session = req.getSession();	 
//		 String email = (String) session.getAttribute("email"); 
		 
		 String session_name = (String) req.getSession().getAttribute("name");
		 Cookie[] cookies = req.getCookies();
		 String Cookie_name = cookies[0].getValue();		 
		  
 		try 
		{
 			if(session_name.equals(Cookie_name))
 			{
 			
 				
				pstmt = con.prepareStatement(EDIT_QUERY);
				pstmt.setString(1, mobile);
				pstmt.setString(2, email);
				 
				status = pstmt.executeUpdate();
	 			
				if (status != 0) {
					resp.sendRedirect("success.html");
				} else {
					resp.sendRedirect("failure.html");
				}
				
	
 			}
 			else
 			{
 				resp.getWriter().println("Name and Cookie name does not match / Boss your session is over Relogin");
 			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	  
} 
