package util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.neu.writeNumbers.R;

/**
 * 欢乐写数字
 *
 * @author Reagan_Zhu
 * @email 2668526325@qq.com
 * @date 2020/5/6 11:12
 */
public class mCustomProgressDialog extends ProgressDialog {
    private AnimationDrawable mAnimarion;//设置对话框动画资源
    private Context mContext;//设置对话框上下文
    private ImageView mImageView;//设置对话框背景图片
    private String mLoadingTip;//设置对话框文字
    private TextView mLoadingTv;//显示对话框文字
    private int mResid;//设置动画资源的id

    public  mCustomProgressDialog(Context context,String content,int id){
        super(context);
        this.mContext=context;
        this.mLoadingTip=content;
        this.mResid=id;
        //单击周边让dialog消失
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        mLoadingTv = findViewById(R.id.loadingTv);
        mImageView=findViewById(R.id.loading);
        if (mResid==0){
            mImageView.setBackgroundDrawable(null);
        }else {
            mImageView.setBackgroundResource(mResid);
        }
        mAnimarion= (AnimationDrawable) mImageView.getBackground();
        //防止只显示第一帧
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimarion.start();
            }
        });
        //设置文字显示
        mLoadingTv.setText(mLoadingTip);
    }

}
