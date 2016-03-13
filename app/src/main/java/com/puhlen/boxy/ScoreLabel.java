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
