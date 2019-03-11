package com.neilerua72.polycolo.NF;

public enum TypeJeu {
    CS("Cul Sec"),
    MJEU("Mini jeux"),
    VOTE("Vote");
    private String nom="";

    TypeJeu(String nom) {
        this.nom=nom;
    }
    public TypeJeu getTypeJeu(String s){
        if(s.equals("CS"))
            return TypeJeu.CS;
        else if(s.equals("MJEU"))
            return TypeJeu.MJEU;
        else if(s.equals("VOTE"))
            return TypeJeu.VOTE;
        else
            return null;
    }

}
