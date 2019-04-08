package com.neilerua72.polycolo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScreenGame extends Activity {

    private TextView text ;
    private FrameLayout layout;
    private LinearLayout linear;
    private ArrayList<String> listeJoueur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = this.getIntent();
        listeJoueur=new ArrayList<>();
        if(intent!=null){
            listeJoueur=intent.getStringArrayListExtra("listeJoueur");
        }

        setContentView(R.layout.activity_screen_game);
        text = (TextView)  findViewById(R.id.main_text);
        layout = (FrameLayout) findViewById(R.id.layout);
        linear = (LinearLayout) findViewById(R.id.linear);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }





}
