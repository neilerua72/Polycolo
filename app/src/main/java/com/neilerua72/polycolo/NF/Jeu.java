package com.neilerua72.polycolo.NF;

public class Jeu extends Regle {
    private String texte1;

    public Jeu(TypeJeu typeJeu,String texte1){
        super(typeJeu);
        this.texte1=texte1;
    }

    public String getTexte1() {
        return texte1;
    }
}
