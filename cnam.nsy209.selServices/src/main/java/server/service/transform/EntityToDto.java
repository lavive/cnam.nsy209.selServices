package server.service.transform;

import java.util.ArrayList;

import server.dao.entity.AssociationEntity;
import server.dao.entity.CategoryEntity;
import server.dao.entity.MemberEntity;
import server.dao.entity.PaymentEntity;
import server.dao.entity.SupplyDemandEntity;
import server.dao.entity.WealthSheetEntity;
import shared.dto.AssociationDto;
import shared.dto.CategoryDto;
import shared.dto.MemberDto;
import shared.dto.PaymentDto;
import shared.dto.SupplyDemandDto;
import shared.dto.WealthSheetDto;



public class EntityToDto {
	
	public static AssociationDto associationEntityToDto(AssociationEntity associationEntity){
		AssociationDto associationDto = new AssociationDto();
		
		associationDto.setId(associationEntity.getId());
		associationDto.setName(associationEntity.getName());
		associationDto.setTown(associationEntity.getTown());
		associationDto.setAddress(associationEntity.getAddress());
		associationDto.setEmail(associationEntity.getEmail());
		associationDto.setCellNumber(associationEntity.getCellNumber());
		associationDto.setPhoneNumber(associationEntity.getPhoneNumber());
		associationDto.setWebsite(associationEntity.getPhoneNumber());
		
		return associationDto;
	}
	
//	public static CatalogDto catalogEntityToDto(CatalogEntity catalogEntity){
//		CatalogDto catalogDto = new CatalogDto();
//		
//		catalogDto.setId(catalogEntity.getId());
//		catalogDto.setModificationDate(catalogEntity.getModificationDate());
//		
//		ArrayList<GeneralOfferDto> offersDto = new ArrayList<GeneralOfferDto>();
//		for(GeneralOfferEntity offerEntity:catalogEntity.getOffers()){
//			offersDto.add(generalOfferEntityToDto(offerEntity));
//		}
//		catalogDto.setOffers(offersDto);
//		
//		return catalogDto;
//	}
	
	public static CategoryDto  categoryEntityToDto(CategoryEntity categoryEntity){
		CategoryDto categoryDto = new CategoryDto();
		
		categoryDto.setId(categoryEntity.getId());
		categoryDto.setName(categoryEntity.getName());
		
		return categoryDto;
	}
	
//	public static GeneralOfferDto generalOfferEntityToDto(GeneralOfferEntity generalOfferEntity){
//		GeneralOfferDto generalOfferDto = new GeneralOfferDto();
//		
//		generalOfferDto.setId(generalOfferEntity.getId());
//		generalOfferDto.setCategory(generalOfferEntity.getCategory());
//		generalOfferDto.setTitle(generalOfferEntity.getTitle());
//		
//		return generalOfferDto;
//	}
	
	public static MemberDto memberEntityToDto(MemberEntity memberEntity){
		MemberDto memberDto = new MemberDto();
		
		memberDto.setId(memberEntity.getId());
		memberDto.setAddress(memberEntity.getAddress());
		memberDto.setCellNumber(memberEntity.getCellNumber());
		memberDto.setEmail(memberEntity.getEmail());
		memberDto.setForname(memberEntity.getForname());
		memberDto.setName(memberEntity.getName());
		memberDto.setPhoneNumber(memberEntity.getPhoneNumber());
		memberDto.setTown(memberEntity.getTown());
		memberDto.setMobileId(memberEntity.getMobileId());
		
		memberDto.setWealthSheet(wealthSheetEntityToDto(memberEntity.getWealthSheet()));
		
		ArrayList<SupplyDemandDto> supplyDemandsDto = new ArrayList<SupplyDemandDto>();
		for(SupplyDemandEntity supplyDemandEntity:memberEntity.getSupplyDemand()){
			supplyDemandsDto.add(supplyDemandEntityToDto(supplyDemandEntity));
		}
		memberDto.setSupplyDemand(supplyDemandsDto);
		
		
		return memberDto;
	}
	
//	public static MessageDto messageEntityToDto(MessageEntity messagenEntity){
//		MessageDto messageDto = new MessageDto();
//		
//		messageDto.setId(messagenEntity.getId());
//		messageDto.setText(messagenEntity.getText());
//		messageDto.setState(messagenEntity.isState());
//		messageDto.setSubject(messageSubjectEntityToDto(messagenEntity.getSubject()));
//		
//		return messageDto;
//	}
	
//	public static MessageSubjectDto messageSubjectEntityToDto(MessageSubjectEntity messageSubjectEntity){
//		MessageSubjectDto messageSubjectDto = new MessageSubjectDto();
//		
//		messageSubjectDto.setId(messageSubjectEntity.getId());
//		messageSubjectDto.setType(messageSubjectEntity.getType());
//		messageSubjectDto.setTitle(messageSubjectEntity.getTitle());		
//		messageSubjectDto.setMessage(messageEntityToDto(messageSubjectEntity.getMessage()));
//		messageSubjectDto.setTransmitterMember(memberEntityToDto(messageSubjectEntity.getTransmitterMember()));
//		
//		return messageSubjectDto;
//	}
	
	public static PaymentDto paymentEntityToDto(PaymentEntity PaymentEntity){
		PaymentDto paymentDto = new PaymentDto();
		
		paymentDto.setId(PaymentEntity.getId());
		paymentDto.setAmount(PaymentEntity.getAmount());
		paymentDto.setCreditorMember(memberEntityToDto(PaymentEntity.getCreditorMember()));
		paymentDto.setDebtorMember(memberEntityToDto(PaymentEntity.getDebtorMember()));
		
		return paymentDto;
	}
	
	public static SupplyDemandDto supplyDemandEntityToDto(SupplyDemandEntity SupplyDemandEntity){
		SupplyDemandDto supplyDemandDto = new SupplyDemandDto();
		
		supplyDemandDto.setId(SupplyDemandEntity.getId());
		supplyDemandDto.setCategory(SupplyDemandEntity.getCategory());
		supplyDemandDto.setTitle(SupplyDemandEntity.getTitle());
		supplyDemandDto.setType(SupplyDemandEntity.getType());
		
		return supplyDemandDto;
	}
	
	public static WealthSheetDto wealthSheetEntityToDto(WealthSheetEntity WealthSheetEntity){
		WealthSheetDto wealthSheetDto = new WealthSheetDto();
		
		wealthSheetDto.setId(WealthSheetEntity.getId());
		wealthSheetDto.setInitialAccount(WealthSheetEntity.getInitialAccount());
		wealthSheetDto.setFinalAccount(WealthSheetEntity.getFinalAccount());
		
		ArrayList<PaymentDto> paymentsDto = new ArrayList<PaymentDto>();
		for(PaymentEntity paymentEntity:WealthSheetEntity.getPayments()){
			paymentsDto.add(paymentEntityToDto(paymentEntity));
		}
		wealthSheetDto.setPayments(paymentsDto);
		
		return wealthSheetDto;
	}

}
