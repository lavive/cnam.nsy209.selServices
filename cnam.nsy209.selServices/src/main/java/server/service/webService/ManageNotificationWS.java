package server.service.webService;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import server.dao.NotificationsMemberDao;
import server.dao.entity.NotificationEntity;
import server.service.transform.EntityToJSON;


@Path("/Notifications")
public class ManageNotificationWS {
	
	@Inject
	private NotificationsMemberDao notificationsMemberDao;
	
	@GET
	@Path("/members/{id}/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveMyNotifications(@PathParam("id") int memberId){
		
		List<NotificationEntity> notifications = notificationsMemberDao.getNotifications(memberId);
		
		JSONObject jObject = new JSONObject();

		JSONArray jArray = new JSONArray();
		for (NotificationEntity notification : notifications) {
			JSONObject notificationJSON = EntityToJSON.notificationEntityToJSON(notification);
			jArray.put(notificationJSON);
		}
		
		jObject.put("member_"+String.valueOf(memberId)+"_Notifications", jArray);
		
		return Response.status(200).entity(jObject).build();//null;
	}
	
	@DELETE
	@Path("/members/{memberId}/notifications/delete/{notificationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteNotification(@PathParam("memberId") int memberId, @PathParam("notificationId") int notificationId){
		
		notificationsMemberDao.deleteNotification(memberId, notificationId);
		
		return Response.ok().build();
	}

}
