package com.neu.writeNumbers;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    static boolean isPlaying=true;
    MediaPlayer mediaPlayer;
    ImageButton music_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music_btn=findViewById(R.id.btn_music);
        playMusic();
    }
    //默认播放
    private void playMusic() {
        mediaPlayer=MediaPlayer.create(this,R.raw.han10cm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    //跳转游戏
    public void onPlay(View v){
        startActivity(new Intent(MainActivity.this,SelectActivity.class));
    }
    //跳转关于
    public void onAbout(View v){
        startActivity(new Intent(MainActivity.this,AboutActivity.class));
    }
    //控制音乐开关
    public void onMusic(View v){
        if (isPlaying==true){
            if (mediaPlayer!=null){
                mediaPlayer.stop();
                music_btn.setBackgroundResource(R.drawable.music_stopping);
                isPlaying=false;
            }
        }else {
            playMusic();
            music_btn.setBackgroundResource(R.drawable.music_playing);
            isPlaying=true;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isPlaying==true){
            playMusic();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
