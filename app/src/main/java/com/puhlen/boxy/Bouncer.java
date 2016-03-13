package com.puhlen.boxy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.util.List;
import java.util.Random;

/**
 * Created by claire on 09/11/15.
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
