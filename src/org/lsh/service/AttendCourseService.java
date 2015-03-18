package org.lsh.service;

import org.joda.time.LocalTime;
import org.lsh.data.Course;

/**
 * Created by lsh on 15/2/15.
 */
public class AttendCourseService {

    private static LocalTime parseTime(String timeString) {
        String[] parts = timeString.split(":");
        LocalTime time = new LocalTime(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        return time;
    }

    public static boolean isInValidTime(Course course) {
        String[] parts = course.getcTime().split("-");
        LocalTime now = new LocalTime();
        LocalTime start = parseTime(parts[0]);
        start = start.minusMinutes(10);
        LocalTime end = parseTime(parts[1]);
        end = end.minusMinutes(-10);
        if (now.isAfter(start) && now.isBefore(end)) {
            return true;
        }

        return false;
    }
}
