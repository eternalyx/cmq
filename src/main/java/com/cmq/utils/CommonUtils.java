package com.cmq.utils;

import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

    /**
     * 年龄计算
     * @param birthday
     * @return
     */
    public static int calculateAgeByBirthday(Date birthday){
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }
}
