package org.lsh.servlets;

import org.lsh.helper.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.lsh.helper.Constants.STU;
import static org.lsh.helper.Constants.root;

/**
 * Created by lsh on 14/11/12.
 */
@WebServlet(urlPatterns = "/reg/upstu", name = "upstu")
public class UpdateStu extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pswd = req.getParameter("pswd");
		if (Functions.Update(id, name, pswd, STU)) {
			resp.sendRedirect(root + "/reg/");
		} else {
			//  todo fail stub
			resp.getWriter().println("Fail");
		}
	}
}
