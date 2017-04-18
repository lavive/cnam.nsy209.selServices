package server.dao.bean;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import server.dao.CategoryDao;
import server.dao.entity.CategoryEntity;
import server.notification.factory.NotificationFactory;

@Singleton
@Transactional
public class CategoryDaoBean implements CategoryDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//private NotificationFactory notificationFactory;	

	@Override
	public void create(CategoryEntity entity) {
		this.entityManager.persist(entity);
		this.entityManager.flush();

	}

	@Override
	public CategoryEntity get(CategoryEntity entity) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> category = query.from(CategoryEntity.class);
		
		query.select(category).where(builder.equal(category.get("id"), entity.getId()));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public void update(CategoryEntity entity) {
		this.entityManager.merge(entity);

	}

	@Override
	public void delete(CategoryEntity entity) {
		this.entityManager.remove(this.entityManager.merge(entity));

	}

	@Override
	public List<CategoryEntity> getCategories() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> category = query.from(CategoryEntity.class);
		
		query.select(category);		
				
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public NotificationFactory getNotificationFactory() {

		return null;//this.notificationFactory;
	}

	@Override
	public CategoryEntity getCategory(int categoryId) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> category = query.from(CategoryEntity.class);
		
		query.select(category).where(builder.equal(category.get("id"), categoryId));		
				
		return this.entityManager.createQuery(query).getSingleResult();
	}

}
