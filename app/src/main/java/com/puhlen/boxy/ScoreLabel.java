package com.puhlen.boxy;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by claire on 05/11/15.
 */
public class ScoreLabel {
    private final Paint style = new Paint();

    private static ScoreLabel sl = null;
    public int score = 0;

    private ScoreLabel(){
        style.setARGB(255, 255, 255, 255);
        style.setTextSize(80);
    }

    public static ScoreLabel getScoreLabel(){
        if (sl == null) {
            sl = new ScoreLabel();
        }
        return sl;
    }

    public void draw(Canvas canvas){
        String text = "Score: " + score;
        canvas.drawText(text,10,100,style);
    }

}
