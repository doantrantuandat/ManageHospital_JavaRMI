package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class GeneralCRUD<T> {
	protected EntityManager manager;

	public GeneralCRUD() {
		manager = MyEntityManagerFactory.getInstance().getEntityManager(); 
	}
	public boolean Them (T t) {
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}

		return false;
	}
	public boolean Sua (T t) {
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.merge(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		
		return false;
	}
	public boolean Xoa (T t) {
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.remove(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> t) {
		return manager.createQuery("From " + t.getSimpleName()).getResultList();
	}
	
}
