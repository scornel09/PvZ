package com.epf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MapDTO {

    private int id;
    private int ligne;
    private int colonne;

    @JsonProperty("chemin_image")
    private String cheminImage;

    // Constructeurs, getters, setters
    public MapDTO() {
    }

    public MapDTO(int id, int ligne, int colonne, String cheminImage) {
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}
