package com.neilerua72.polycolo.IA;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neilerua72.polycolo.R;
import com.neilerua72.polycolo.ScreenGame;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nom1 = new EditText(this);
        nom1.setHint("Nom 1");
        EditText nom2 = new EditText(this);
        nom2.setHint("Nom 2");
        EditText nom3 = new EditText(this);
        nom3.setHint("Nom 3");
        LinearLayout verti = (LinearLayout)findViewById(R.id.containEdit);
        verti.addView(nom1,0);
        verti.addView(nom2,1);
        verti.addView(nom3,2);
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout verti = (LinearLayout)findViewById(R.id.containEdit);

                EditText nom=new EditText(v.getContext());
                nom.setHint("Nom "+ verti.getChildCount()+1);
                verti.addView(nom);
            }
        });

        Button jouer=(Button)findViewById(R.id.play);
        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listeJoueur = new ArrayList<>();
                LinearLayout verti = (LinearLayout)findViewById(R.id.containEdit);
                for(int i=0;i<verti.getChildCount();i++){
                    EditText nom = (EditText)verti.getChildAt(i);
                    //String s = nom.getText().toString();
                    listeJoueur.add(nom.getText().toString());


                }

                Intent intent = new Intent(MainActivity.this, ScreenGame.class);
                intent.putStringArrayListExtra("listeJoueur",listeJoueur);
                startActivity(intent);


            }
        });


    }


}
