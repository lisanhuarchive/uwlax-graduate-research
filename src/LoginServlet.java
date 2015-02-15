import com.google.gson.Gson;
import org.lsh.data.control.User;
import org.lsh.helper.Constants;
import org.lsh.helper.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lsh on 15/1/27.
 */
@WebServlet(name = "Device Login", urlPatterns = {"/login_result"})
public class LoginServlet extends HttpServlet {
    private class Message {
        public String result = "success";
        public User user = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        String pswd = req.getParameter("pswd");
        int id = Integer.parseInt(req.getParameter("id"));
        Message msg = new Message();
        Gson gson = new Gson();

        PrintWriter out = resp.getWriter();

        if (id == Constants.STU && Functions.Login(uname, pswd, id)) {
            msg.user = new User(id, uname);
            out.println(gson.toJson(msg));
        } else {
            msg.result = "fail";
            out.print(gson.toJson(msg));
        }
    }
}
