package com.xf.base.utils;

public class ReStrUtils {
    public static String reNo(String realNo) {
        String newNo = realNo;
        if (realNo.length() == 15)
            newNo = realNo.replaceAll("(\\d{4})\\d{7}(\\d{4})", "$1*******$2");
        if (realNo.length() == 18)
            newNo = realNo.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1**********$2");
        return newNo;
    }
    //名字加*号 

    public static String reName(String realname) {
        char[] r = realname.toCharArray();
        String name = realname;
        if (r.length == 1) name = realname;
        if (r.length == 2) name = realname.replaceFirst(realname.substring(1), "*");
        if (r.length > 2)
            name = realname.replaceFirst(realname.substring(1, r.length - 1), "*");
        return name;
    }  //手机号加*号   

    public static String rePhone(String realPhone) {
        String phoneNumber = realPhone;
        if (realPhone.length() == 11)
            phoneNumber = realPhone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        else
            phoneNumber = realPhone;
        return phoneNumber;
    }
}

