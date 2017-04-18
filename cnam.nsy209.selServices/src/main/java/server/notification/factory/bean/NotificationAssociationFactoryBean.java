package server.notification.factory.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import server.dao.AssociationDao;
import server.dao.MemberDao;
import server.dao.constantes.EnumAssociationAttribute;
import server.dao.entity.AssociationEntity;
import server.dao.entity.MemberEntity;
import server.dao.entity.NotificationEntity;
import server.dao.entity.NotificationTopicEntity;
import server.notification.constantes.EnumTopicNotification;
import server.notification.factory.NotificationAssociationFactory;

@Singleton
@Transactional
public class NotificationAssociationFactoryBean implements NotificationAssociationFactory {

	@Inject
	private AssociationDao associationDao;
	
	@Inject
	private MemberDao memberDao;
		
	@PersistenceContext
	private EntityManager entityManager;
		
	private Map<EnumAssociationAttribute,String> mapAttributeValue;
	
	@Override
	public void setMapAttributeValue(Map<EnumAssociationAttribute,String> mapAttributeValue){
		this.mapAttributeValue = mapAttributeValue;
	}

	@Override
	public List<NotificationEntity> create() {
		List<NotificationEntity> notifications = new ArrayList<NotificationEntity>();
		
		NotificationEntity notification = createAssociationChange();
		
		notifications.add(notification);
		
		return notifications;
	}
	
	private NotificationEntity createAssociationChange(){
		NotificationEntity notification = new NotificationEntity();
		NotificationTopicEntity notificationTopicEntity = new NotificationTopicEntity();
		
		AssociationEntity association = associationDao.getAssociation();
		
		String event = EnumTopicNotification.ASSOCIATION_CHANGE.getWording();
		
		String category = null;
						
		String title = association.getName()+": "+event;
		
		String attributeValues ="";
		for(EnumAssociationAttribute attribute:this.mapAttributeValue.keySet()){
			attributeValues += attribute.getWording()+": "+this.mapAttributeValue.get(attribute)+"; ";
		}
		String text = "Les modification concernent: "+attributeValues;
		
		List<MemberEntity> memberToNotify = memberDao.getAllMembers();
		
		notificationTopicEntity.setTopic(event);
		notificationTopicEntity.setCategory(category);
		notificationTopicEntity.setPersonOriginEvent(association);
		
		this.entityManager.persist(notificationTopicEntity);
		this.entityManager.flush();
		
		notification.setMembresToNotify(memberToNotify);
		notification.setText(text);
		notification.setTitle(title);
		notification.setTopic(notificationTopicEntity);
		
		this.entityManager.persist(notification);
		this.entityManager.flush();
		
		return notification;
	}


}
