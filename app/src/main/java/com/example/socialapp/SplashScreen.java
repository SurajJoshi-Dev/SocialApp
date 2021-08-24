package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
TextView from,surajjoshi;
long animTime=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        from=findViewById(R.id.From);
        surajjoshi=findViewById(R.id.suraj_joshi);
       ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(from,"Y",1600f);
       ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(surajjoshi,"Y",1660f);

    objectAnimator.setDuration(animTime);
    objectAnimator1.setDuration(animTime);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(objectAnimator,objectAnimator1);
        animatorSet.start();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,Login.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },6000);
    }

}