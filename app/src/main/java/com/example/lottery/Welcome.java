package com.example.lottery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.lottery.IntroPages.IntroPages;
import com.example.lottery.Manager.CacheManager;

public class Welcome extends AppCompatActivity {
    private ImageView welcome;
    public static final String launcher_code="first_timer";
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
            if(isFirst){

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