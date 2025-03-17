package com.epf;

public class Map {

    private int id;
    private int  ligne;
    private int colonne;

    // Constructeurs, getters et setters
    public Map() {
    }

    public Map(int id, int ligne, int colonne) {
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Getters and setters...
    public int getId() {
        return id;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getColonne() {
        return colonne;
    }
}
