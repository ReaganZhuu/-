package com.neu.rememberwords;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

/**
 * 设置屏幕状态的监听
 * @author Reagan_Zhu
 * @email 2668526325@qq.com
 * @date 2020/5/8 23:33
 */
public class ScreenListener {
    private Context context;        //联系上下文
    private ScreenBroadcastReceiver mScreenReceiver;    //定义一个广播
    private ScreenStateListener mScreenStateListener;   //定义一个内部接口

    public ScreenListener(Context context) {
        this.context = context;
        mScreenReceiver =new ScreenBroadcastReceiver();//初始化广播
    }

    /**
     * 写一个内部的广播
     * */
    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action = null;
        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)){        //开屏操作
                mScreenStateListener.onScreenOn();
            }else if (Intent.ACTION_SCREEN_OFF.equals(action)){     //锁屏操作
                mScreenStateListener.onScreenOff();
            }else if (Intent.ACTION_USER_PRESENT.equals(action)) {      //解锁操作
                mScreenStateListener.onUserPresent();
            }
        }
    }

    /**
     * 自定义接口
     */
    private interface ScreenStateListener {
        void onScreenOn();//手机屏幕点亮
        void onScreenOff();//手机屏幕关闭
        void onUserPresent();//手机屏幕解锁
    }

    /**
     * 获取screen的状态
     * */
    private void getScreenState() {
        //初始化powerManager
        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (manager.isScreenOn()){//如果监听已经开启
            if (mScreenStateListener != null){
                mScreenStateListener.onScreenOn();
            }
        }else {//如果监听没开启
            if (mScreenStateListener != null){
                mScreenStateListener.onScreenOff();
            }
        }
    }

    /**
     * 开始监听广播状态
     * */

    public void begin(ScreenStateListener listener){
        mScreenStateListener = listener;
        registerListener();//注册监听
        getScreenState();//获取监听
    }


    /**
     * 启动广播接收器
     * */
    private void registerListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);//屏幕亮起时开启的广播
        filter.addAction(Intent.ACTION_SCREEN_OFF);//屏幕关闭时开启的广播
        filter.addAction(Intent.ACTION_USER_PRESENT);//屏幕解锁时开启的广播
        context.registerReceiver(mScreenReceiver, filter);//发送广播

    }



    /**
     * 解除广播
     * */
    public void unregisterListener(){
        context.unregisterReceiver(mScreenReceiver); //注销广播
    }

}
