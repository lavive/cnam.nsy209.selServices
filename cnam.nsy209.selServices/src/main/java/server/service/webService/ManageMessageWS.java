package server.service.webService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import server.dao.MessageDao;
import server.service.transform.JSONToEntity;


@Path("/Messages")
public class ManageMessageWS {
	
	@Inject
	MessageDao messageDao;
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendMessage(String message){
		
		JSONObject jObject = new JSONObject(message);
		messageDao.create(JSONToEntity.messageJSONToEntity(jObject));
		
		
		return Response.ok().build();
	}

}
