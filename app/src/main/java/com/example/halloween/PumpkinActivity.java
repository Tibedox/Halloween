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
    Pumpkin[] pumpkin = new Pumpkin[5];
    int screenWidth, screenHeight;
    int statusBarHeight;
    ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pumpkin);

        screenWidth = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        screenHeight = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        statusBarHeight = getStatusBarHeight();
        bg = findViewById(R.id.grave);

        for (int i = 0; i < pumpkin.length; i++) {
            pumpkin[i] = new Pumpkin();
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
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10);
    }

    boolean isGameOver(){
        for (int i = 0; i < pumpkin.length; i++) {
            if(pumpkin[i].isAlive) return false;
        }
        return true;
    }

    public int getStatusBarHeight() {
        int height = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    class Pumpkin {
        ImageView view;
        float x, y;
        float vx, vy;
        boolean isAlive = true;

        Pumpkin() {
            Random rnd = new Random();
            vx = rnd.nextFloat()*21-10;
            vy = rnd.nextFloat()*21-10;

            view = new ImageView(getApplicationContext());
            view.setImageResource(R.drawable.pumpkin);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(rnd.nextInt(200)+150, rnd.nextInt(200)+150);
            view.setLayoutParams(layoutParams);
            view.setX(500);
            view.setY(500);
            bg.addView(view);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isAlive = false;
                    view.setX(-1000);
                }
            };
            view.setOnClickListener(listener);
        }

        void move() {
            if(isAlive) {
                x = view.getX();
                x += vx;
                if (x > screenWidth - view.getWidth() | x < 0) vx = -vx;
                view.setX(x);
                y = view.getY();
                y += vy;
                if (y > screenHeight - statusBarHeight - view.getHeight() | y < 0) vy = -vy;
                view.setY(y);
            }
        }
    }
}