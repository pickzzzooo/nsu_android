package com.example.nsu_android_project;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.splash_activity);

        Handler handler = new Handler();

        ImageView aniImg = (ImageView) findViewById(R.id.imageView);
        aniImg.setBackgroundResource(R.drawable.animation);
        animationDrawable = (AnimationDrawable) aniImg.getBackground();
        animationDrawable.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000); // 2초 있다 메인액티비티로
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
