package org.lsh.servlets.admin;

import org.lsh.data.control.User;
import org.lsh.helper.Functions;

import static org.lsh.helper.Constants.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by lsh on 14/11/12.
 */
@WebServlet(urlPatterns = {"/login"}, name = "Login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dest = root;
		HttpSession session = req.getSession();

		if (!checkParams(req.getParameterNames(), 3)) {
			session.setAttribute("error", "Parameters incorrect");
			resp.sendRedirect(dest);
			return;
		}
		String uname = req.getParameter("uname");
		String pswd = req.getParameter("pswd");
		int id = Integer.parseInt(req.getParameter("id"));

		if (!Functions.Login(uname, pswd, id)) {
			session.setAttribute("error", "Invalid username or password");
			resp.sendRedirect(dest);
			return;
		}

		session.setAttribute("usr", new User(id, uname));
		switch (id) {
			case STU:
				dest = root + "/stu/";
				break;
			case TEA:
				dest = root + "/tea/";
				break;
			case ADM:
				dest = root + "/reg/";
				break;
		}
		resp.sendRedirect(dest);
	}

	private boolean checkParams(Enumeration<String> values, int num) {
		ArrayList<String> vals = new ArrayList<>();
		while (values.hasMoreElements()) {
			vals.add(values.nextElement());
		}
		return num == vals.size();
	}
}
