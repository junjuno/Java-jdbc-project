package com.project.shop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueIdUtil {
    public static int generateIdByTime() {
        return Integer.parseInt(new SimpleDateFormat("ddHHmmss").format(new Date()));
    }
}
