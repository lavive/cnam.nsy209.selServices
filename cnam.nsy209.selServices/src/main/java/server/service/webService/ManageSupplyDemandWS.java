package server.service.webService;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import server.dao.SupplyDemandDao;
import server.dao.entity.SupplyDemandEntity;
import server.service.transform.EntityToJSON;
import server.service.transform.JSONToEntity;


@Path("/SupplyDemands")
public class ManageSupplyDemandWS {
	
	@Inject
	SupplyDemandDao supplyDemandDao;
	
//	@GET
//	@Path("/member/{id}/get/supplyDemands/all")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response retrieveAllMySupplyDemands(@PathParam("id") int memberId){
//		
//		List<SupplyDemandEntity> mySupplyDemands = supplyDemandDao.getSupplyDemand(memberId);
//		
//		JSONObject jObject = new JSONObject();
//
//		JSONArray jArray = new JSONArray();
//		for (SupplyDemandEntity supplyDemand : mySupplyDemands) {
//			JSONObject supplyDemandJSON = EntityToJSON.supplyDemandEntityToJSON(supplyDemand);
//			jArray.put(supplyDemandJSON);
//		}
//		
//		jObject.put("member_"+String.valueOf(memberId)+"_SupplyDemands", jArray);
//		
//		return null;
//	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveSupplyDemand(@PathParam("id") int supplyDemandId){
		
		SupplyDemandEntity mySupplyDemand = supplyDemandDao.getSupplyDemandById(supplyDemandId);
		
		JSONObject jObject = EntityToJSON.supplyDemandEntityToJSON(mySupplyDemand);
		
		//jObject.put("SupplyDemand_"+String.valueOf(supplyDemandId), jObject);
		
		return Response.status(200).entity(jObject).build();//null;
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSupplyDemand(String supplyDemand){
		
		JSONObject jObject = new JSONObject(supplyDemand);
		supplyDemandDao.create(JSONToEntity.supplyDemandJSONToEntity(jObject));
		
		
		return Response.ok().build();
	}
	
	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSupplyDemand(String supplyDemand){
		
		JSONObject jObject = new JSONObject(supplyDemand);
		supplyDemandDao.update(JSONToEntity.supplyDemandJSONToEntity(jObject));
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSupplyDemand(@PathParam("id") int supplyDemandId){
		supplyDemandDao.delete(supplyDemandDao.getSupplyDemandById(supplyDemandId));
		
		return Response.ok().build();
	}
	
	@GET
	@Path("/all")
	public Response retrieveAllSupplyDemands(){
		
		List<SupplyDemandEntity> supplyDemands = supplyDemandDao.getAllSupplyDemand();
		JSONObject jObject = new JSONObject();

		JSONArray jArray = new JSONArray();
		for (SupplyDemandEntity supplyDemand : supplyDemands) {
			JSONObject supplyDemandJSON = EntityToJSON.supplyDemandEntityToJSON(supplyDemand);
			jArray.put(supplyDemandJSON);
		}

		jObject.put("supplyDemands", jArray);
		
		return Response.status(200).entity(jObject).build();//null;
	}


}
