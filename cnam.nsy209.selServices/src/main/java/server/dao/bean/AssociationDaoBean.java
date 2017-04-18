package server.dao.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import server.dao.AssociationDao;
import server.dao.constantes.EnumAssociationAttribute;
import server.dao.entity.AssociationEntity;
import server.notification.factory.NotificationAssociationFactory;
import server.notification.factory.NotificationFactory;

@Singleton
@Transactional
public class AssociationDaoBean implements AssociationDao {

	@Inject
	private NotificationAssociationFactory notificationFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void create(AssociationEntity entity) {
		this.entityManager.persist(entity);
		this.entityManager.flush();
	}	

	@Override
	public AssociationEntity get(AssociationEntity entity) {
		if(entity.getId() != getAssociation().getId()){
			this.entityManager.remove(this.entityManager.merge(entity));
			return getAssociation();
		} else {
			update(entity);
			return entity;
		}
		
	}

	@Override
	@Interceptors({ server.notification.interceptor.InterceptorToNotify.class}) 
	public void update(AssociationEntity entity) {
		this.notificationFactory.setMapAttributeValue(attributesModified(entity));
		if(entity.getId() != getAssociation().getId()){
			AssociationEntity associationEntity = getAssociation();			
			updateAssociationEntity(associationEntity,entity);
			this.entityManager.remove(this.entityManager.merge(entity));
			this.entityManager.merge(associationEntity);
		} else {
			this.entityManager.merge(entity);
		}
		
	}

	@Override
	public void delete(AssociationEntity entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public AssociationEntity getAssociation() {
		AssociationEntity resultEntity = null;
		
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<AssociationEntity> query = builder.createQuery(AssociationEntity.class);
		Root<AssociationEntity> association = query.from(AssociationEntity.class);
		
		query.select(association);
		
		List<AssociationEntity> associations = this.entityManager.createQuery(query).getResultList();
		
		if(!associations.isEmpty()){
			int rank = 0;
			for(AssociationEntity associationEntity:associations){
				if(rank>0) this.entityManager.remove(this.entityManager.merge(associationEntity));
				else resultEntity = associationEntity;
				rank++;
			}
		}
		
		return resultEntity;
	}
	

	@Override
	public NotificationFactory getNotificationFactory() {
		
		return this.notificationFactory;
	}
	
	private void updateAssociationEntity(AssociationEntity target, AssociationEntity toCopy){

		target.setName(toCopy.getName());
		target.setTown(toCopy.getTown());
		target.setAddress(toCopy.getAddress());
		target.setEmail(toCopy.getEmail());
		target.setCellNumber(toCopy.getCellNumber());
		target.setPhoneNumber(toCopy.getPhoneNumber());
		target.setWebsite(toCopy.getWebsite());
	}
	
	
	private Map<EnumAssociationAttribute,String> attributesModified(AssociationEntity association){
		Map<EnumAssociationAttribute,String> result = new HashMap<EnumAssociationAttribute,String>();
		AssociationEntity origin = getAssociation();
		if(origin.getName() != null){
			if(!origin.getName().equals(association.getName()))
				result.put(EnumAssociationAttribute.NAME, association.getName());
		}else if(association.getName() != null){
			result.put(EnumAssociationAttribute.NAME, association.getName());
		}
		
		if(origin.getTown() != null){
			if(!origin.getTown().equals(association.getTown()))
				result.put(EnumAssociationAttribute.TOWN, association.getTown());
		}else if(association.getTown() != null){
			result.put(EnumAssociationAttribute.TOWN, association.getTown());
		}
		
		if(origin.getAddress() != null){
			if(!origin.getAddress().equals(association.getAddress()))
				result.put(EnumAssociationAttribute.ADDRESS, association.getAddress());
		}else if(association.getAddress() != null){
			result.put(EnumAssociationAttribute.ADDRESS, association.getAddress());
		}		
		
		if(origin.getCellNumber() != null){
			if(!origin.getCellNumber().equals(association.getCellNumber()))
				result.put(EnumAssociationAttribute.CELL_NUMBER, association.getCellNumber());
		}else if(association.getCellNumber() != null){
			result.put(EnumAssociationAttribute.CELL_NUMBER, association.getTown());
		}
		
		if(origin.getPhoneNumber() != null){
			if(!origin.getPhoneNumber().equals(association.getPhoneNumber()))
				result.put(EnumAssociationAttribute.PHONE_NUMBER, association.getPhoneNumber());
		}else if(association.getPhoneNumber() != null){
			result.put(EnumAssociationAttribute.PHONE_NUMBER, association.getTown());
		}
		
		if(origin.getEmail() != null){
			if(!origin.getEmail().equals(association.getEmail()))
				result.put(EnumAssociationAttribute.EMAIL, association.getEmail());
		}else if(association.getEmail() != null){
			result.put(EnumAssociationAttribute.EMAIL, association.getTown());
		}
		
		if(origin.getWebsite() != null){
			if(!origin.getWebsite().equals(association.getWebsite()))
				result.put(EnumAssociationAttribute.WEBSITE, association.getWebsite());
		}else if(association.getWebsite() != null){
			result.put(EnumAssociationAttribute.WEBSITE, association.getWebsite());
		}
		
		return result;
	}



	

}
