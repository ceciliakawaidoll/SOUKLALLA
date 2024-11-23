package com.example.souklalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class LOGO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);


        ImageView imageView = findViewById(R.id.LOGO);




    }
    TimerTask mStartActivityTask;
    final Handler mHandler = new Handler();
    Timer mTimer = new Timer();

    @Override
    protected void onResume() {

        super.onResume();
        mStartActivityTask = new TimerTask() {
            public void run() {
                mHandler.post(new Runnable() {
                    public void run() {
                        startNewActivity(new Intent(LOGO.this, APPSTART.class));
                    }
                });
            }
        };

        // This will start the task with 10 seconds delay with no intervals.
        mTimer.schedule(mStartActivityTask, 2000, 1);
    }

    private void startNewActivity(Intent i) {

        mTimer.cancel();// To prevent multiple invocations
        View imageView = findViewById(R.id.LOGO);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,imageView,"LOGO");

        startActivity(i, activityOptionsCompat.toBundle());            // Start new activity
     overridePendingTransition(R.anim.fadein, R.anim.fadeout);

    }
}