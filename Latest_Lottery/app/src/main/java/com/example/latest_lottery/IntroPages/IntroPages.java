package com.example.latest_lottery.IntroPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.latest_lottery.*;
import com.example.latest_lottery.Manager.*;
import com.example.latest_lottery.R;

import java.util.ArrayList;

/**
 * The Intro pages will display 3 slides of welcome pages for the new user
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class IntroPages extends AppCompatActivity {

    private ViewPager viewpager;
    private Button button;
    private LinearLayout linearLayout;
    private ArrayList<ImageView> imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_pages);
        viewpager=(ViewPager) findViewById(R.id.viewpage);
        button=(Button) findViewById(R.id.btn_start);
        linearLayout=(LinearLayout) findViewById(R.id.linear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CacheManager.add_cache(IntroPages.this,"new_install",true);
                Intent intent=new Intent(IntroPages.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        int[] img_data=new int[]{
                R.drawable.welcome1,
                R.drawable.welcome2,
                R.drawable.welcome3,
        };
        imageViews =new ArrayList<>();
        for(int i=0; i<img_data.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(img_data[i]);
            imageViews.add(imageView);
        }
        viewpager.setAdapter(new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView=imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //super.destroyItem(container, position, object);
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return imageViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }


        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==imageViews.size()-1)//ADD ANIMATION
                    button.setVisibility(View.VISIBLE);
                else
                    button.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}