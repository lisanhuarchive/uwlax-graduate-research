package org.lsh.servlets;

import org.lsh.data.Term;
import org.lsh.data.control.DataCenter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lsh on 14/11/19.
 */
@WebServlet(urlPatterns = {"/reg/update_term"}, name = "update_term")
public class UpdateTerm extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s_id = req.getParameter("id");
		String name = req.getParameter("name");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		PrintWriter out = resp.getWriter();

		if (!checkParam(s_id) || !checkParam(name) || !checkParam(start) || !checkParam(end)) {
			// todo Fail stub
			out.println("Fail");
			return;
		}

		try {
			int id = Integer.parseInt(s_id);
			Term term = new Term();
			term.setTermId(id);
			term.setTermName(name);
			term.setTermStart(start);
			term.setTermEnd(end);
			term.setActivated(true);
			DataCenter.update(term);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// todo Fail stub
			out.println("Fail");
			return;
		}

		// todo success stub
		out.println("Success");
	}

	private boolean checkParam(String param) {
		if (null != param && !"".equals(param)) {
			return true;
		}

		return false;
	}
}
