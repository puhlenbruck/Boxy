package com.puhlen.boxy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by claire on 05/11/15.
 */
public class Follower extends GameObject {
    private static final int sideLength = 50;

    private Rect sprite;

    private Paint paint = new Paint();

    private final int target;

    public Follower(int target) {
        width = sideLength;
        height = sideLength;
        paint.setARGB(255, 0, 0, 255);
        sprite = new Rect(x, y, x + width, y + height);
        this.target = target;
    }

    public void update() {
        move();
        updateRect();
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(sprite, paint);
    }

    private void updateRect() {
        sprite.offsetTo(x,y);
    }

    public int getTarget(){
        return target;
    }

    public String toString(){
        return "The Follower";
    }
}
