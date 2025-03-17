package com.epf;

public class MapDTO {
    private int id;
    private int ligne;
    private int colonne;

    // Constructeurs, getters, setters
    public MapDTO() {}

    public MapDTO(int id, int ligne, int colonne) {
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getId() { return id; }
    public int getLigne() { return ligne; }
    public int getColonne() { return colonne; }


    public void setId(int id) { this.id = id; }
    public void setLigne(int ligne) { this.ligne = ligne; }
    public void setColonne(int colonne) { this.colonne = colonne; }
}
