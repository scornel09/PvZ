package com.epf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "map")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_map")
    private int id;

    @Column(name = "ligne")
    private int ligne;

    @Column(name = "colonne")
    private int colonne;

    @Column(name = "chemin_image")
    private String cheminImage;

    // Constructeurs, getters et setters
    public Map() {
        this.cheminImage = "/api/images/map/gazon.png";
    }

    public Map(int ligne, int colonne, String cheminImage) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = determineImagePath(cheminImage);
    }

    private String determineImagePath(String cheminImage) {
        if (cheminImage != null) {
            return cheminImage;
        }
        return "/api/images/map/gazon.png";
    }

    // Getters and setters...
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
