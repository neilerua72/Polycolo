package com.neilerua72.polycolo.NF;

public enum TypeJeu {
    CS("Cul Sec"),
    JEU("Mini jeu"),
    VOTE("Vote");
    private String nom="";

    TypeJeu(String nom) {
        this.nom=nom;
    }
    public TypeJeu getTypeJeu(String s){
        if(s.equals("CS"))
            return TypeJeu.CS;
        else if(s.equals("JEU"))
            return TypeJeu.JEU;
        else if(s.equals("VOTE"))
            return TypeJeu.VOTE;
        else
            return null;
    }

    public String toString(){
        return nom;
    }

}
