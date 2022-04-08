package com.wanted.wantedtrend.utils;

// valid check
public class ValidationChecker {

    // 빈칸 혹은 Null 이면 true를 리턴함
    public static boolean isNull(Object o){
        try {
            String str = (String)o;

            str = str.replace(" ", "");

            return str.equals("");

        } catch (NullPointerException e) {
            return true;
        }
    }
}
