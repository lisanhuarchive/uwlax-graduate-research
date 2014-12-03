package org.lsh.servlets.tea;

import org.lsh.data.GradeItem;
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
@WebServlet(urlPatterns = {"/tea/edit_grade_item"}, name = "edit-grade-item")
public class EditGradeItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String value = req.getParameter("value");
        String id = req.getParameter("id");
        PrintWriter out = resp.getWriter();

        if (id == null || name == null || value == null || action == null) {
            out.println("fail");
            return;
        }

        if ("delete".equals(action)) {
            GradeItem gi = Functions.getGradeItemById(Integer.parseInt(id));
            gi.setIsValid(0);
            DataCenter.update(gi);
        }

        if ("activate".equals(action)) {
            GradeItem gi = Functions.getGradeItemById(Integer.parseInt(id));
            gi.setIsValid(1);
            DataCenter.update(gi);
        }

        if ("update".equals(action)) {
            GradeItem gi = Functions.getGradeItemById(Integer.parseInt(id));
            gi.setItemName(name);
            gi.setTotal(Integer.parseInt(value));
            DataCenter.update(gi);
        }

        out.println("success");
    }
}
