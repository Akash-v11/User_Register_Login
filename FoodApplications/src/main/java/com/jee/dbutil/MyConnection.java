package com.jee.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class MyConnection
{	
	private static Connection con;

	public static Connection connect()
	{
		try
		{
			String url = "jdbc:mysql://localhost:3306/octjdbc";
			String dbuser = "root";
			String dbpassword = "root";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, dbuser, dbpassword);
			  
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
}
