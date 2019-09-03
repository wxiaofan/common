package com.xf.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuexiang.xutil.XUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * SharePreference工具类，用于进行数据缓存以及获取
 */
public class SPUtil {
    private static String PreferenceName = "Constant";

    /**
     * 缓存对象
     *
     * @param key 缓存键
     * @param t   缓存的数值
     * @param <T> 数据类型
     * @return boolean 缓存状态，默认一般都是会成功的
     */
    public static <T> boolean saveObjectToShare(String key, T t) {
        return saveObjectToShare(XUtil.getContext(), PreferenceName, key, t);
    }

    /**
     * 缓存对象
     *
     * @param context 内容实体
     * @param key     缓存键
     * @param t       缓存数值
     * @param <T>     数据类型
     * @return boolean 缓存状态，默认一般都是会成功的
     */
    public static <T> boolean saveObjectToShare(Context context, String key, T t) {
        return saveObjectToShare(context, PreferenceName, key, t);
    }

    /**
     * 缓存对象
     *
     * @param context 内容实体
     * @param name    缓存器名称
     * @param key     键
     * @param t       数据
     * @param <T>     数据类型
     * @return boolean 缓存状态，默认一般都是成功的
     */
    public static <T> boolean saveObjectToShare(Context context, String name, String key, T t) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            Editor editor = sp.edit();
            if (t == null) {
                editor.putString(key, "");
                editor.commit();
                return true;
            }
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos;

            oos = new ObjectOutputStream(toByte);
            oos.writeObject(t);
            String payCityMapBase64 = new String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT));

            editor.putString(key, payCityMapBase64);
            editor.commit();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("错误", e);
            return false;
        }
    }

    /**
     * 从缓存中获得存储的对象
     *
     * @param key 键
     * @param <T> 数据类型
     * @return T 返回的数据
     */
    public static <T> T getObjectFromShare(String key) {
        return getObjectFromShare(XUtil.getContext(), PreferenceName, key);
    }

    /**
     * 从缓存中获取存储的对象
     *
     * @param context 内容实体
     * @param key     键
     * @param <T>     数据类型
     * @return T 返回数据
     */
    public static <T> T getObjectFromShare(Context context, String key) {
        return getObjectFromShare(context, PreferenceName, key);
    }

    /**
     * 从缓存中获取存储的对象
     *
     * @param context 内容实体
     * @param name    数据缓存器名称
     * @param key     键
     * @param <T>     数据类型
     * @return T 返回数据
     */
    public static <T> T getObjectFromShare(Context context, String name, String key) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            String payCityMapBase64 = sp.getString(key, "");
            if (payCityMapBase64.length() == 0) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(payCityMapBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 缓存字符串
     *
     * @param key   键
     * @param value 字符串数据
     */
    public static void saveString(String key, String value) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 得到缓存的字符串
     *
     * @param key 键
     * @return String 字符串数据
     */
    public static String getString(String key) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 缓存boolen类型数据
     *
     * @param key   key
     * @param value boolen 类型数据
     */
    public static void saveboolean(String key, boolean value) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 得到boolen类型数据
     *
     * @param key 键
     * @return boolean
     */
    public static boolean getBoolean(String key) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, true);
    }

    public static boolean getBooleanDefultFalse(String key) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static boolean getBooleanDefultFalse(String key, boolean b) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, b);
    }

    /**
     * 保存int类型数据
     *
     * @param key   键
     * @param value 缓存的整形数据
     */
    public static void saveInt(String key, int value) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 得到int类型数据
     *
     * @param key 键
     * @return int 缓存的数据，默认是0
     */
    public static int getInt(String key) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    public static int getInt(String key, int deft) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getInt(key, deft);
    }


    /**
     * 保存float类型数据
     *
     * @param key   键
     * @param value 缓存的数据
     */
    public static void saveFloat(String key, float value) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 得到float类型数据
     *
     * @param key 键
     * @return float 缓存的数据，默认是0
     */
    public static float getFloat(String key) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    /**
     * 保存long类型数据
     *
     * @param key   键
     * @param value 数据
     */
    public static void saveLong(String key, long value) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 得到long类型数据
     *
     * @param key 键
     * @return long 缓存的数据
     */
    public static long getLong(String key) {
        SharedPreferences sp = XUtil.getContext().getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }


    public static String[] getSharedPreference(String key) {
        String regularEx = "#";
        String[] str = null;
        SharedPreferences sp = XUtil.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String values;
        values = sp.getString(key, "");
        str = values.split(regularEx);

        return str;
    }

    public static void setSharedPreference(String key, String[] values) {
        String regularEx = "#";
        String str = "";
        SharedPreferences sp = XUtil.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        if (values != null && values.length > 0) {
            for (String value : values) {
                str += value;
                str += regularEx;
            }
            Editor et = sp.edit();
            et.putString(key, str);
            et.commit();
        }
    }


    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(String tag, List<T> datalist) {


        SharedPreferences sp = XUtil.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson;
        if (null == datalist || datalist.size() <= 0) {
            strJson = null;
        } else {
            strJson = gson.toJson(datalist);
        }
        edit.clear();
        edit.putString(tag, strJson);
        edit.commit();

    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(String tag) {
        List<T> datalist = new ArrayList<T>();
        SharedPreferences sp = XUtil.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);

        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;

    }





}
