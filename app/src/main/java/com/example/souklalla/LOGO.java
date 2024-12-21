package com.example.souklalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class LOGO extends AppCompatActivity {
    TimerTask mStartActivityTask;
    final Handler mHandler = new Handler();
    Timer mTimer = new Timer();
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);

         imageView = findViewById(R.id.iv_LOGO);


        startFadeAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Set up a task to launch the new activity after the animation is finished
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewActivity(new Intent(LOGO.this, APPSTART.class));
            }
        }, 5000);  // Delay for the duration of the fade animation loop (5 seconds)
    }

    private void startFadeAnimation() {
        // Set the Fade In effect duration (2 seconds)
        final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(2000); // Duration of 2 seconds for the fade-in
        fadeIn.setFillAfter(true); // Keep the final state

        // Set the Fade Out effect duration (2 seconds)
        final AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(2000); // Duration of 2 seconds for the fade-out
        fadeOut.setFillAfter(true); // Keep the final state

        // Set the animation listeners to control the loop
        fadeIn.setAnimationListener(new AlphaAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {}

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                // Start fade-out after fade-in ends
                imageView.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {}
        });

        fadeOut.setAnimationListener(new AlphaAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {}

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {
                // Start fade-in after fade-out ends
                imageView.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {}
        });

        // Start the first animation (fade-in)
        imageView.startAnimation(fadeIn);
    }

    private void startNewActivity(Intent intent) {
        // Start the new activity and apply the fade transition
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);  // Use fade transition for the activity
    }
}