package org.lsh.data.control;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static org.lsh.helper.Constants.*;

/**
 * Created by lsh on 14/11/12.
 */
public class User {
	private int type = STU;
	private String id = null;
	private Object usr_obj = null;

	public User(int type, String id) {
		this.type = type;
		this.id = id;

		SessionFactory sf = DataCenter.getSf();
		Session s = sf.openSession();
		Query q;
		switch (type) {
			case STU:
				q = s.createQuery("from Student s where s.studentId = :id");
				break;
			case TEA:
				q = s.createQuery("from Teacher t where t.teacherId = :id");
				break;
			case ADM:
				q = s.createQuery("from Admin a where a.adminId = :id");
				break;
			default:
				throw new RuntimeException("No such type of user");
		}
		q.setParameter("id", id);
		List result = q.list();
		if (result.size() == 1) {
			usr_obj = result.get(0);
		} else {
			throw new RuntimeException("No such user");
		}
		s.close();
	}

	public int getType() {
		return type;
	}

	public Object getUser() {
		return usr_obj;
	}
}
