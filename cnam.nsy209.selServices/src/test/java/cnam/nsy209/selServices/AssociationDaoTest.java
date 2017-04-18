package cnam.nsy209.selServices;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import server.dao.AssociationDao;
import server.dao.entity.AssociationEntity;


public class AssociationDaoTest {
	
	
	private static EntityManager em;

	private static EntityManagerFactory emf; 
	
	@Inject
	private AssociationDao associationDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		emf = Persistence.createEntityManagerFactory("test_selServices");

		em = emf.createEntityManager(); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		em.close();

		emf.close(); 
	}

	@Test
	public void test() {
		associationDao.create(new AssociationEntity());
	}

}
