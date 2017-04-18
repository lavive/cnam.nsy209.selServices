package server.service.transform;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import server.dao.constantes.EnumSupplyDemand;
import server.dao.entity.MemberEntity;
import server.dao.entity.MessageEntity;
import server.dao.entity.PaymentEntity;
import server.dao.entity.PersonEntity;
import server.dao.entity.SupplyDemandEntity;
import server.dao.entity.WealthSheetEntity;

public class JSONToEntity {
	
	public static SupplyDemandEntity supplyDemandJSONToEntity(JSONObject jObject ){
		SupplyDemandEntity supplyDemandEntity = new SupplyDemandEntity();
		
		supplyDemandEntity.setId(jObject.getInt("id"));
		supplyDemandEntity.setCategory(jObject.getString("category"));
		supplyDemandEntity.setTitle(jObject.getString("title"));
		supplyDemandEntity.setType(EnumSupplyDemand.getByWording(jObject.getString("type")));
		//SupplyDemandEntityjObject.put("member",memberEntityToJSON(SupplyDemandEntity.getMember()));
		
		return supplyDemandEntity;
	}
	
	public static MemberEntity memberJSONToEntity(JSONObject jObject ){
		MemberEntity memberEntity = new MemberEntity();
		
		memberEntity.setId(jObject.getInt("id"));
		memberEntity.setAddress(jObject.getString("address"));
		memberEntity.setCellNumber(jObject.getString("cellNumber"));
		memberEntity.setEmail(jObject.getString("email"));
		memberEntity.setForname(jObject.getString("forname"));
		memberEntity.setName(jObject.getString("name"));
		memberEntity.setPhoneNumber(jObject.getString("phoneNumber"));
		memberEntity.setTown(jObject.getString("town"));
		memberEntity.setMobileId(jObject.getString("mobileId"));
		
		memberEntity.setWealthSheet(wealthSheetJSONToEntity(jObject.getJSONObject("wealthSheet")));
		
		JSONArray jArray = jObject.getJSONArray("supplyDemand");
		List<SupplyDemandEntity> supplyDemands = new ArrayList<SupplyDemandEntity>();
		for(int i = 0 ; i < jArray.length(); i++){
			SupplyDemandEntity supplyDemandEntity = supplyDemandJSONToEntity((JSONObject) jArray.get(i));
			supplyDemands.add(supplyDemandEntity);
		}
		memberEntity.setSupplyDemand(supplyDemands);
		
		
		return memberEntity;
	}
	
	public static WealthSheetEntity wealthSheetJSONToEntity(JSONObject jObject ){
		WealthSheetEntity wealthSheetEntity = new WealthSheetEntity();
		
		wealthSheetEntity.setId(jObject.getInt("id"));
		wealthSheetEntity.setInitialAccount(jObject.getBigDecimal("initialAccount"));
		wealthSheetEntity.setFinalAccount(jObject.getBigDecimal("finalAccount"));		
		
//		JSONObject jObjectMember = memberEntityToJSON(WealthSheetEntity.getMember());
//		jObject.put("member",jObjectMember);
		
		JSONArray jArray = jObject.getJSONArray("payments");
		List<PaymentEntity> payments = new ArrayList<PaymentEntity>();
		for(int i = 0 ; i < jArray.length(); i++){
			PaymentEntity payment = paymentJSONToEntity((JSONObject) jArray.get(i));
			payments.add(payment);
		}
		
		wealthSheetEntity.setPayments(payments);
		
		return wealthSheetEntity;
	}
	
	public static PaymentEntity paymentJSONToEntity(JSONObject jObject){
		PaymentEntity paymentEntity = new PaymentEntity();
		
		paymentEntity.setId(jObject.getInt("id"));
		paymentEntity.setAmount(jObject.getBigDecimal("amount"));
		
		paymentEntity.setCreditorMember(memberJSONToEntity(jObject.getJSONObject("creditorMember")));
		
		paymentEntity.setDebtorMember(memberJSONToEntity(jObject.getJSONObject("debtorMember")));
		
		paymentEntity.setSupplyDemand(supplyDemandJSONToEntity(jObject.getJSONObject("supplyDemand")));
		
		return paymentEntity;
	}
	
	public static MessageEntity messageJSONToEntity(JSONObject jObject){
		MessageEntity messageEntity = new MessageEntity();
		
		messageEntity.setId(jObject.getInt("id"));
		messageEntity.setState(jObject.getBoolean("state"));
		messageEntity.setText(jObject.getString("text"));
		messageEntity.setTitle(jObject.getString("title"));
		messageEntity.setTransmitterPerson(personJSONToEntity(jObject.getJSONObject("transmitterPerson")));
		
		return messageEntity;
	}
	
	public static PersonEntity personJSONToEntity(JSONObject jObject ){
		PersonEntity personEntity = new PersonEntity();
		
		personEntity.setId(jObject.getInt("id"));
		personEntity.setAddress(jObject.getString("address"));
		personEntity.setCellNumber(jObject.getString("cellNumber"));
		personEntity.setEmail(jObject.getString("email"));
		personEntity.setName(jObject.getString("name"));
		personEntity.setPhoneNumber(jObject.getString("phoneNumber"));
		personEntity.setTown(jObject.getString("town"));
		
		
		return personEntity;
	}
	


}
