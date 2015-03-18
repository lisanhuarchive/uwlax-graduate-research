package org.lsh.servlets.admin;

import org.lsh.data.Course;
import org.lsh.data.Teacher;
import org.lsh.data.Term;
import org.lsh.data.control.DataCenter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lsh on 14/11/20.
 */
@WebServlet(urlPatterns = {"/reg/add_course"}, name = "add_course")
public class AddCourse extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String c_no = req.getParameter("cno");
		String section = req.getParameter("section");
		String c_title = req.getParameter("ctitle");
		String teacher_name = req.getParameter("teacher");
		String time = req.getParameter("time");
		String s_capacity = req.getParameter("capacity");
		String[] weekdays = req.getParameterValues("weekdays");
		String term_name = req.getParameter("term");

		Teacher teacher = (Teacher) DataCenter.select("from Teacher t where t.teacherName = ?", teacher_name);
		Term term = (Term) DataCenter.select("from Term t where t.termName = ?", term_name);
		Course course = new Course();
		course.setcNo(c_no);
		course.setSection(section);
		course.setcTitle(c_title);
		course.setTeacher(teacher.getTeacherId());
		course.setcTime(time);
		course.setCapacity(Integer.parseInt(s_capacity));
		int wds = 0;
		for (String wd : weekdays) {
			wds += Integer.parseInt(wd);
		}
		course.setWeekdays(wds);
		course.setTerm(term.getTermId());
		course.setActivate(true);

		DataCenter.saveOrUpdate(course);
	}
}
