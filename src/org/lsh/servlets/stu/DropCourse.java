package org.lsh.servlets.stu;

import org.lsh.data.Course;
import org.lsh.data.Student;
import org.lsh.data.StudentCourse;
import org.lsh.data.control.DataCenter;
import org.lsh.helper.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lsh on 14/12/3.
 */
@WebServlet(urlPatterns = {"/stu/drop_course"}, name = "drop-course")
public class DropCourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        String cid = req.getParameter("cid");
        PrintWriter out = resp.getWriter();
        if (sid == null || cid == null) {
            out.println("fail");
            return;
        }

        Student student = Functions.getStudentById(sid);
        Course course = Functions.getCourseById(Integer.parseInt(cid));
        StudentCourse sc = Functions.getSCByStudentAndCourse(student, course);
        if (sc != null) {
            sc.setIsValid(0);
            DataCenter.update(sc);
        }
        out.println("success");
    }
}
