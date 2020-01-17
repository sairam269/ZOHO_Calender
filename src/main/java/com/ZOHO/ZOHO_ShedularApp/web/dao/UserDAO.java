package com.ZOHO.ZOHO_ShedularApp.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ZOHO.ZOHO_ShedularApp.model.UserModel;

public class UserDAO {
Connection con=DB_Connection.getConnection();
	
	public UserModel validateUser(String userName,String pwd) {
		UserModel user=new UserModel();
        Connection con=DB_Connection.getConnection();
		try {
            String query = "select * from loginData where `uName` = ? and `pwd`=?";
            PreparedStatement pst = con.prepareStatement(query);            
            pst.setString(1, userName);
            pst.setString(2, pwd);            
            ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("uName"));
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
}
