package com.jee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 import com.jee.dbutil.MyConnection;

public class Register extends HttpServlet
{
	
	private PreparedStatement pstmt = null;
	private int status;
	private PrintWriter pw;
	private Connection con;
 
	public static final String Insert_Query = "insert into `user` (name, email, mobile, password, address) values(?,?,?,?,?)";
 	    
	@Override
	public void init() throws ServletException
	{
		con = MyConnection.connect();
		  
	}
	 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		pw = resp.getWriter();
		 
    	try 
    	{
    		String name = req.getParameter("name");
			String email = req.getParameter("email");
			String mobile = req.getParameter("mobile");
			String password = req.getParameter("password");
			String address = req.getParameter("address");
			 
			pstmt = con.prepareStatement(Insert_Query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, mobile);
			pstmt.setString(4, password);
			pstmt.setString(5, address);
			
			status = pstmt.executeUpdate();
			
			if (status != 0) {
				resp.sendRedirect("success.html");
//				pw.println("Data Inserted Successfully");
			} else {
				resp.sendRedirect("failure.html");
//				pw.println("Data was not Inserted");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 
	@Override
	public void destroy() {
		try {
			if (pstmt != null) {
				pstmt.close();
 			}
			if (con != null) {
				con.close();
 			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

