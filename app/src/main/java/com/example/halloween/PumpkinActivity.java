package com.example.halloween;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PumpkinActivity extends AppCompatActivity {
    private Pumpkin[] pumpkin = new Pumpkin[5];
    public int screenWidth, screenHeight;
    public int statusBarHeight;
    public ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumpkin);

        screenWidth = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        screenHeight = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        statusBarHeight = getStatusBarHeight();
        bg = findViewById(R.id.grave);

        for (int i = 0; i < pumpkin.length; i++) {
            pumpkin[i] = new Pumpkin(this);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < pumpkin.length; i++) {
                    pumpkin[i].move();
                }
                if(isGameOver()){
                    Intent intent = new Intent(PumpkinActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10);
    }

    private boolean isGameOver(){
        for (int i = 0; i < pumpkin.length; i++) {
            if(pumpkin[i].isAlive) return false;
        }
        return true;
    }

    private int getStatusBarHeight() {
        int height = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }
}