package org.lsh.data.control;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
}
