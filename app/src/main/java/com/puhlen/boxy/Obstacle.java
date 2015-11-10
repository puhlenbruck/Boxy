package com.puhlen.boxy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by claire on 05/11/15.
 */
public final class Obstacle extends GameObject {
    private final Rect r;
    private final Paint paint = new Paint();

    public Obstacle(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        r = new Rect(x, y, x + size, y + size);
        paint.setARGB(255, 255, 0, 0);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(r, paint);
    }

    public static Obstacle randomObstacle(Context context, int size) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int scrWidth = metrics.widthPixels;
        int scrHeight = metrics.heightPixels;

        Random r = new Random();

        int x = r.nextInt(scrWidth - size);
        int y = r.nextInt((scrHeight - size) - 100) + 100;

        return new Obstacle(x, y, size);
    }

    public void update() {
    }
    public int centreX(){
        return r.centerX();
    }

    public int centreY(){
        return r.centerY();
    }

    public int getSize() {
        return height;
    }
}
