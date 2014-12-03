package org.lsh.servlets.tea;

import org.lsh.data.Grade;
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
@WebServlet(urlPatterns = {"/tea/edit_student_grade"}, name = "edit-student-grade")
public class EditStudentGrade extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer url = req.getRequestURL();
        String action = req.getParameter("action");
        String itemid = req.getParameter("itemid");
        String scid = req.getParameter("scid");
        String grade = req.getParameter("grade");
        PrintWriter out = resp.getWriter();
        if (action == null || itemid == null || scid == null || grade == null) {
            out.println("fail");
            return;
        }

        if ("add".equals(action)) {
            Grade g = new Grade();
            g.setGrade(Integer.parseInt(grade));
            g.setItemId(Integer.parseInt(itemid));
            g.setScid(Integer.parseInt(scid));
            DataCenter.save(g);
        }

        if ("update".equals(action)) {
            String gid = req.getParameter("gid");
            if (gid == null) {
                out.println("fail");
            } else {
                Grade g = Functions.getGradeById(Integer.parseInt(gid));
                g.setGrade(Integer.parseInt(grade));
                DataCenter.update(g);
            }
        }
        out.println("success");
    }
}
