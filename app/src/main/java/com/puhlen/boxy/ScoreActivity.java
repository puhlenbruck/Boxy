package com.puhlen.boxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_layout);

        Intent intent = getIntent();
        if(intent!=null){
            ((TextView)findViewById(R.id.scoreView)).setText(Integer.toString(intent.getIntExtra
                    ("score",0)));
        }
    }

    public void okClick(View v){
        finish();
    }
}
