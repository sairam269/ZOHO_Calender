package com.ZOHO.ZOHO_ShedularApp.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ZOHO.ZOHO_ShedularApp.model.AlarmModel;
import com.ZOHO.ZOHO_ShedularApp.model.UserModel;
import com.ZOHO.ZOHO_ShedularApp.service.AlarmService;

public class AlarmDao {
		
	public int storeAlarm(int userID,AlarmModel alarm){
		
		Connection con=DB_Connection.getConnection();
		try {
            String query = "INSERT INTO notificationTable(userID,dateTime,message,repeateYear) VALUES(?,?,?,?)";
            String[] dateTime=alarm.getTarget().split(" ");
            java.sql.Timestamp timeStamp=AlarmService.convertUtilDateToSql(AlarmService.convertStringToDate(dateTime[0]+" "+dateTime[1]));
            PreparedStatement pst = con.prepareStatement(query);   
           
            pst.setInt(1,userID); 
            pst.setTimestamp(2,timeStamp);
            pst.setString(3, alarm.getMessage());
            pst.setString(4,alarm.getRepeateYear());
            int row = pst.executeUpdate();
            if(row>=1) {
            	return 1;
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
		return 0;
	} 
	
	public List<AlarmModel> getNotifications(int userID,java.sql.Timestamp timstamp1,java.sql.Timestamp timestamp2){
		Connection con=DB_Connection.getConnection();
		PreparedStatement pst;
		String sql ="SELECT * FROM notificationTable WHERE userID=? AND dateTime BETWEEN ? AND ?";
		List<AlarmModel> alarmList=new ArrayList<>();
		AlarmModel am=new AlarmModel();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, userID);
			pst.setTimestamp(2, timstamp1);
			pst.setTimestamp(3, timestamp2);
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()) {
				am.setTarget(resultSet.getTimestamp("dateTime")+"");
				am.setAlarmID(resultSet.getInt("notificationID"));
				am.setMessage(resultSet.getString("message"));
				am.setRepeateYear(resultSet.getString("repeateYear"));
				alarmList.add(am);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return alarmList;
	}
	
	
	
	public List<AlarmModel> getNotifications(java.sql.Timestamp timstamp1,java.sql.Timestamp timestamp2){
		Connection con=DB_Connection.getConnection();
		PreparedStatement pst;
		String sql ="SELECT * FROM notificationTable WHERE dateTime BETWEEN ? AND ?";
		List<AlarmModel> alarmList=new ArrayList<>();
		AlarmModel am=new AlarmModel();
		try {
			pst = con.prepareStatement(sql);
			pst.setTimestamp(1, timstamp1);
			pst.setTimestamp(2, timestamp2);
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()) {
				int userID=resultSet.getInt("userID"); 
				am.setTarget(resultSet.getTimestamp("dateTime")+"");
				am.setAlarmID(resultSet.getInt("notificationID"));
				am.setMessage(resultSet.getString("message"));
				am.setRepeateYear(resultSet.getString("repeateYear"));
				alarmList.add(am);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return alarmList;
	}
	
	
	public void deleteAlarm(AlarmModel am) {
		Connection con=DB_Connection.getConnection();
		PreparedStatement pst;
		String sql ="DELETE FROM notificationTable WHERE notificationID =?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, am.getAlarmID());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
