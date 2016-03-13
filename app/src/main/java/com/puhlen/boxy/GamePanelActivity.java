package com.puhlen.boxy;

import android.app.Activity;
import android.os.Bundle;

public class GamePanelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //remove Title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        //Set Fullscreen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new GamePanel(this));
    }
}
