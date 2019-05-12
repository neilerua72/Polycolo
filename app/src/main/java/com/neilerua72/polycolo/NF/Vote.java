package com.neilerua72.polycolo.NF;

public class Vote extends Regle {
    String texte1;
    String texte2;
    public Vote(TypeJeu typeJeu, String texte1, String texte2){
        super(typeJeu);
        this.texte1=texte1;
        this.texte2=texte2;
    }

    public String getTexte1() {
        return texte1;
    }

    public String getTexte2() {
        return texte2;
    }
}
