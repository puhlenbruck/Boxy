package com.puhlen.boxy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by claire on 03/11/15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread mainThread;
    private Obstacle sq = null;
    private Player player = null;

    public GamePanel(Context context) {
        super(context);
        //
        getHolder().addCallback(this);

        mainThread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player = Player.getInstance();
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
        if (sq == null) {
            sq = Obstacle.randomObstacle(getContext(), 100);
            player.setTarget(sq.centreX(),sq.centreY());
        }
        player.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        ScoreLabel.getScoreLabel().draw(canvas);
        sq.draw(canvas);
        player.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ((x > sq.getX() && x < sq.getX() + sq.getSize()) && (y > sq.getY() && y < sq.getY() + sq.getSize())) {
                    scorePoint();
                }

        }
        return true;
    }

    private void scorePoint(){
        ScoreLabel.getScoreLabel().score++;
        sq = null;
    }
}
