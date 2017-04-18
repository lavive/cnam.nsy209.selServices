package server.dao.bean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import server.dao.MemberDao;
import server.dao.NotificationDao;
import server.dao.NotificationsMemberDao;
import server.dao.entity.MemberEntity;
import server.dao.entity.NotificationEntity;
import server.dao.entity.NotificationsMemberEntity;
import server.notification.factory.NotificationFactory;

@Singleton
@Transactional
public class NotificationsMemberDaoBean implements NotificationsMemberDao {
	
	@Inject
	private MemberDao memberDao;
	
	@Inject
	private NotificationDao notificationDao;
	
	@PersistenceContext
	private EntityManager entityManager;	

	@Override
	public void create(NotificationsMemberEntity entity) {
		this.entityManager.persist(entity);
		this.entityManager.flush();

	}

	@Override
	public NotificationsMemberEntity get(NotificationsMemberEntity entity) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<NotificationsMemberEntity> query = builder.createQuery(NotificationsMemberEntity.class);
		Root<NotificationsMemberEntity> notificationsMember = query.from(NotificationsMemberEntity.class);
		
		query.select(notificationsMember).where(builder.equal(notificationsMember.get("id"), entity.getId()));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public void update(NotificationsMemberEntity entity) {

		this.entityManager.merge(entity);

	}

	@Override
	public void delete(NotificationsMemberEntity entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public NotificationFactory getNotificationFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NotificationsMemberEntity> getAllNotificationsMember() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<NotificationsMemberEntity> query = builder.createQuery(NotificationsMemberEntity.class);
		Root<NotificationsMemberEntity> notificationsMember = query.from(NotificationsMemberEntity.class);
		
		query.select(notificationsMember);
				
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public void initialise() {
		List<NotificationsMemberEntity> notificationsMembers = getAllNotificationsMember();
		List<MemberEntity> members = memberDao.getAllMembers();
		for(MemberEntity member:members){
			NotificationsMemberEntity notificationsMember = isInside(member,notificationsMembers);
			if(notificationsMember != null){
				notificationsMember.setNotifications(null);
				update(notificationsMember);
			}
			else{
				notificationsMember = new NotificationsMemberEntity();
				notificationsMember.setMember(member);
				create(notificationsMember);
			}
		}
		
	}

	@Override
	public List<NotificationEntity> getNotifications(int memberId) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<NotificationsMemberEntity> query = builder.createQuery(NotificationsMemberEntity.class);
		Root<NotificationsMemberEntity> notificationsMember = query.from(NotificationsMemberEntity.class);
		Join<NotificationsMemberEntity,MemberEntity> notificationsMemberMember = notificationsMember.join("member",JoinType.LEFT);
		
		query.select(notificationsMember).where(builder.equal(notificationsMemberMember.get("id"), memberId));
		
		return this.entityManager.createQuery(query).getSingleResult().getNotifications();
	}

	@Override
	public void deleteNotification(int memberId, int notificationId) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<NotificationsMemberEntity> query = builder.createQuery(NotificationsMemberEntity.class);
		Root<NotificationsMemberEntity> notificationsMember = query.from(NotificationsMemberEntity.class);
		Join<NotificationsMemberEntity,MemberEntity> notificationsMemberMember = notificationsMember.join("member",JoinType.LEFT);
		
		query.select(notificationsMember).where(builder.equal(notificationsMemberMember.get("id"), memberId));
		
		NotificationsMemberEntity notificationsMemberEntity = this.entityManager.createQuery(query).getSingleResult();
		
		notificationsMemberEntity.getNotifications().remove(notificationDao.getNotification(notificationId));
		
		this.entityManager.merge(notificationsMemberEntity);
		
		
	}
	
	
	private NotificationsMemberEntity isInside(MemberEntity member, List<NotificationsMemberEntity> notificationsMembers){
		
		if(notificationsMembers != null){
			for(NotificationsMemberEntity notificationsMember:notificationsMembers){
				if(member.getId().equals(notificationsMember.getMember().getId()))
						return notificationsMember;
			}
		}
		return null;
	}

}
