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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.util.List;
import java.util.Random;

/**
 * Peter
 */
public class Bouncer extends GameObject{
    private Rect sprite;
    private Paint paint = new Paint();
    private static final int sideLength = 50;
    private static final Random r = new Random();

    public Bouncer(Context context) {
        width = sideLength;
        height = sideLength;
        paint.setARGB(255, 255, 255, 0);
        sprite = new Rect(x, y, x + width, y + height);
        changeDirection(context);
    }

    public void changeDirection(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int scrWidth = metrics.widthPixels;
        int scrHeight = metrics.heightPixels;



        int x = r.nextInt(scrWidth - sideLength);
        int y = r.nextInt((scrHeight - sideLength) - 100) + 100;

        setTarget(x, y);
    }

    @Override
    public void draw(Canvas canvas){canvas.drawRect(sprite, paint);}

    @Override
    public void update(){
        move();
        updateRect();

        List<Rect> bounds = GamePanel.getBounds();
        for (int i = 0; i < bounds.size(); i++) {
            if(new Rect(bounds.get(i)).intersect(sprite)){
                bounce(i);
                break;
            }
        }
    }

    private void bounce(int side){
        switch (side){
            case 0:
                dx = -dx;
                break;
            case 1:
                dy = -dy;
                break;
            case 2:
                dx = -dx;
                break;
            case 3:
                dy = -dy;
                break;
        }
    }

    private void updateRect() {
        sprite.offsetTo(x, y);
    }
}
