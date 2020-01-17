package com.ZOHO.ZOHO_ShedularApp.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.ZOHO.ZOHO_ShedularApp.model.AlarmModel;
import com.ZOHO.ZOHO_ShedularApp.model.UserModel;
import com.ZOHO.ZOHO_ShedularApp.service.AlarmService;
import com.ZOHO.ZOHO_ShedularApp.web.dao.AlarmDao;

public class UserNotifications {
	UserModel user=new UserModel();
	public ArrayList<AlarmModel> alarmList;
	public static ArrayList<Integer> dates;
	int targetMonth;
	int targetYear;
	
	public int getTargetMonth() {
		return targetMonth;
	}

	public void setTargetMonth(int targetMonth) {
		this.targetMonth = targetMonth;
	}

	public int getTargetYear() {
		return targetYear;
	}

	public void setTargetYear(int targetYear) {
		this.targetYear = targetYear;
	}

	public List<AlarmModel> getUserNotifications(){
		alarmList=new ArrayList<>();
		AlarmDao alarmDao=new AlarmDao();
		ArrayList<Date> twoDates=generateDates();
		alarmList=(ArrayList<AlarmModel>) alarmDao.getNotifications(1,AlarmService.convertUtilDateToSql(twoDates.get(0)),AlarmService.convertUtilDateToSql(twoDates.get(1)));
		return alarmList;
	}
	
	private ArrayList<Date> generateDates() {
		// TODO Auto-generated method stub
		ArrayList<Date> twoDates=new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.MONTH, targetMonth);
	    cal.set(Calendar.YEAR, targetYear);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    twoDates.add(cal.getTime());
	    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	    twoDates.add(cal.getTime());
		return twoDates;
	}

	public ArrayList<Integer> getUserNotificationDates() {
		dates=new ArrayList<>();
		for(AlarmModel am:alarmList) {
			String[] tokens=am.getTarget().split("-");
			String[] tokensTokens=tokens[2].split(" ");
			dates.add(Integer.parseInt(tokensTokens[0]));
		}
		return dates;
	}
	
	public void fetchNotificationDates(int targetMonth,int targetYear) {
		this.targetMonth=targetMonth;
		this.targetYear=targetYear;
		getUserNotifications();
		getUserNotificationDates();
	}
	
	public int checkDate(int checkDate) {
		
		int flag=0;
		for(int date:dates) {
			
			if(date==checkDate) {
				flag=1;
			}
		}
		return flag;
		
	}

}
