package com.neilerua72.polycolo.NF;

public enum TypeJeu {
    CS("Cul Sec"),
    JEU("Mini jeu"),
    VOTE("Vote"),
    POURCMB("Pour combien ?");
    private String nom="";

    TypeJeu(String nom) {
        this.nom=nom;
    }


    public String toString(){
        return nom;
    }

}
