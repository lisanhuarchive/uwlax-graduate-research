package org.lsh.servlets.admin;

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
@WebServlet(urlPatterns = {"/reg/add_term"}, name = "addterm")
public class AddTerm extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		PrintWriter out = resp.getWriter();

		if (!checkParam(name) || !checkParam(start) || !checkParam(end)) {
			// todo fail stub
			out.println("Fail");
			return;
		}

		Term t = new Term();
		t.setTermName(name);
		t.setTermStart(start);
		t.setTermEnd(end);
		t.setActivated(true);

		DataCenter.save(t);

		//todo success stub
		out.println("Success");
	}

	private boolean checkParam(String param) {
		if (null != param && !"".equals(param)) {
			return true;
		}

		return false;
	}

}
