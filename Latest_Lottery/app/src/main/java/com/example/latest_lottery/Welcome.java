package com.example.latest_lottery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.latest_lottery.IntroPages.*;
import com.example.latest_lottery.Manager.*;

/**
 * This page is used to generate a welcome page and a simple animation to the user
 *
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class Welcome extends AppCompatActivity {
    private ImageView welcome;
    public static final String launcher_code="new_install";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        animation();

    }

    class AnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            boolean isFirst= CacheManager.isFirstTime(Welcome.this,launcher_code);
            //Use the logic to handle the welcome page and direct to the proper page if the user is a first time user(should show the three pages welcome slide for first time user)
            if(isFirst){
                Intent intent=new Intent(Welcome.this, Home.class);
                startActivity(intent);
            }
            else{
                Intent intent=new Intent(Welcome.this, IntroPages.class);
                startActivity(intent);

            }
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


    /**
     * Area for the utility function definition
     *
     */
    void animation(){
        welcome=findViewById(R.id.welcome);


        AlphaAnimation animation=new AlphaAnimation(0,1);
        animation.setDuration(2300);
        animation.setFillAfter(true);

        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.6f);
        scaleAnimation.setDuration(2300);
        scaleAnimation.setFillAfter(true);

        RotateAnimation rotateAnimation=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2300);
        rotateAnimation.setFillAfter(true);

        AnimationSet animate=new AnimationSet(false);
        animate.addAnimation(scaleAnimation);
        animate.addAnimation(rotateAnimation);
        animate.addAnimation(animation);

        welcome.startAnimation(animate);
        animate.setAnimationListener(new AnimationListener());
    }
}