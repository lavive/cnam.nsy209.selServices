package server.service.transform;

import org.json.JSONArray;
import org.json.JSONObject;

import server.dao.entity.MemberEntity;
import server.dao.entity.NotificationEntity;
import server.dao.entity.NotificationTopicEntity;
import server.dao.entity.PaymentEntity;
import server.dao.entity.PersonEntity;
import server.dao.entity.SupplyDemandEntity;
import server.dao.entity.WealthSheetEntity;

public class EntityToJSON {
	
	public static JSONObject supplyDemandEntityToJSON(SupplyDemandEntity SupplyDemandEntity){
		JSONObject jObject = new JSONObject();
		
		jObject.put("id",SupplyDemandEntity.getId());
		jObject.put("category",SupplyDemandEntity.getCategory());
		jObject.put("title",SupplyDemandEntity.getTitle());
		jObject.put("type",SupplyDemandEntity.getType());
		//jObject.put("member",memberEntityToJSON(SupplyDemandEntity.getMember()));
		
		return jObject;
	}
	
	public static JSONObject personEntityToJSON(PersonEntity personEntity){
		JSONObject jObject = new JSONObject();
		
		jObject.put("id",personEntity.getId());
		jObject.put("address",personEntity.getAddress());
		jObject.put("cellNumber",personEntity.getCellNumber());
		jObject.put("email",personEntity.getEmail());
		jObject.put("name",personEntity.getName());
		jObject.put("phoneNumber",personEntity.getPhoneNumber());
		jObject.put("town",personEntity.getTown());
		
		
		return jObject;
	}
	
	public static JSONObject memberEntityToJSON(MemberEntity memberEntity){
		JSONObject jObject = new JSONObject();
		
		jObject.put("id",memberEntity.getId());
		jObject.put("address",memberEntity.getAddress());
		jObject.put("cellNumber",memberEntity.getCellNumber());
		jObject.put("email",memberEntity.getEmail());
		jObject.put("forname",memberEntity.getForname());
		jObject.put("name",memberEntity.getName());
		jObject.put("phoneNumber",memberEntity.getPhoneNumber());
		jObject.put("town",memberEntity.getTown());
		jObject.put("mobileId",memberEntity.getMobileId());
		
		jObject.put("wealthSheet",wealthSheetEntityToJSON(memberEntity.getWealthSheet()));
		
		JSONArray jArray = new JSONArray();
		for(SupplyDemandEntity supplyDemandEntity:memberEntity.getSupplyDemand()){
			JSONObject jObjectSupplyDemand = supplyDemandEntityToJSON(supplyDemandEntity);
			jArray.put(jObjectSupplyDemand);
		}
		jObject.put("supplyDemand",jArray);
		
		
		return jObject;
	}
	
	public static JSONObject wealthSheetEntityToJSON(WealthSheetEntity WealthSheetEntity){
		JSONObject jObject = new JSONObject();
		
		jObject.put("id", WealthSheetEntity.getId());
		jObject.put("initialAccount",WealthSheetEntity.getInitialAccount());
		jObject.put("finalAccount",WealthSheetEntity.getFinalAccount());		
		
//		JSONObject jObjectMember = memberEntityToJSON(WealthSheetEntity.getMember());
//		jObject.put("member",jObjectMember);
		
		JSONArray jArray = new JSONArray();
		for (PaymentEntity paymentEntity:WealthSheetEntity.getPayments()) {
			JSONObject paymentJSON = paymentEntityToJSON(paymentEntity);
			jArray.put(paymentJSON);
		}
		jObject.put("payments", jArray);
		
		return jObject;
	}
	
	public static JSONObject paymentEntityToJSON(PaymentEntity paymentEntity){
		JSONObject jObject = new JSONObject();
		
		jObject.put("id", paymentEntity.getId());
		jObject.put("amount", paymentEntity.getAmount());
		
		JSONObject jObjectCreditorMember = memberEntityToJSON(paymentEntity.getCreditorMember());
		jObject.put("creditorMember",jObjectCreditorMember);
		
		JSONObject jObjectDebtorMember = memberEntityToJSON(paymentEntity.getDebtorMember());
		jObject.put("debtorMember",jObjectDebtorMember);
		
		JSONObject jObjectSupplyDemand = supplyDemandEntityToJSON(paymentEntity.getSupplyDemand());
		jObject.put("supplyDemand",jObjectSupplyDemand);
		
		return jObject;
	}
	
	public static JSONObject notificationEntityToJSON(NotificationEntity notificationEntity){
		JSONObject jObject = new JSONObject();
		
		jObject.put("id", notificationEntity.getId());
		jObject.put("title", notificationEntity.getTitle());
		jObject.put("text", notificationEntity.getText());
		
		JSONObject jObjectTopic = notificationTopicEntityToJSON(notificationEntity.getTopic());
		jObject.put("topic",jObjectTopic);
		
		return jObject;
	}
	
	public static JSONObject notificationTopicEntityToJSON(NotificationTopicEntity notificationTopicEntity){
		JSONObject jObject = new JSONObject();
		
		jObject.put("id", notificationTopicEntity.getId());
		jObject.put("topic", notificationTopicEntity.getTopic());
		jObject.put("category", notificationTopicEntity.getCategory());
		
		JSONObject jObjectPersonOriginEvent = personEntityToJSON(notificationTopicEntity.getPersonOriginEvent());
		jObject.put("personOriginEvent",jObjectPersonOriginEvent);
		
		return jObject;
	}

}
