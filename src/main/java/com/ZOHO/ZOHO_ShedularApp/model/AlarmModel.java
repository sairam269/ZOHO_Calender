package com.ZOHO.ZOHO_ShedularApp.model;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class AlarmModel{
	int alarmID;
    public int getAlarmID() {
		return alarmID;
	}
	public void setAlarmID(int alarmID) {
		this.alarmID = alarmID;
	}
	String target="";
    String message="";
    String repeateYear="0";
    List<AlarmModel> userAlarmList=new ArrayList<>();
    public AlarmModel() {
    	target=new Date()+"";
    }
    public AlarmModel(String targetDate,String message,String repeateYear){
        this.target=targetDate;
        this.message=message;
        this.repeateYear=repeateYear;
    }
    public String getRepeateYear() {
		return repeateYear;
	}
	public void setRepeateYear(String repeateYear) {
		this.repeateYear = repeateYear;
	}
	@Override
	public String toString() {
		return "AlarmModel [target=" + target + ", message=" + message + "]";
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}