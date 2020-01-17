package com.ZOHO.ZOHO_ShedularApp.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ZOHO.ZOHO_ShedularApp.model.AlarmModel;
import com.ZOHO.ZOHO_ShedularApp.service.AlarmService;
@Path("/alarm")
public class AlarmResource {
	AlarmService as;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_ATOM_XML)
	public String setAlarm(AlarmModel am) {
		as=new AlarmService();
		System.out.println(am.getTarget());
		try {
			as.process(am);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Success";
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AlarmModel getAlarm() {
		AlarmModel am=new AlarmModel();
		System.out.println(am);
		return am;
	}
	
	
}
