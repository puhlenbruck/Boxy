package com.puhlen.boxy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by claire on 03/11/15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread mainThread;
    private Activity game;
    private static List<Rect> bounds = new ArrayList<>(4);
    private List<GameObject> enemies = new LinkedList<>();
    private List<Clicker> clickers = new LinkedList<>();
    private int numClickers = 1;

    public GamePanel(Activity game) {
        super(game);
        //
        getHolder().addCallback(this);
        this.game = game;

        mainThread = new MainThread(getHolder(), this);

        setFocusable(true);
        DisplayMetrics metrics = game.getResources().getDisplayMetrics();
        bounds.add(new Rect(0, 0, -1, metrics.heightPixels));
        bounds.add(new Rect(0, 0, metrics.widthPixels, -1));
        bounds.add(new Rect(metrics.widthPixels, 0, metrics.widthPixels + 1, metrics.heightPixels));
        bounds.add(new Rect(0, metrics.heightPixels, metrics.widthPixels, metrics.heightPixels + 1));

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        enemies.add(new Follower(0));
        enemies.add(new Bouncer(this.getContext()));
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
                Log.i(this.toString(), "Thread ending");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        if (clickers.isEmpty()) {
            for (int i = 0; i < numClickers; i++) {
                clickers.add(Clicker.randomObstacle(getContext(), 100));
            }
            for (GameObject ob : enemies) {
                if (ob instanceof Follower) {
                    int t = ((Follower) ob).getTarget();
                    ob.setTarget(clickers.get(t).centreX(), clickers.get(t).centreY());
                }
            }
        }
        for (GameObject enemy : enemies) {
            enemy.update();
        }

        checkForLoss();
    }

    private void checkForLoss() {
        for (Clicker c:clickers) {
            for(GameObject enemy: enemies){
                if(c.getBoundingBox().intersect(enemy.getBoundingBox())){
                    lose();
                }
            }
        }
    }

    private void lose() {
        mainThread.setRunning(false);
        Intent scoreScreen = new Intent(game, ScoreActivity.class);
        scoreScreen.putExtra("score",ScoreLabel.getScoreLabel().score);
        game.startActivity(scoreScreen);
        game.finish();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        ScoreLabel.getScoreLabel().draw(canvas);
        for (Clicker clicker : clickers) {
            clicker.draw(canvas);
        }

        for (GameObject ob : enemies) {
            ob.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Clicker clicker : clickers) {
                    if (clicker.contains(x, y)) {
                        clickers.remove(clicker);
                        if (clickers.isEmpty()) {
                            scorePoint();
                        }
                        break;
                    }

                }

        }
        return true;
    }


    private void scorePoint(){
        final int score = ++ScoreLabel.getScoreLabel().score;
        if(score%20==0){
            enemies.add(new Bouncer(this.getContext()));
        }
        if(score%50==0){
            numClickers++;
            enemies.add(new Follower(score/50));
        }
    }

    public static List<Rect> getBounds(){
        return bounds;
    }
}
