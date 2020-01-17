package com.ZOHO.ZOHO_ShedularApp.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	public static Connection getConnection() {
		String jdbcURL = "jdbc:mysql://localhost:3306/ZOHO_Calender?useSSL=false"; 
        // Database name to access 
        String jdbcUsername = "sai"; 
        String jdbcPassword = "sai123"; 
        Connection con=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}
		catch(Exception e)
		{
			System.out.print("no con ");
			e.printStackTrace();
		}
		return con;
	}
}

