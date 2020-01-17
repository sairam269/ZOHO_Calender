package com.ZOHO.ZOHO_ShedularApp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ZOHO.ZOHO_ShedularApp.model.AlarmModel;
import com.ZOHO.ZOHO_ShedularApp.web.dao.AlarmDao;

public class AlarmService implements Runnable {
	public AlarmModel alarm=null;
	public AlarmDao alarmDao =new AlarmDao();

    long diffSeconds;
    long diffMinutes;
    long diffHours;
    long diffDays;
	public AlarmService() {
		this.alarm=new AlarmModel();
	}
	
    public AlarmModel getAlarm() {
    	return this.alarm;
    }
    public void setAlarm(AlarmModel am) {
    	this.alarm=am;
    }
    public void startAlarm() {
    	Thread alarmThread=new Thread(this);
		alarmThread.start();
    }
    @Override
    public void run() {
        while(true){
            //System.out.println("sleep");
        	if(zeroTimeDifference(new Date())==0) {
        		try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AlarmModel.class.getName()).log(Level.SEVERE, null, ex);
                }
        	}else if(zeroTimeDifference(new Date())==1) {
        		System.out.println("Wake Up "+this.alarm.getMessage());
        		break;
        	}else if(zeroTimeDifference(new Date())==-1) {
        		System.out.println("breaking thread");
        		break;
        	}
            
        }
        
    }

    private int zeroTimeDifference(Date now) {
        try {

                //in milliseconds
        		long diff=this.checkDifference(now);
                if(diff<0) {
                    return -1;
                }

                if(diffSeconds==0 && diffMinutes==0 && diffHours==0 && diffDays==0){
                    return 1;
                }

        } catch (Exception e) {
                e.printStackTrace();
        }    
        return 0;
    }
    public static Date convertStringToDate(String date) {
    	SimpleDateFormat availDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	try {
			return availDate.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public static java.sql.Timestamp convertUtilDateToSql(Date date) {
    	return date == null ? null : new java.sql.Timestamp(date.getTime());
    }
    
    public long checkDifference(Date now) throws ParseException {
        long diff = convertStringToDate(this.alarm.getTarget()).getTime() - now.getTime();
        diffSeconds = diff / 1000 % 60;
        diffMinutes = diff / (60 * 1000) % 60;
        diffHours = diff / (60 * 60 * 1000) % 24;
        diffDays = diff / (24 * 60 * 60 * 1000);
        return diff;
    }
    
    public void process(AlarmModel am) throws ParseException {
    	this.setAlarm(am);
    	long diff=this.checkDifference(new Date());
    	if(diff>300000) {
    		alarmDao.storeAlarm(1,am);
    		System.out.println("storing");
    		
    	}else {
    		alarmDao.deleteAlarm(am);
    		if(am.getRepeateYear().equalsIgnoreCase("1")) {
    			am.setTarget(AlarmService.addYearToDate(am.getTarget()));
				alarmDao.storeAlarm(1, am);
    		}
    		this.startAlarm();
    	}
    }
    public static String addYearToDate(String target) {
    	String[] tokens=target.split("-");
		int year=Integer.parseInt(tokens[0]);
		year+=1;
		tokens[0]=year+"";
		String newTarget=tokens[0]+"-"+tokens[1]+"-"+tokens[2];
		return newTarget;
    }
}
