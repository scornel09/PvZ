package com.epf.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZombieDTO {

    private int id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("point_de_vie")
    private int pointDeVie;

    @JsonProperty("attaque_par_seconde")
    private BigDecimal attaqueParSeconde;

    @JsonProperty("degat_attaque")
    private int degatAttaque;

    @JsonProperty("vitesse_de_deplacement")
    private float vitesseDeDeplacement;

    @JsonProperty("chemin_image")
    private String cheminImage;

    @JsonProperty("id_map")
    private int idMap;

    // Constructeurs, getters, setters
    public ZombieDTO() {
    }

    public ZombieDTO(int id, String nom, int pointDeVie, BigDecimal attaqueParSeconde, int degatAttaque, float vitesseDeDeplacement, String cheminImage, int idMap) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = cheminImage;
        this.idMap = idMap;
    }

    @JsonProperty("id_zombie")
    public int getId() {
        return id;
    }

    @JsonProperty("id_zombie")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("point_de_vie")
    public int getPointDeVie() {
        return pointDeVie;
    }

    @JsonProperty("point_de_vie")
    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    @JsonProperty("attaque_par_seconde")
    public BigDecimal getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    @JsonProperty("attaque_par_seconde")
    public void setAttaqueParSeconde(BigDecimal attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }

    @JsonProperty("degat_attaque")
    public int getDegatAttaque() {
        return degatAttaque;
    }

    @JsonProperty("degat_attaque")
    public void setDegatAttaque(int degatAttaque) {
        this.degatAttaque = degatAttaque;
    }

    @JsonProperty("vitesse_de_deplacement")
    public float getVitesseDeDeplacement() {
        return vitesseDeDeplacement;
    }

    @JsonProperty("vitesse_de_deplacement")
    public void setVitesseDeDeplacement(float vitesseDeDeplacement) {
        this.vitesseDeDeplacement = vitesseDeDeplacement;
    }

    @JsonProperty("chemin_image")
    public String getCheminImage() {
        return cheminImage;
    }

    @JsonProperty("chemin_image")
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    @JsonProperty("id_map")
    public int getIdMap() {
        return idMap;
    }

    @JsonProperty("id_map")
    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }
}
