package com.example.halloween;

public class Ghost extends Pumpkin {

    public Ghost(PumpkinActivity activity) {
        super(activity);
        view.setImageResource(R.drawable.ghost);
    }

    @Override
    public void move() {
        if(isAlive) {
            x = view.getX();
            x += vx;
            if (x > activity.screenWidth) x = -view.getWidth();
            if (x < -view.getWidth()) x = activity.screenWidth;
            view.setX(x);
            y = view.getY();
            y += vy;
            if (y > activity.screenHeight - activity.statusBarHeight) y = -view.getHeight();
            if (y < -view.getHeight()) y = activity.screenHeight - activity.statusBarHeight;
            view.setY(y);
        }
    }
}
