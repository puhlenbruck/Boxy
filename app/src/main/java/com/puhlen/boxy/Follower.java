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
