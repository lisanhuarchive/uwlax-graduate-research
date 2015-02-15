import com.google.gson.Gson;
import org.lsh.data.*;
import org.lsh.data.control.DataCenter;
import org.lsh.helper.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lsh on 15/2/2.
 */
@WebServlet(name = "AttendCourse", urlPatterns = {"/attend_course"})
public class AttendCourse extends HttpServlet {

    private class Message {
        public String result = null;
        public String reason = null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String rid = req.getParameter("rid");
        String sid = req.getParameter("sid");
        Gson gson = new Gson();
        if (rid == null || sid == null) {
            Message msg = new Message();
            msg.result = "fail";
            msg.reason = "Parameter lost";
            out.println(gson.toJson(msg));
            return;
        }

        Record record = Functions.getRecordByRid(Integer.parseInt(rid));
        if (record == null) {
            out.println("No such record");
            Message msg = new Message();
            msg.result = "fail";
            msg.reason = "No such record";
            out.println(gson.toJson(msg));
            return;
        }

        Student student = Functions.getStudentById(sid);
        if (student == null) {
            Message msg = new Message();
            msg.result = "fail";
            msg.reason = "No such student";
            out.println(gson.toJson(msg));
            return;
        }

        if (record.getIsOpen() == 0) {
            Message msg = new Message();
            msg.result = "fail";
            msg.reason = "Recording closed";
            out.println(gson.toJson(msg));
            return;
        }

        if (record.getIsOpen() == 1) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String time = sdf.format(Calendar.getInstance().getTime());
            Course course = Functions.getCourseById(record.getCid());
            StudentCourse sc = Functions.getSCByStudentAndCourse(student, course);
            StudentRecord sr = new StudentRecord();
            sr.setRecordTime(time);
            sr.setScid(sc.getScid());
            sr.setRid(record.getRid());
            DataCenter.save(sr);
            Message msg = new Message();
            msg.result = "success";
            out.println(gson.toJson(msg));
        }
    }
}
