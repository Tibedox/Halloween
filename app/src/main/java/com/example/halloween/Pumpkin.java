package com.example.halloween;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class Pumpkin {
    private PumpkinActivity activity;
    private ImageView view;
    private float x, y;
    private float vx, vy;
    public boolean isAlive = true;

    public Pumpkin(PumpkinActivity activity) {
        this.activity = activity;
        Random rnd = new Random();
        vx = rnd.nextFloat()*21-10;
        vy = rnd.nextFloat()*21-10;

        view = new ImageView(activity.getApplicationContext());
        view.setImageResource(R.drawable.pumpkin);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(rnd.nextInt(200)+150, rnd.nextInt(200)+150);
        view.setLayoutParams(layoutParams);
        view.setX(500);
        view.setY(500);
        activity.bg.addView(view);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAlive = false;
                view.setX(-1000);
            }
        };
        view.setOnClickListener(listener);
    }

    public void move() {
        if(isAlive) {
            x = view.getX();
            x += vx;
            if (x > activity.screenWidth - view.getWidth() | x < 0) vx = -vx;
            view.setX(x);
            y = view.getY();
            y += vy;
            if (y > activity.screenHeight - activity.statusBarHeight - view.getHeight() | y < 0) vy = -vy;
            view.setY(y);
        }
    }
}