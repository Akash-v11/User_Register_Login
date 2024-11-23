package com.jee;

 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jee.dbutil.MyConnection;
  
 
@WebServlet("/login")
public class Login extends HttpServlet
{
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private PrintWriter pw;
	private HttpSession session;
 	
	private static final String LOGIN_QUERY = "select * from `user` where email = ? ";
	
	@Override
	public void init() throws ServletException 
	{
		con = MyConnection.connect();
		 
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		pw = resp.getWriter();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try 
		{
			pstmt = con.prepareStatement(LOGIN_QUERY);
			pstmt.setString(1, email);
			
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next() == true)
			{
				String pwdFromDB = resultSet.getString("password");
				
				if(password.equals(pwdFromDB))
				{
//					pw.println("Login Success"); 
//					resp.sendRedirect("success.html");
					
//					String name = resultSet.getString("name"); 
//					pw.println("Welcome  " + name.toUpperCase() ); 
				  					
					String name	= resultSet.getString("name"); 
					String mobile = resultSet.getString("mobile"); 
					String address = resultSet.getString("address"); 
					
					
//					 COOKIES TOPIC
					
					Cookie ck1 = new Cookie("name", name);
					Cookie ck2 = new Cookie("email", email);		 	
					Cookie ck3 = new Cookie("mobile", mobile);		 	
					Cookie ck4 = new Cookie("password", password);			 	
					Cookie ck5 = new Cookie("address", address);
					
					ck1.setMaxAge(30); 
					ck2.setMaxAge(25);
					ck3.setMaxAge(20);
					ck4.setMaxAge(15);
					ck5.setMaxAge(10);
					
					resp.addCookie(ck1);
					resp.addCookie(ck2);
					resp.addCookie(ck3);
					resp.addCookie(ck4);
					resp.addCookie(ck5);
//					 COOKIES TOPIC Completed

					
					 
//					req.setAttribute("name", name);
					session = req.getSession(); 
				 
					session.setAttribute("name", name);
					session.setAttribute("email", email);
					session.setAttribute("mobile", mobile);
					session.setAttribute("password", password);
					session.setAttribute("address", address);
					
					req.getRequestDispatcher("home.jsp").forward(req, resp);
					
					
// 					resp.sendRedirect("home.jsp");			   
					
				}
				else
				{
					resp.sendRedirect("failure.html");
//					pw.println("Incorrect Password"); 					
				}
			}
			else
			{
				pw.println("Invalid user"); 					
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}



