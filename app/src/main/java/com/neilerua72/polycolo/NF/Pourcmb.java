package com.neilerua72.polycolo.NF;

public class Pourcmb extends Regle {
    private String texte;
    public Pourcmb(String texte){
        super(TypeJeu.POURCMB);
        this.texte=texte;
    }

    public String getTexte() {
        return texte;
    }
}
