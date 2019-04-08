package com.neilerua72.polycolo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neilerua72.polycolo.NF.Jeu;
import com.neilerua72.polycolo.NF.Regle;
import com.neilerua72.polycolo.NF.TypeJeu;
import com.neilerua72.polycolo.NF.XML.LectureJeu;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScreenGame extends Activity {

    private TextView text ;
    private TextView textType;
    private FrameLayout layout;
    private LinearLayout linear;
    private ArrayList<String> listeJoueur;
    private ArrayList<Regle> listeRegle;
    private ArrayList<Integer> listeDejaJoue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         listeRegle = new ArrayList<>();
        listeDejaJoue = new ArrayList<>();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = this.getIntent();
        listeJoueur=new ArrayList<>();
        if(intent!=null){
            listeJoueur=intent.getStringArrayListExtra("listeJoueur");
        }
        LectureJeu lectureJeu = new LectureJeu(this);
        listeRegle=lectureJeu.getListeJeu();
        setContentView(R.layout.activity_screen_game);

        text = (TextView)  findViewById(R.id.main_text);
        textType = (TextView) findViewById(R.id.typeJeu);
        text.setText("Bonne partie !");
        layout = (FrameLayout) findViewById(R.id.layout);
        linear = (LinearLayout) findViewById(R.id.linear);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer result = 0;
                do{
                    int nombreAleatoire = (int)(Math.random() * (listeRegle.size()-1 + 1));
                    result=nombreAleatoire;
                }while (listeDejaJoue.contains(result));
                listeDejaJoue.add(result);
                Regle r = listeRegle.get(result);
                TypeJeu typeJeu = r.getTypeJeu();
                textType.setText(typeJeu.toString());
                textType.setVisibility(View.VISIBLE);
                switch (typeJeu){
                    case JEU:
                        layout.setBackgroundColor(Color.GREEN);
                        Jeu jeu = (Jeu)r;


                        text.setText(jeu.getTexte1()+".\n"+getString(R.string.repete)+" "+nombreAlea(2,5)+" "+getString(R.string.gorgees)+" "+listeJoueur.get(nombreAlea(0,listeJoueur.size()-1))+" "+getString(R.string.commence));
                        break;
                        default:
                            break;
                }
            }
        });

    }

    public static int nombreAlea(int min,int max){
        return min + (int)(Math.random() * ((max- min) + 1));
    }


}
