package org.lsh.data.control;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by lsh on 14/11/12.
 */
public class DataCenter {
	private static SessionFactory sf = null;

	public static SessionFactory getSf() {
		if (sf == null) {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sf = new Configuration().configure().buildSessionFactory(sr);
		}
		return sf;
	}

	public static List query(String hql, Object... params) {
		List result;
		Session s = getSf().openSession();
		Query q = s.createQuery(hql);
		for (int i = 0; i < params.length; ++i) {
			q.setParameter(i, params[i]);
		}
		result = q.list();
		s.close();
		return result;
	}

	public static void execute(String hql, Object... params) {
		Session s = getSf().openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery(hql);
		for (int i = 0; i < params.length; ++i) {
			q.setParameter(i, params[i]);
		}
		q.executeUpdate();
		tx.commit();
		s.close();
	}

	public static void saveOrUpdate(Object obj) {
		Session s = getSf().openSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(obj);
		tx.commit();
		s.close();
	}

	public static void update(Object obj) {
		Session s = getSf().openSession();
		Transaction tx = s.beginTransaction();
		s.update(obj);
		tx.commit();
		s.close();
	}

	public static void save(Object obj) {
		Session s = getSf().openSession();
		Transaction tx = s.beginTransaction();
		s.update(obj);
		tx.commit();
		s.close();
	}

	public static Object select(String hql, Object... params) {
		List result;
		Session s = getSf().openSession();
		Query q = s.createQuery(hql);
		for (int i = 0; i < params.length; ++i) {
			q.setParameter(i, params[i]);
		}
		result = q.list();
		s.close();
		return result.get(0);
	}
}
