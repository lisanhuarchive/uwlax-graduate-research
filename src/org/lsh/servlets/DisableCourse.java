package org.lsh.servlets;

import org.lsh.data.Course;
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
@WebServlet(urlPatterns = {"/reg/disable_course"}, name = "disable_course")
public class DisableCourse extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s_id = req.getParameter("id");

		Course course = (Course) DataCenter.select("from Course c where c.cid = ?", Integer.parseInt(s_id));
		course.setActivate(false);

		DataCenter.update(course);
	}
}
