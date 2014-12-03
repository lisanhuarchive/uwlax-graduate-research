package org.lsh.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lsh on 14/12/3.
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String time = sdf.format(Calendar.getInstance().getTime());
        System.out.println(time);
    }
}
