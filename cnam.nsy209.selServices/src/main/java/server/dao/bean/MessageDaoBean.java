package server.dao.bean;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import server.dao.MessageDao;
import server.dao.entity.MessageEntity;
import server.dao.entity.PersonEntity;
import server.notification.factory.NotificationFactory;
import server.notification.factory.NotificationMessageFactory;

@Singleton
@Transactional
public class MessageDaoBean implements MessageDao {

	@Inject
	private NotificationMessageFactory notificationFactory;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Interceptors({ server.notification.interceptor.InterceptorToNotify.class}) 
	public void create(MessageEntity entity) {
		this.notificationFactory.setMessage(entity);
		entity.setDate(new Date(System.currentTimeMillis()));
		this.entityManager.persist(entity);
		this.entityManager.flush();

	}

	@Override
	public MessageEntity get(MessageEntity entity) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);
		Root<MessageEntity> message = query.from(MessageEntity.class);
		
		query.select(message).where(builder.equal(message.get("id"), entity.getId()));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	@Interceptors({ server.notification.interceptor.InterceptorToNotify.class}) 
	public void update(MessageEntity entity) {
		this.notificationFactory.setMessage(entity);
		entity.setDate(new Date(System.currentTimeMillis()));
		this.entityManager.merge(entity);

	}

	@Override
	public void delete(MessageEntity entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public List<MessageEntity> getMessageByState(boolean state) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);
		Root<MessageEntity> message = query.from(MessageEntity.class);
		
		query.select(message).where(builder.equal(message.get("state"), state));		
				
		return this.entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public List<MessageEntity> getMessages() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);
		Root<MessageEntity> message = query.from(MessageEntity.class);
		
		query.select(message);
		query.orderBy(builder.desc(message.get("date")));
				
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<MessageEntity> getMessages(PersonEntity personEntity) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);
		Root<MessageEntity> message = query.from(MessageEntity.class);
		Join<MessageEntity,PersonEntity> messagePerson = message.join("person",JoinType.LEFT);
		
		query.select(message).where(builder.equal(messagePerson.get("id"), personEntity.getId()));
		
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public MessageEntity getMessageById(int id) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);
		Root<MessageEntity> message = query.from(MessageEntity.class);
		
		query.select(message).where(builder.equal(message.get("id"), id));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public void deleteMessage() {
		for(MessageEntity messageEntity:getMessages()){
			delete(messageEntity);
		}
		
	}

	@Override
	public void deleteMessages(List<MessageEntity> messageEntities) {
		for(MessageEntity messageEntity:messageEntities){
			delete(messageEntity);
		}
		
	}

	@Override
	public NotificationFactory getNotificationFactory() {

		return this.notificationFactory;
	}

}
