package com.xf.base.utils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;


public class LogUtils {


    /**
     * 是否开启debug
     * 注意：使用Eclipse打包的时候记得取消Build Automatically，否则一直是true
     */
    public static boolean isDebug = true;

    /**
     * 错误
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).e(msg + "");
        }
    }

    public static void e(String tag, Object o) {
        if (isDebug) {
            Logger.t(tag).e(new Gson().toJson(o) + "");
        }
    }

    /**
     * 调试
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).d(msg + "");
        }
    }

    public static void d(String tag, Object msg) {
        if (isDebug) {
            Logger.t(tag).d(new Gson().toJson(msg) + "");
        }
    }


    /**
     * 信息
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).i(msg + "");
        }
    }

    public static void i(String tag, Object msg) {
        if (isDebug) {
            Logger.t(tag).i(new Gson().toJson(msg) + "");
        }
    }
}
