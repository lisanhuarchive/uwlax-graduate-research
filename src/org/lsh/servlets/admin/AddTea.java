package org.lsh.servlets.admin;

import org.lsh.helper.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.lsh.helper.Constants.*;

/**
 * Created by lsh on 14/11/12.
 */
@WebServlet(urlPatterns = {"/reg/addtea"}, name = "addtea")
public class AddTea extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pswd = req.getParameter("pswd");
		if (Functions.Reg(id, name, pswd, TEA)) {
			resp.sendRedirect(root + "/reg/");
		} else {
			//  todo fail stub
			resp.getWriter().println("Fail");
		}
	}
}
