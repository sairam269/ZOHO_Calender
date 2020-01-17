package com.ZOHO.ZOHO_ShedularApp.resources;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.ZOHO.ZOHO_ShedularApp.service.Shedular;

@Path("/startThread")
public class StartThread{
       
    
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void start() {
		System.out.println("Main Thread Started");
        // TODO Auto-generated constructor stub
        Thread mainThread=new Thread(new Shedular());
    	mainThread.start();
    }

	

}
