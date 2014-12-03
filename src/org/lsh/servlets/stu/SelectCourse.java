package org.lsh.servlets.stu;

import org.lsh.data.Course;
import org.lsh.data.Student;
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
 * Created by lsh on 14/12/2.
 */
@WebServlet(urlPatterns = {"/stu/select_course"}, name = "select-course")
public class SelectCourse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String student_id = req.getParameter("sid");
        String course_id = req.getParameter("cid");

        if (null != student_id && null != course_id) {
            Student stu = (Student) DataCenter.query("from Student s where s.studentId = ?", student_id).get(0);
            Course c = (Course) DataCenter.query("from Course c where c.cid = ?", Integer.parseInt(course_id)).get(0);
            if (Functions.SelectCourse(stu, c)) {
                out.println("success");
            } else {
                out.println("fail");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
