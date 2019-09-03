package com.xf.base.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;


/**
 * Created by wangchengcheng on 16/8/5.
 * Activity的管理类
 */
public class ActivityManager {
    private static Context context;

    private static ActivityManager activityManager;
    private static Stack<Activity> activityStack;

    public static ActivityManager getActivityManager(Context context) {
        if (activityManager == null) {
            activityManager = new ActivityManager(context);
        }
        return activityManager;
    }

    private ActivityManager() {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
    }

    private ActivityManager(Context conetext) {
        this.context = conetext;
    }


    /**
     * 向运用task map里面添加activity
     *
     * @param activity: activity
     */
    public final void putActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * @return activity
     */
    public Stack<Activity> getActivities() {
        return activityStack;
    }

    /**
     * 获取顶层Activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中后一个压入的Activity)
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            removeActivity(activity);
        }
    }


    /**
     * 移除task栈里的某个activity
     *
     * @param activity: activity
     */
    public final void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束除开指定Activity的其他Activity
     */
    public void finishOtherActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (!activityStack.get(i).getClass().equals(cls)) {
                this.finishActivity(activityStack.get(i));
                //如果不加这一行，remove之后当前元素的下标跟变化之后的size完全不对应，可能会跳过几个元素没有检查或者下标会大于size造成数组越界
                i = -1;
            }
        }
    }

    /**
     * 结束除开指定Activity:MainActivity的其他Activity
     */
//    public void finishOtherHomeActivity(Class<?> cls) {
//
//        for (int i = 0; i < activityStack.size(); i++) {
//            if (!activityStack.get(i).getClass().equals(cls)
//                    && !activityStack.get(i).getClass().equals(MainActivity.class)) {
//                this.finishActivity(activityStack.get(i));
//                i = -1;
//            }
//        }
//
//    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                this.finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        //移出Stack中所有的元素
        activityStack.clear();
    }


    /**
     * 清除运用的task栈,如果程序正常运行会导致运用退出桌面
     */
    public final void exit() {
        try {
            this.finishAllActivity();
            //MobclickAgent.onKillProcess(context);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
