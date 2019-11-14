package daos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MyEntityManagerFactory {
	private static MyEntityManagerFactory instance;
	private EntityManager entityManager;
	private MyEntityManagerFactory() {
		
		entityManager = Persistence.createEntityManagerFactory("HeThongQuanLyBenhVien").createEntityManager();
	}
	public static MyEntityManagerFactory getInstance() {
		if(instance == null)
			return instance = new MyEntityManagerFactory();
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
