package org.lsh.helper;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lsh.data.*;
import org.lsh.data.control.DataCenter;
import org.lsh.data.control.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.lsh.helper.Constants.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lsh on 14/11/12.
 */
public class Functions {

    public static Grade getGradeById(Integer gid) {
        List<Grade> result = DataCenter.query("from Grade g where g.gid = ?", gid);
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public static Grade getGradeByGradeItemAndSCID(GradeItem item, Integer scid) {
        List<Grade> result = DataCenter.query("from Grade g where g.scid = ? and g.itemId = ?", scid, item.getItemId());
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public static List<GradeItem> getValidGradeItemsByCourse(Course course) {
        return DataCenter.query("from GradeItem gi where gi.cid = ? and gi.isValid = ?", course.getCid(), 1);
    }

    public static StudentCourse getSCById(Integer id) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.scid = ?", id);
        if (scs.size() == 0) {
            return null;
        }
        return scs.get(0);
    }

    public static List<Grade> getGradesByStudentAndCourse(Student student, Course course) {
        StudentCourse sc = getSCByStudentAndCourse(student, course);
        List<Grade> result = DataCenter.query("from Grade g where g.scid = ?", sc.getScid());
        return result;
    }

    public static List<Grade> getValidGradesByStudentAndCourse(Student student, Course course) {
        StudentCourse sc = getValidSCByStudentAndCourse(student, course);

        if (sc == null) {
            return new ArrayList<>();
        }

        List<Grade> result = DataCenter.query("from Grade g where g.scid = ?", sc.getScid());
        for (Grade g : result) {
            GradeItem gi = getGradeItemById(g.getItemId());
            if (gi.getIsValid() == 0) {
                result.remove(g);
            }
        }
        return result;
    }

    public static GradeItem getGradeItemById(Integer id) {
        List<GradeItem> result = DataCenter.query("from GradeItem gi where gi.itemId = ?", id);
        if (result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

    public static List<GradeItem> getGradeItemsByCourse(Course course) {
        return DataCenter.query("from GradeItem gi where gi.cid = ?", course.getCid());
    }

    public static StudentCourse getSCByStudentAndCourse(Student student, Course course) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.studentId = ? and sc.courseId = ?", student.getStudentId(), course.getCid());

        if (scs.size() == 0) {
            return null;
        }

        return scs.get(0);
    }

    public static StudentCourse getValidSCByStudentAndCourse(Student student, Course course) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.studentId = ? and sc.courseId = ? and sc.isValid = 1", student.getStudentId(), course.getCid());

        if (scs.size() == 0) {
            return null;
        }

        return scs.get(0);
    }

    public static List<Record> getRecordsByCourse(Course course) {
        List<Record> records = DataCenter.query("from Record r where r.cid = ?", course.getCid());
        return records;
    }

    public static List<StudentRecord> getStudentRecordsByStudentAndCourse(Student student, Course course) {
        StudentCourse sc = getSCByStudentAndCourse(student, course);
        if (sc == null) {
            return null;
        }
        return DataCenter.query("from StudentRecord sr where sr.scid = ?", sc.getScid());
    }

    public static List<StudentRecord> getStudentRecordsBySCId(Integer scid) {
        return DataCenter.query("from StudentRecord sr where sr.scid = ?", scid);
    }

    public static Record getRecordByRid(Integer rid) {
        List<Record> records = DataCenter.query("from Record where rid = ?", rid);
        if (records.size() == 0) {
            return null;
        }
        return records.get(0);
    }

    public static Record getOpenRecordByCourse(Course course) {
        List<Record> records = DataCenter.query("from Record r where r.isOpen = ? and r.cid = ?", 1, course.getCid());
        if (records.size() == 0) {
            return null;
        }
        return records.get(0);
    }

    public static ArrayList<Student> getStudentsByCourse(Course course) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.courseId = ?", course.getCid());
        ArrayList<Student> result = new ArrayList<>();
        for (StudentCourse sc : scs) {
            result.add(getStudentById(sc.getStudentId()));
        }
        return result;
    }

