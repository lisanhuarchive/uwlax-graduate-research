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
 * Created by lsh on 14/11/20.
 */
@WebServlet(urlPatterns = {"/reg/disable_term"}, name = "disable_term")
public class DisableTerm extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s_id = req.getParameter("id");
		PrintWriter out = resp.getWriter();
		if (s_id != null && !"".equals(s_id)) {
			try {
				Term term = (Term) DataCenter.select("from Term t where t.id = ?", Integer.parseInt(s_id));
				term.setActivated(false);
				DataCenter.update(term);
				// todo success stub
				out.println("success");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				// todo fail stub
				out.println("fail");
			}
		} else {
			// todo fail stub
			out.println("fail");
		}
	}
}
