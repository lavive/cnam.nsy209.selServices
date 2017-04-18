package server.dao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import server.dao.NotificationDao;
import server.dao.entity.NotificationEntity;
import server.notification.factory.NotificationFactory;

@Singleton
@Transactional
public class NotificationDaoBean implements NotificationDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(NotificationEntity entity) {
		this.entityManager.persist(entity);
		this.entityManager.flush();

	}

	@Override
	public NotificationEntity get(NotificationEntity entity) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<NotificationEntity> query = builder.createQuery(NotificationEntity.class);
		Root<NotificationEntity> notification = query.from(NotificationEntity.class);
		
		query.select(notification).where(builder.equal(notification.get("id"), entity.getId()));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public void update(NotificationEntity entity) {
		this.entityManager.merge(entity);

	}

	@Override
	public void delete(NotificationEntity entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}
	
	@Override
	public List<NotificationEntity> create(List<NotificationEntity> notifications) {
		List<NotificationEntity> notificationsResult = new ArrayList<NotificationEntity>();
		for(NotificationEntity notification:notifications){
			create(notification);
			notificationsResult.add(notification);
		}
		return notificationsResult;
	}

//	@Override
//	public void addNotification(MemberEntity memberEntity, NotificationEntity notificationEntity) {
//		List<MemberEntity> membresANotifier = notificationEntity.getMembresToNotify();
//		if(!membresANotifier.contains(memberEntity)) membresANotifier.add(memberEntity);
//		notificationEntity.setMembresToNotify(membresANotifier);
//		
//
//		List<NotificationEntity> notifications = memberEntity.getNotifications();
//		if(!notifications.contains(memberEntity)) notifications.add(notificationEntity);
//		memberEntity.setNotifications(notifications);
//		
//		this.entityManager.merge(notificationEntity);
//
//	}
//
//	@Override
//	public NotificationEntity getNotification(MemberEntity memberEntity, NotificationEntity notificationEntity) {
//		if(notificationEntity.getMembresToNotify().contains(memberEntity)){
//			CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
//			
//			CriteriaQuery<NotificationEntity> query = builder.createQuery(NotificationEntity.class);
//			Root<NotificationEntity> notification = query.from(NotificationEntity.class);
//			query.select(notification).where(builder.equal(notification.get("id"), notificationEntity.getId()));		
//					
//			return this.entityManager.createQuery(query).getSingleResult();			
//		}
//		else return null;		
//		
//	}
//
//	@Override
//	public List<NotificationEntity> getNotifications(MemberEntity memberEntity) {
//		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<NotificationEntity> query = builder.createQuery(NotificationEntity.class);
//		Root<NotificationEntity> notification = query.from(NotificationEntity.class);
//		
//		query.select(notification).where(builder.equal(notification.get("membresToNotify").get("id"), memberEntity.getId()));		
//				
//		return this.entityManager.createQuery(query).getResultList();
//	}
//
//	@Override
//	public List<MemberEntity> getMembers(NotificationEntity notificationEntity) {
//		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
//		Root<MemberEntity> member = query.from(MemberEntity.class);
//		
//		query.select(member).where(builder.equal(member.get("notifications").get("id"), notificationEntity.getId()));		
//				
//		return this.entityManager.createQuery(query).getResultList();
//	}
//
//	@Override
//	public void deleteNotification(MemberEntity memberEntity) {
//		List<NotificationEntity> notifications = getNotifications(memberEntity);
//		memberEntity.getNotifications().removeAll(notifications);		
//		
//		for(NotificationEntity notification:notifications){
//			notification.getMembresToNotify().remove(memberEntity); 
//		}
//		this.entityManager.flush();
//		
//		Iterator<NotificationEntity> iterator = getNotifications(memberEntity).iterator();
//		while ( iterator.hasNext() ) {
//			NotificationEntity notification = iterator.next();
//			if(notification.getMembresToNotify().isEmpty()){ 
//		        iterator.remove();
//		        delete(notification);
//		    }
//		}	
//		this.entityManager.flush();
//	}
//
//	@Override
//	public void deleteNotification(MemberEntity memberEntity, NotificationEntity notificationEntity) {
//		List<MemberEntity> membresANotifier = notificationEntity.getMembresToNotify();
//		if(!membresANotifier.contains(memberEntity)) membresANotifier.remove(memberEntity);
//		notificationEntity.setMembresToNotify(membresANotifier);
//		
//
//		List<NotificationEntity> notifications = memberEntity.getNotifications();
//		if(!notifications.contains(memberEntity)) notifications.remove(notificationEntity);
//		memberEntity.setNotifications(notifications);
//		
//		this.entityManager.merge(notificationEntity);
//
//	}

	@Override
	public NotificationFactory getNotificationFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationEntity getNotification(int notificationId) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<NotificationEntity> query = builder.createQuery(NotificationEntity.class);
		Root<NotificationEntity> notification = query.from(NotificationEntity.class);
		
		query.select(notification).where(builder.equal(notification.get("id"), notificationId));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	
}
