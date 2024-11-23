package com.example.souklalla;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class SuccessfullPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.successfull_pass);

        ImageView imageView = findViewById(R.id.imageSUCCESS);


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
                        startActivity(new Intent(SuccessfullPass.this, WOMENHOMEPAGE.class));
                    }
                });
            }
        };

        // This will start the task with 10 seconds delay with no intervals.
        mTimer.schedule(mStartActivityTask, 2000, 1);
    }

    private void startNewActivity(Intent i) {

        mTimer.cancel();// To prevent multiple invocations
        View imageView = findViewById(R.id.imageSUCCESS);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,imageView,"imageSUCCESS");

        startActivity(i, activityOptionsCompat.toBundle());            // Start new activity
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

    }

}