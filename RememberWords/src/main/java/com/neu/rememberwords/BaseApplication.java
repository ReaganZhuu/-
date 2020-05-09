package com.neu.rememberwords;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 主要方法
 * 继承Application，贯穿整个App生命周期
 * @author Reagan_Zhu
 * @email 2668526325@qq.com
 * @date 2020/5/8 23:22
 */
public class BaseApplication extends Application {
    //创建一个Map的集合用来把activity加到这个map里面
    private static Map<String, Activity> destroyMap = new HashMap<>();

    /**
     * 要销毁的activity添加到销毁的列队
     */
    public static void addDestroyActiivty(Activity activity, String activityName) {
        destroyMap.put(activityName, activity);
    }

    /**
     * 销毁指定的activity
     */
    public static void destroyActivity(String activityName) {
        Set<String> keySet = destroyMap.keySet();
        for (String key : keySet) {
            destroyMap.get(key).finish();
        }
    }
}
