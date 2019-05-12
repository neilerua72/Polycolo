package com.neilerua72.polycolo.NF;

import android.graphics.Color;
import android.view.View;

import com.neilerua72.polycolo.IA.ScreenGame;
import com.neilerua72.polycolo.R;

import static com.neilerua72.polycolo.IA.ScreenGame.nombreAlea;

public class ActionJeu {

    ScreenGame sg;
    int nbClick = 0;

    public ActionJeu(ScreenGame sg){
        this.sg=sg;
    }
    public void click(){

        Integer result = 0;
        //Boucle qui donne une règle qui n'a pas encore été joué
        do{
            int nombreAleatoire = (int)(Math.random() * (sg.getListeRegle().size()-1 + 1));
            result=nombreAleatoire;
        }while (sg.getListeDejaJoue().contains(result));

        //Ajout de la nouvelle règle dans la liste des règles déjà joué
        sg.getListeDejaJoue().add(result);

        //Récupération de l'objet Regle
        Regle r = sg.getListeRegle().get(result);
        TypeJeu typeJeu = r.getTypeJeu();

        //Définition du titre de l'ecran en fonction du type de jeu
        sg.getTextType().setText(typeJeu.toString());
        sg.getTextType().setVisibility(View.VISIBLE);

        //Action en fonction du type de jeu
        switch (typeJeu){
            case JEU:
                sg.getLayout().setBackgroundColor(Color.rgb(127,37,46));
                Jeu jeu = (Jeu)r;
                sg.getText().setText(jeu.getTexte1()+".\n"+sg.getString(R.string.repete)+" "+nombreAlea(2,5)+" "+sg.getString(R.string.gorgees)+" "+sg.getListeJoueur().get(nombreAlea(0,sg.getListeJoueur().size()-1))+" "+sg.getString(R.string.commence));
                break;
            case VOTE:
                sg.getLayout().setBackgroundColor(Color.rgb(219,108,255));
                Vote vote = (Vote)r;
                sg.getText().setText("Plutôt "+vote.getTexte1()+" ou "+ vote.getTexte2()+" ? \n Votez tous en même temps, les minoritaires boivent "+nombreAlea(2,5)+" gorgées !");
                break;
            case CS:
            break;
            case POURCMB:
                sg.getLayout().setBackgroundColor(Color.rgb(30,127,203));
                int j1 = nombreAlea(0,sg.getListeJoueur().size()-1);
                int j2=0;
                Pourcmb pourcmb = (Pourcmb)r;
                do{
                    j2=nombreAlea(0,sg.getListeJoueur().size()-1);
                }while(j2==j1);
                sg.getText().setText("Pour combien "+pourcmb.getTexte()+" ? 50 max. "+sg.getListeJoueur().get(j1)+" défie "+sg.getListeJoueur().get(j2)+" ! Cul sec en cas de refus.");
            default:
                break;
        }
    }
}
