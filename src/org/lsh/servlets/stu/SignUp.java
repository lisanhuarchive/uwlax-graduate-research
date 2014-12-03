package org.lsh.servlets.stu;

import org.lsh.data.*;
import org.lsh.data.control.DataCenter;
import org.lsh.helper.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lsh on 14/12/3.
 */
@WebServlet(urlPatterns = {"/stu/sign_up"}, name = "sign-up")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String rid = req.getParameter("rid");
        String sid = req.getParameter("sid");
        if (rid == null || sid == null) {
            out.println("Parameter lost");
            return;
        }

        Record record = Functions.getRecordByRid(Integer.parseInt(rid));
        if (record == null) {
            out.println("No such record");
            return;
        }

        Student student = Functions.getStudentById(sid);
        if (student == null) {
            out.println("No such student");
            return;
        }

        if (record.getIsOpen() == 0) {
            out.println("Recording closed");
            return;
        }

        if (record.getIsOpen() == 1) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String time = sdf.format(Calendar.getInstance().getTime());
            Course course = Functions.getCourseById(record.getCid());
            StudentCourse sc = Functions.getSCByStudentAndCourse(student, course);
            StudentRecord sr = new StudentRecord();
            sr.setRecordTime(time);
            sr.setScid(sc.getScid());
            sr.setRid(record.getRid());
            DataCenter.save(sr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
