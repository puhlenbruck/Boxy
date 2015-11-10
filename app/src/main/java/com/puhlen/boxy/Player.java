package com.puhlen.boxy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by claire on 05/11/15.
 */
public class Player extends GameObject {
    private static Player ourInstance = null;
    private static final int sideLength = 50;

    private Rect sprite;

    private Paint paint = new Paint();

    private int targetX, targetY;

    public static Player getInstance() {
        if (ourInstance == null) {
            ourInstance = new Player();
        }
        return ourInstance;
    }

    private Player() {
        width = sideLength;
        height = sideLength;
        paint.setARGB(255, 0, 0, 255);
        sprite = new Rect(x, y, x + width, y + height);
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

    public void setTarget(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
        int totaldx = targetX - sprite.centerX();
        int totaldy = targetY - sprite.centerY();

        double xRatio = (double)totaldx/(Math.abs(totaldx) + Math.abs(totaldy));
        double yRatio = (double)totaldy/(Math.abs(totaldx) + Math.abs(totaldy));

        double xrm = xRatio * 10;
        double yrm = yRatio * 10;

        dx = (int) Math.round(xrm);
        dy = (int)Math.round(yrm);

    }

    public String toString(){
        return "The Player";
    }
}
