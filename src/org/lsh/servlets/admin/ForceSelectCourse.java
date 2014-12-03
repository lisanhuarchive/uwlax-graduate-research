package org.lsh.servlets.admin;

import org.lsh.data.Course;
import org.lsh.data.Student;
import org.lsh.helper.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lsh on 14/12/2.
 */
@WebServlet(urlPatterns = {"/reg/force_select_course"}, name = "force-select-course")
public class ForceSelectCourse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String sid = req.getParameter("sid");
        String cid = req.getParameter("cid");
        Student student = Functions.getStudentById(sid);
        Course course = Functions.getCourseById(Integer.parseInt(cid));
        Functions.ForceSelectCourse(student, course);
        out.println("success");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
