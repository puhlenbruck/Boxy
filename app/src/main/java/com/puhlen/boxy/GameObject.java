package com.puhlen.boxy;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by claire on 05/11/15.
 */
public abstract class GameObject {
    protected int x = 0;
    protected int y = 0;
    protected int dy = 0;
    protected int dx = 0;
    protected int width = 0;
    protected int height = 0;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Rect getBoundingBox() {
        return new Rect(x, y, x + width, y + height);
    }

    public void move() {
        x += dx;
        y += dy;
        if (dx != 0 && dy != 0);
    }

    public abstract void update();

    public abstract void draw(Canvas canvas);
}
