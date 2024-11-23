package com.example.souklalla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class APPSTART extends AppCompatActivity {
private ViewPager2 viewPager2;
private List<SliderItem>sliderItems;
private SliderAdapter adapter;
private Handler sliderHandler =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_start);

        Button bt1=findViewById(R.id.button8);
        Button bt2=findViewById(R.id.button4);
         viewPager2 = findViewById(R.id.viewPagerImageSlider);
         sliderItems=new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.iv3));
        sliderItems.add(new SliderItem(R.drawable.iv4));
        sliderItems.add(new SliderItem(R.drawable.iv5));
        sliderItems.add(new SliderItem(R.drawable.iv6));
        sliderItems.add(new SliderItem(R.drawable.iv7));
        sliderItems.add(new SliderItem(R.drawable.iv2));
        sliderItems.add(new SliderItem(R.drawable.iv8));
        sliderItems.add(new SliderItem(R.drawable.iv9));
        sliderItems.add(new SliderItem(R.drawable.iv10));
        sliderItems.add(new SliderItem(R.drawable.iv1));
       adapter=new SliderAdapter(sliderItems,viewPager2);
       viewPager2.setAdapter(adapter);

        adapter = new SliderAdapter(sliderItems , viewPager2);
        viewPager2.setAdapter(adapter);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);



        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));



        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f +r *0.15f);
            }


        });

        viewPager2.setPageTransformer(transformer);


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,2000);
            }
        });

        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(APPSTART.this, WOMENHELLO.class);
            startActivity(intent);
        });



        bt1.setOnClickListener(v -> {
            Intent intent = new Intent(APPSTART.this, USERCATEGORY.class);
            startActivity(intent);
        });


    }
    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
       viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,2000);
    }

}