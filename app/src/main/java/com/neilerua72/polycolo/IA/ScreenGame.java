package com.neilerua72.polycolo.IA;

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

import com.neilerua72.polycolo.NF.ActionJeu;
import com.neilerua72.polycolo.NF.Jeu;
import com.neilerua72.polycolo.NF.Regle;
import com.neilerua72.polycolo.NF.TypeJeu;
import com.neilerua72.polycolo.NF.Vote;
import com.neilerua72.polycolo.NF.XML.LectureJeu;
import com.neilerua72.polycolo.NF.XML.LecturePourcmb;
import com.neilerua72.polycolo.NF.XML.LectureVote;
import com.neilerua72.polycolo.R;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScreenGame extends Activity {
    private ScreenGame sg;
    private TextView text ;
    private TextView textType;
    private FrameLayout layout;
    private LinearLayout linear;
    private ArrayList<String> listeJoueur;
    private ArrayList<Regle> listeRegle;
    private ArrayList<Integer> listeDejaJoue;
    int nbClick=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Initialisation de l'interface
         listeRegle = new ArrayList<>();
        listeDejaJoue = new ArrayList<>();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = this.getIntent();
        listeJoueur=new ArrayList<>();

        //Récupération des données
        if(intent!=null){
            listeJoueur=intent.getStringArrayListExtra("listeJoueur");
        }
        //Initialisation des règles
        LectureJeu lectureJeu = new LectureJeu(this);
        LectureVote lectureVote = new LectureVote(this);
        LecturePourcmb lecturePourcmb = new LecturePourcmb(this);

        //Ajout de toutes les règles à une seule et unique liste
        listeRegle=lectureJeu.getListeJeu();
        listeRegle.addAll(lectureVote.getListeVote());
        listeRegle.addAll(lecturePourcmb.getListePourcmb());
        setContentView(R.layout.activity_screen_game);

        text = (TextView)  findViewById(R.id.main_text);
        textType = (TextView) findViewById(R.id.typeJeu);
        text.setText("Bonne partie !");
        layout = (FrameLayout) findViewById(R.id.layout);
        linear = (LinearLayout) findViewById(R.id.linear);
        this.sg=this;
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seuil = 10;
                if(nbClick<seuil){
                ActionJeu actionJeu=new ActionJeu(sg);
                actionJeu.click();}
                else if(nbClick==seuil){
                    sg.getTextType().setVisibility(View.INVISIBLE);
                    sg.getText().setText("Fin de la partie");
                    sg.getLayout().setBackgroundColor(Color.rgb(0,0,0));
                }
                else{
                    finish();
                }
                nbClick++;
            }
        });

    }

    public static int nombreAlea(int min,int max){
        return min + (int)(Math.random() * ((max- min) + 1));
    }

    public TextView getText() {
        return text;
    }

    public TextView getTextType() {
        return textType;
    }

    public FrameLayout getLayout() {
        return layout;
    }

    public LinearLayout getLinear() {
        return linear;
    }

    public ArrayList<String> getListeJoueur() {
        return listeJoueur;
    }

    public ArrayList<Regle> getListeRegle() {
        return listeRegle;
    }

    public ArrayList<Integer> getListeDejaJoue() {
        return listeDejaJoue;
    }
    public int getNbClick(){
        return nbClick;
    }
}
