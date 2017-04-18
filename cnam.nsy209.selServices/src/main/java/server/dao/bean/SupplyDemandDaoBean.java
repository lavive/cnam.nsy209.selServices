package server.dao.bean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import server.dao.SupplyDemandDao;
import server.dao.constantes.EnumSupplyDemand;
import server.dao.entity.SupplyDemandEntity;
import server.notification.factory.NotificationFactory;
import server.notification.factory.NotificationSupplyDemandFactory;

@Singleton
@Transactional
public class SupplyDemandDaoBean implements SupplyDemandDao {

	@Inject
	private NotificationSupplyDemandFactory notificationFactory;

	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	@Interceptors({ server.notification.interceptor.InterceptorToNotify.class}) 
	public void create(SupplyDemandEntity entity) {
		this.notificationFactory.setMemberOrigin(entity.getMember());
		
		if(entity.getType().equals(EnumSupplyDemand.SUPPLY))
			this.notificationFactory.setNewSupply(entity);
		
		if(entity.getType().equals(EnumSupplyDemand.DEMAND))
			this.notificationFactory.setNewDemand(entity);
		
		this.entityManager.persist(entity);
		this.entityManager.flush();

	}

	@Override
	public SupplyDemandEntity get(SupplyDemandEntity entity) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<SupplyDemandEntity> query = builder.createQuery(SupplyDemandEntity.class);
		Root<SupplyDemandEntity> supplyDemand = query.from(SupplyDemandEntity.class);
		
		query.select(supplyDemand).where(builder.equal(supplyDemand.get("id"), entity.getId()));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	@Interceptors({ server.notification.interceptor.InterceptorToNotify.class}) 
	public void update(SupplyDemandEntity entity) {
		this.notificationFactory.setMemberOrigin(entity.getMember());
		
		if(entity.getType().equals(EnumSupplyDemand.SUPPLY))
			this.notificationFactory.setNewSupply(entity);
		
		if(entity.getType().equals(EnumSupplyDemand.DEMAND))
			this.notificationFactory.setNewDemand(entity);
		
		this.entityManager.merge(entity);

	}

	@Override
	public void delete(SupplyDemandEntity entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public List<SupplyDemandEntity> getAllSupplyDemand() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<SupplyDemandEntity> query = builder.createQuery(SupplyDemandEntity.class);
		Root<SupplyDemandEntity> supplyDemand = query.from(SupplyDemandEntity.class);
		
		query.select(supplyDemand);
		query.orderBy(builder.asc(supplyDemand.get("type")),builder.asc(supplyDemand.get("category")),builder.asc(supplyDemand.get("title")));
				
		return this.entityManager.createQuery(query).getResultList();
	}

//	@Override
//	public List<SupplyDemandEntity> getSupplyDemand(MemberEntity memberEntity) {
//		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<SupplyDemandEntity> query = builder.createQuery(SupplyDemandEntity.class);
//		Root<SupplyDemandEntity> supplyDemand = query.from(SupplyDemandEntity.class);
//		Join<SupplyDemandEntity,MemberEntity> supplyDemandMember = supplyDemand.join("member",JoinType.LEFT);
//				
//		query.select(supplyDemand).where(builder.equal(supplyDemandMember.get("id"), memberEntity.getId()));
//		
//		return this.entityManager.createQuery(query).getResultList();
//	}

	@Override
	public SupplyDemandEntity getSupplyDemandById(int id) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<SupplyDemandEntity> query = builder.createQuery(SupplyDemandEntity.class);
		Root<SupplyDemandEntity> supplyDemand = query.from(SupplyDemandEntity.class);
		
		query.select(supplyDemand).where(builder.equal(supplyDemand.get("id"), id));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public NotificationFactory getNotificationFactory() {

		return this.notificationFactory;
	}

//	@Override
//	public List<SupplyDemandEntity> getSupplyDemand(int memberEntityId) {
//		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<SupplyDemandEntity> query = builder.createQuery(SupplyDemandEntity.class);
//		Root<SupplyDemandEntity> supplyDemand = query.from(SupplyDemandEntity.class);
//		Join<SupplyDemandEntity,MemberEntity> supplyDemandMember = supplyDemand.join("member",JoinType.LEFT);
//				
//		query.select(supplyDemand).where(builder.equal(supplyDemandMember.get("id"), memberEntityId));
//		
//		return this.entityManager.createQuery(query).getResultList();
//	}

}
