/*
 *    Copyright 2016 Peter Uhlenbruck
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.puhlen.boxy;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by claire on 05/11/15.
 */
public abstract class GameObject {
    protected int x = 0;
    protected int y = 100;
    protected int dy = 0;
    protected int dx = 0;
    protected int width = 0;
    protected int height = 0;
    private int targetX, targetY;

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
        if (dx != 0 && dy != 0) ;
    }

    public void setTarget(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
        int totaldx = targetX - getBoundingBox().centerX();
        int totaldy = targetY - getBoundingBox().centerY();

        double xRatio = (double) totaldx / (Math.abs(totaldx) + Math.abs(totaldy));
        double yRatio = (double) totaldy / (Math.abs(totaldx) + Math.abs(totaldy));

        double xrm = xRatio * 7;
        double yrm = yRatio * 7;

        dx = (int) Math.round(xrm);
        dy = (int) Math.round(yrm);

    }

    public abstract void update();

    public abstract void draw(Canvas canvas);
}
