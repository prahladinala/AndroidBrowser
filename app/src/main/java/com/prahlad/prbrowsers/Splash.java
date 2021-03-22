package com.prahlad.prbrowsers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Splash extends AppCompatActivity {

    RelativeLayout mLinearLayout;

    ImageView browser_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread xyz = new Thread(){
            public void run()
            {
                try{
                    sleep(2000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                }
            }

        };
        xyz.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

