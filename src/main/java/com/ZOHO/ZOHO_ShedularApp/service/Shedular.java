package com.ZOHO.ZOHO_ShedularApp.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.ZOHO.ZOHO_ShedularApp.model.AlarmModel;
import com.ZOHO.ZOHO_ShedularApp.web.dao.AlarmDao;

public class Shedular implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		AlarmService as=new AlarmService();
		AlarmDao alarmDao=new AlarmDao();
		while(true) {
			System.out.println("fetching");
			java.sql.Timestamp target1=AlarmService.convertUtilDateToSql(new Date());
			java.sql.Timestamp target2=AlarmService.convertUtilDateToSql(new Date(new Date().getTime()+59000));
			ArrayList<AlarmModel> alarmList=(ArrayList<AlarmModel>) alarmDao.getNotifications(target1,target2);
			for(AlarmModel am:alarmList){
				try {
					as.process(am);
					alarmDao.deleteAlarm(am);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("off to sleep");
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
