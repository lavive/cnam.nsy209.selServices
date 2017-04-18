package server.dao.bean;

import java.util.ArrayList;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import server.dao.MemberDao;
import server.dao.entity.MemberEntity;
import server.dao.entity.SupplyDemandEntity;
import server.dao.entity.WealthSheetEntity;
import server.notification.factory.NotificationFactory;
import server.notification.factory.NotificationMemberFactory;

@Singleton
@Transactional
public class MemberDaoBean implements MemberDao {
	
	@Inject
	private NotificationMemberFactory notificationFactory;

	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	@Interceptors({ server.notification.interceptor.InterceptorToNotify.class}) 
	public void create(MemberEntity entity) {
		this.notificationFactory.setNewMember(entity);
		entity.setDateModification(new Date(System.currentTimeMillis()));
		this.entityManager.persist(entity);
		this.entityManager.flush();

	}

	@Override
	public MemberEntity get(MemberEntity entity) {
		entity.setDateModification(new Date(System.currentTimeMillis()));
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		
		query.select(member).where(builder.equal(member.get("id"), entity.getId()));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public void update(MemberEntity entity) {
		entity.setDateModification(new Date(System.currentTimeMillis()));
		this.entityManager.merge(entity);

	}

	@Override
	@Interceptors({ server.notification.interceptor.InterceptorToNotify.class}) 
	public void delete(MemberEntity entity) {
		this.notificationFactory.setMemberLeaving(entity);
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public List<MemberEntity> getListLastMember(int sizeList) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		
		query.select(member);
		
		query.orderBy(builder.desc(member.get("dateModification")));
		
		List<MemberEntity> members = this.entityManager.createQuery(query).getResultList();
		
		List<MemberEntity> membersResult = new ArrayList<MemberEntity>();
		
		int end = Math.min(sizeList, members.size());		
		for(int i = 0; i < end; i++){
			membersResult.add(members.get(i));
		}
		
		return membersResult;
	}

	@Override
	public List<MemberEntity> getAllMembers() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		
		query.select(member);
				
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public MemberEntity getMemberById(int id) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		
		query.select(member).where(builder.equal(member.get("id"), id));
		MemberEntity entity = this.entityManager.createQuery(query).getSingleResult();
		entity.setDateModification(new Date(System.currentTimeMillis()));
				
		return entity;
	}

	@Override
	public List<MemberEntity> getMembersByName(String name) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		
		query.select(member).where(builder.equal(member.get("name"), name));
		
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<MemberEntity> getMembersByNameAndForname(String name, String forname) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		
		Predicate clauseWhere = builder.conjunction();
		clauseWhere.getExpressions().add(builder.equal(member.get("name"), name));
		clauseWhere.getExpressions().add(builder.equal(member.get("forname"), forname));
		
		query.select(member).where(clauseWhere);
		
		return this.entityManager.createQuery(query).getResultList();
	}


	@Override
	public List<MemberEntity> getMembersByTown(String town) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		
		query.select(member).where(builder.equal(member.get("town"), town));
		
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<MemberEntity> getMembersBySupplyDemand(String type, String category) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<MemberEntity> query = builder.createQuery(MemberEntity.class);
		Root<MemberEntity> member = query.from(MemberEntity.class);
		Join<MemberEntity,SupplyDemandEntity> memberSupplyDemand = member.join("supplyDemand",JoinType.LEFT);
		
		Predicate clauseWhere = builder.conjunction();
		clauseWhere.getExpressions().add(builder.equal(memberSupplyDemand.get("type"), type));
		clauseWhere.getExpressions().add(builder.equal(memberSupplyDemand.get("category"), category));
		
		query.select(member).where(clauseWhere);
		
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public NotificationFactory getNotificationFactory() {

		return this.notificationFactory;
	}

	@Override
	public List<SupplyDemandEntity> getSupplyDemands(int memberId) {
		
		return getMemberById(memberId).getSupplyDemand();
	}

	public WealthSheetEntity getWealthSheetEntity(int memberId) {
		
		return getMemberById(memberId).getWealthSheet();
	}


}
