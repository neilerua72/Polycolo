package com.neilerua72.polycolo.NF;

public abstract class Regle {
    private TypeJeu typeJeu;
    public Regle(TypeJeu typeJeu){
        this.typeJeu=typeJeu;
    }

    public TypeJeu getTypeJeu() {
        return typeJeu;
    }
}
