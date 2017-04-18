package server.notification.factory.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import server.dao.AssociationDao;
import server.dao.MemberDao;
import server.dao.entity.AssociationEntity;
import server.dao.entity.MemberEntity;
import server.dao.entity.NotificationEntity;
import server.dao.entity.NotificationTopicEntity;
import server.notification.constantes.EnumTopicNotification;
import server.notification.factory.NotificationMemberFactory;

@Singleton
@Transactional
public class NotificationMemberFactoryBean implements NotificationMemberFactory {
	
	@Inject
	private AssociationDao associationDao;
	
	@Inject
	private MemberDao memberDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private MemberEntity newMember;
	
	@Override
	public void setNewMember(MemberEntity member){
		this.newMember = member;
	}	
	
	private MemberEntity memberLeaving;
	
	@Override
	public void setMemberLeaving(MemberEntity member){
		this.memberLeaving = member;
	}
	
	@Override
	public List<NotificationEntity> create() {
		List<NotificationEntity> notifications = new ArrayList<NotificationEntity>();
		
		if(this.newMember != null){
			NotificationEntity notificationNewMember = createNewMember();
			NotificationEntity notificationWelcome = createWelcome();		
			notifications.add(notificationNewMember);
			notifications.add(notificationWelcome);
		}
		if(this.memberLeaving != null){
			NotificationEntity notificationMemberLeaving = createMemberLeaving();
			notifications.add(notificationMemberLeaving);
		}
		this.newMember = null;
		this.memberLeaving = null;
		
		return notifications;
	}
	
	private NotificationEntity createNewMember(){
		NotificationEntity notification = new NotificationEntity();
		NotificationTopicEntity notificationTopicEntity = new NotificationTopicEntity();
		
		AssociationEntity association = associationDao.getAssociation();
		
		String event = EnumTopicNotification.NEW_MEMBER.getWording();
		
		String title = association.getName()+": "+event+" ("+
						this.newMember.getForname()+" "+this.newMember.getName()+")";
		
		String text = "Nous souhaitons la bienvenue parmis nous à "+
				this.newMember.getForname()+" "+this.newMember.getName();
		
		List<MemberEntity> memberToNotify = memberDao.getAllMembers();
		
		String category = null;
		
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
	
	private NotificationEntity createMemberLeaving(){
		NotificationEntity notification = new NotificationEntity();
		NotificationTopicEntity notificationTopicEntity = new NotificationTopicEntity();
		
		AssociationEntity association = associationDao.getAssociation();
		
		String event = EnumTopicNotification.MEMBER_LEAVING.getWording();
		
		String title = association.getName()+": "+event+" ("+
						this.memberLeaving.getForname()+" "+this.memberLeaving.getName()+")";
		
		String text = this.memberLeaving.getForname()+" "+this.memberLeaving.getName()+
						" a quitté notre association ";
		
		List<MemberEntity> memberToNotify = memberDao.getAllMembers();
		
		String category = null;
		
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
	
	private NotificationEntity createWelcome(){
		NotificationEntity notification = new NotificationEntity();
		NotificationTopicEntity notificationTopicEntity = new NotificationTopicEntity();
		
		AssociationEntity association = associationDao.getAssociation();
		
		String event = EnumTopicNotification.WELCOME_MESSAGE.getWording();
		
		String category = null;
						
		String title = event+" à "+association.getName();
		
		String text = "Nous vous souhaitons la bienvenue parmi nous";
		
		List<MemberEntity> memberToNotify = new ArrayList<MemberEntity>();
		memberToNotify.add(this.newMember);
		
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
