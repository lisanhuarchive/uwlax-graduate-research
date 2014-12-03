package org.lsh.servlets.tea;

import org.lsh.data.GradeItem;
import org.lsh.data.control.DataCenter;

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
@WebServlet(urlPatterns = {"/tea/add_grade_item"}, name = "add-grade-item")
public class AddGradeItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String value = req.getParameter("value");
        String cid = req.getParameter("cid");
        if (name == null || value == null || cid == null) {
            out.println("fail");
            return;
        }

        GradeItem gi = new GradeItem();
        gi.setCid(Integer.parseInt(cid));
        gi.setItemName(name);
        gi.setTotal(Integer.parseInt(value));
        gi.setIsValid(1);
        DataCenter.save(gi);
        out.println("success");
    }
}