    public static ArrayList<Student> getValidStudentsByCourse(Course course) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.courseId = ? and isValid = ?", course.getCid(), 1);
        ArrayList<Student> result = new ArrayList<>();
        for (StudentCourse sc : scs) {
            result.add(getStudentById(sc.getStudentId()));
        }
        return result;
    }

    public static List<Course> getCoursesByTeacher(Teacher teacher) {
        List<Course> courses = DataCenter.query("from Course c where c.teacher = ?", teacher.getTeacherId());
        return courses;
    }

    public static boolean checkUser(HttpServletRequest req, HttpServletResponse resp, int type) throws IOException {
        HttpSession session = req.getSession();
        User usr = (User) session.getAttribute("usr");
        if (null == usr) {
            session.setAttribute("error", "Please login first.");
            resp.sendRedirect(root);
            return false;
        } else if (usr.getType() != type) {
            session.setAttribute("error", "You can not access this page");
            resp.sendRedirect(root);
            return false;
        }
        return true;
    }

    public static int getStudentNumberByCourse(Course course) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.courseId = ? and sc.isValid = ?", course.getCid(), 1);
        return scs.size();
    }

    public static Teacher getTeacherByCourse(Course course) {
        String tid = course.getTeacher();
        return getTeacherById(tid);
    }

    public static Teacher getTeacherById(String teacherId) {
        List<Teacher> result = DataCenter.query("from Teacher t where t.teacherId = ?", teacherId);
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public static ArrayList<Course> getCoursesByStudent(Student student) {
        ArrayList<Course> courses = new ArrayList<>();
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.studentId = ?", student.getStudentId());
        for (StudentCourse sc : scs) {
            courses.add(getCourseById(sc.getCourseId()));
        }
        return courses;
    }

    public static ArrayList<Course> getValidCoursesByStudent(Student student) {
        ArrayList<Course> courses = new ArrayList<>();
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.studentId = ? and sc.isValid = ?", student.getStudentId(), 1);
        for (StudentCourse sc : scs) {
            courses.add(getCourseById(sc.getCourseId()));
        }
        return courses;
    }

    public static Course getCourseById(Integer course_id) {
        List<Course> result = DataCenter.query("from Course c where c.cid = ?", course_id);
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public static void ForceSelectCourse(Student student, Course course) {
        StudentCourse sc = new StudentCourse();
        sc.setStudentId(student.getStudentId());
        sc.setCourseId(course.getCid());
        DataCenter.save(sc);
    }

    public static Student getStudentById(String id) {
        List<Student> result = DataCenter.query("from Student s where s.studentId = ?", id);
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    public static boolean SelectCourse(Student student, Course course) {
        List<StudentCourse> scs = DataCenter.query("from StudentCourse sc where sc.courseId = ?", course.getCid());
        if (scs.size() >= course.getCapacity()) {
            return false;
        }

        if (!canSelect(course)) {
            return false;
        }

        StudentCourse sc = getSCByStudentAndCourse(student, course);
        if (sc == null) {
            sc = new StudentCourse();
            sc.setStudentId(student.getStudentId());
            sc.setCourseId(course.getCid());
            sc.setIsValid(1);
            DataCenter.save(sc);
        } else {
            sc.setIsValid(1);
            DataCenter.update(sc);
        }
        return true;
    }

    public static boolean canSelect(Course course) {
        Term t = getTermByCourse(course);
        if (t == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        try {
            start.setTime(sdf.parse(t.getTermStart()));
            end.setTime(sdf.parse(t.getTermEnd()));
            if (start.before(now) && end.after(now)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Term getTermByCourse(Course course) {
        List<Term> result = DataCenter.query("from Term t where t.termId = ?", course.getTerm());
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

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
        Calendar now = Calendar.getInstance();
        for (Term t : terms) {
            try {
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                start.setTime(sdf.parse(t.getTermStart()));
                end.setTime(sdf.parse(t.getTermEnd()));
                if (now.after(start) && now.before(end)) {
                    return t;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
