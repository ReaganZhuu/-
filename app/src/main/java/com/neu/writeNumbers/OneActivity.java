package com.neu.writeNumbers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.Timer;

import util.mCustomProgressDialog;

/**
 * 欢乐写数字
 *
 * @author Reagan_Zhu
 * @email 2668526325@qq.com
 * @date 2020/5/6 10:09
 */
public class OneActivity extends Activity {
    mCustomProgressDialog mdialog;
    private ImageView iv_frame;//显示写数字的ImageView控件
    int i =1;//图片展示到第几张标记
    float x1;//屏幕按下时x坐标
    float y1;//屏幕按下时y坐标
    float x2;//屏幕离开时x坐标
    float y2;//屏幕离开时y坐标
    float x3;//移动中的x坐标
    float y3;//移动中的y坐标

    int igvx;//图片x坐标
    int igvy;//图片y坐标
    int type=0;//是否可以书写标识

    int widthPixel;//屏幕宽度
    int heightPixel;//屏幕高度
    float scaleWidth;//宽度的缩放比例
    float scaleHeight;//高度的缩放比例
    Timer touchTimer=null;//用于连续动作的计时器
    Bitmap arrdow;//Bitmap图像处理
    boolean typedialog=true;//dialog对话框状态
    private LinearLayout linearLayout=null;//LinearLayout线性布局

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
    }
    public void OnYS(View v){
        if (mdialog==null){
            mdialog=new mCustomProgressDialog(this,"演示中，点击边缘取消演示动画",R.drawable.frame);
        }
        mdialog.show();
    }
}
