package com.ZOHO.ZOHO_ShedularApp.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.ZOHO.ZOHO_ShedularApp.model.AlarmModel;
import com.ZOHO.ZOHO_ShedularApp.model.UserModel;
import com.ZOHO.ZOHO_ShedularApp.service.AlarmService;
import com.ZOHO.ZOHO_ShedularApp.web.dao.UserDAO;
@Path("/login")
public class CheckLoginResource {
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Produces(MediaType.APPLICATION_ATOM_XML)
	public Response checkCredentials(@FormParam("uName") String uName,@FormParam("password") String pwd) throws URISyntaxException {
		UserDAO userDAO=new UserDAO();
		UserModel user=userDAO.validateUser(uName, pwd);
		if(user.getUserID()!=0) {
			URI targetURIForRedirection = new URI("http://localhost:8080/ZOHO_ShedularApp/alarm.jsp");
			Response
            .ok()
            .cookie(new NewCookie("id", user.getUserID()+""))
            .build();
		    return Response.temporaryRedirect(targetURIForRedirection).build();

		}else {
			System.out.println("invalid");
		}
		return null;
	}
	
	
}

