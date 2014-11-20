package org.lsh.helper;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lsh.data.Admin;
import org.lsh.data.Student;
import org.lsh.data.Teacher;
import org.lsh.data.Term;
import org.lsh.data.control.DataCenter;

import static org.lsh.helper.Constants.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lsh on 14/11/12.
 */
public class Functions {

	public static boolean Login(String uname, String pswd, int ID) {
		SessionFactory sf = DataCenter.getSf();
		Session s = sf.openSession();
		pswd = Encrypt(pswd).toLowerCase();
		Query q = null;
		switch (ID) {
			case STU:
				q = s.createQuery("from Student s where s.studentId = :uname and s.studentPassword = :pswd");
				q.setParameter("uname", uname);
				q.setParameter("pswd", pswd);
				List<Student> stus = q.list();
				s.close();
				if (stus.size() != 1) {
					return false;
				} else {
					return true;
				}
			case TEA:
				q = s.createQuery("from Teacher t where t.teacherId = :uname and t.teacherPassword = :pswd");
				q.setParameter("uname", uname);
				q.setParameter("pswd", pswd);
				List<Teacher> teas = q.list();
				s.close();
				if (teas.size() != 1) {
					return false;
				} else {
					return true;
				}
			case ADM:
				q = s.createQuery("from Admin a where a.adminId = :uname and a.colPswd = :pswd");
				q.setParameter("uname", uname);
				q.setParameter("pswd", pswd);
				List<Admin> adms = q.list();
				s.close();
				if (adms.size() != 1) {
					return false;
				} else {
					return true;
				}
			default:
				return false;
		}
	}

	public static String Encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return strDes != null ? strDes.toLowerCase() : null;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		for (int i = 0; i < bts.length; i++) {
			String tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static boolean Reg(String id, String name, String pswd, int ID) {
		SessionFactory sf = DataCenter.getSf();
		Session s = sf.openSession();
		Query q;
		s.beginTransaction();

		switch (ID) {
			case STU:
				Student stu = new Student();
				q = s.createQuery("from Student s where s.studentId = :id");
				q.setParameter("id", id);
				if (q.list().size() > 0) {
					return false;
				}
				stu.setStudentId(id);
				stu.setStudentName(name);
				stu.setStudentPassword(Encrypt(pswd).toLowerCase());
				s.save(stu);
				break;
			case TEA:
				Teacher t = new Teacher();
				q = s.createQuery("from Teacher t where t.teacherId = :id");
				q.setParameter("id", id);
				if (q.list().size() > 0) {
					return false;
				}
				t.setTeacherId(id);
				t.setTeacherName(name);
				t.setTeacherPassword(Encrypt(pswd).toLowerCase());
				s.save(t);
				break;
			default:
				break;
		}

		s.getTransaction().commit();
		s.close();
		return true;
	}

	public static boolean Update(String id, String name, String pswd, int ID) {
		SessionFactory sf = DataCenter.getSf();
		Session s = sf.openSession();
		Query q;
		s.beginTransaction();

		switch (ID) {
			case STU:
				Student stu;
				q = s.createQuery("from Student s where s.studentId = :id");
				q.setParameter("id", id);
				if (q.list().size() != 1) {
					return false;
				}
				stu = (Student) q.list().get(0);
				stu.setStudentId(id);
				stu.setStudentName(name);
				if (pswd != null && !"".equals(pswd)) {
					stu.setStudentPassword(Encrypt(pswd).toLowerCase());
				}
				s.saveOrUpdate(stu);
				break;
			case TEA:
				Teacher t;
				q = s.createQuery("from Teacher t where t.teacherId = :id");
				q.setParameter("id", id);
				if (q.list().size() != 1) {
					return false;
				}
				t = (Teacher) q.list().get(0);
				t.setTeacherId(id);
				t.setTeacherName(name);
				if (pswd != null && !"".equals(pswd)) {
					t.setTeacherPassword(Encrypt(pswd).toLowerCase());
				}
				s.saveOrUpdate(t);
				break;
			default:
				break;
		}

		s.getTransaction().commit();
		s.close();
		return true;
	}

	/**
	 * Get the current term
	 *
	 * @return the last term it could be ignore it may overlaps the end date
	 */
	public static Term getCurrentTerm() {
		List<Term> terms = DataCenter.query("from Term t where t.activated = ?", true);
		Term term = terms.get(0);
		Calendar now = Calendar.getInstance();
		for (Term t : terms) {
			try {
				Calendar start = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				start.setTime(sdf.parse(t.getTermStart()));
				if (now.after(start)) {
					term = t;
				} else {
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return term;
	}
}
