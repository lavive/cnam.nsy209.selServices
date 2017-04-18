package server.service.webService;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import server.dao.MemberDao;
import server.dao.entity.SupplyDemandEntity;
import server.service.transform.EntityToJSON;
import server.service.transform.JSONToEntity;


@Path("/Members")
public class ManageProfileWS {
	
	@Inject
	MemberDao memberDao;
	
	@GET
	@Path("/{id}/get/supplyDemands/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAllMySupplyDemands(@PathParam("id") int memberId){
		
		List<SupplyDemandEntity> mySupplyDemands = memberDao.getSupplyDemands(memberId);
		
		JSONObject jObject = new JSONObject();

		JSONArray jArray = new JSONArray();
		for (SupplyDemandEntity supplyDemand : mySupplyDemands) {
			JSONObject supplyDemandJSON = EntityToJSON.supplyDemandEntityToJSON(supplyDemand);
			jArray.put(supplyDemandJSON);
		}
		
		jObject.put("member_"+String.valueOf(memberId)+"_SupplyDemands", jArray);
		
		return Response.status(200).entity(jObject).build();//null;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveMyProfile(@PathParam("id") int memberId){
		
		JSONObject jObject = EntityToJSON.memberEntityToJSON(memberDao.getMemberById(memberId));
		
		return Response.status(200).entity(jObject).build();//null;
	}
	
	@GET
	@Path("/{id}/wealthSheet")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveMyWealthSheet(@PathParam("id") int memberId){
		
		JSONObject jObject = EntityToJSON.wealthSheetEntityToJSON(memberDao.getWealthSheetEntity(memberId));
		
		return Response.status(200).entity(jObject).build();//null;
	}
	
	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMyProfile(String member){
		
		JSONObject jObject = new JSONObject(member);
		memberDao.update(JSONToEntity.memberJSONToEntity(jObject));
		
		return Response.ok().build();
	}

}
