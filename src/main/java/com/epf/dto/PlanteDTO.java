package com.epf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanteDTO {

    @JsonProperty("id_plante")
    private int id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("point_de_vie")
    private int pointDeVie;

    @JsonProperty("temps_recharge")
    private float tempsRecharge;

    @JsonProperty("attaque_par_seconde")
    private float attaqueParSeconde;

    @JsonProperty("degat_attaque")
    private int degatAttaque;

    @JsonProperty("cout")
    private int cout;

    @JsonProperty("soleil_par_seconde")
    private int soleilParSeconde;

    @JsonProperty("effet")
    private String effet;

    @JsonProperty("chemin_image")
    private String cheminImage;

    public PlanteDTO() {
    }

    public PlanteDTO(int id, String nom, int pointDeVie, float tempsRecharge, float attaqueParSeconde,
            int degatAttaque, int cout, int soleilParSeconde, String effet, String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.tempsRecharge = tempsRecharge;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.cout = cout;
        this.soleilParSeconde = soleilParSeconde;
        this.effet = effet;
        this.cheminImage = cheminImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public float getTempsRecharge() {
        return tempsRecharge;
    }

    public void setTempsRecharge(float tempsRecharge) {
        this.tempsRecharge = tempsRecharge;
    }

    public float getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public void setAttaqueParSeconde(float attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }

    public int getDegatAttaque() {
        return degatAttaque;
    }

    public void setDegatAttaque(int degatAttaque) {
        this.degatAttaque = degatAttaque;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getSoleilParSeconde() {
        return soleilParSeconde;
    }

    public void setSoleilParSeconde(int soleilParSeconde) {
        this.soleilParSeconde = soleilParSeconde;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}
