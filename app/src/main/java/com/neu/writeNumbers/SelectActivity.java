package com.neu.writeNumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class SelectActivity extends Activity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        if (MainActivity.isPlaying==true){
            playMusic();
        }
    }

    private void playMusic() {
        mediaPlayer=MediaPlayer.create(this,R.raw.together);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (MainActivity.isPlaying==true){
            playMusic();
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

    //访问数字页面时背景音乐停止
    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    public void onOne(View v){
        startActivity(new Intent(SelectActivity.this,OneActivity.class));
    }
}
