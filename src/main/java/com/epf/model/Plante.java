package com.epf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plantes")
public class Plante {

    public enum Effet {
        NORMAL,
        SLOW_LOW,
        SLOW_MEDIUM,
        SLOW_STOP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plante")
    private int id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "point_de_vie")
    private int pointDeVie;

    @Column(name = "temps_recharge")
    private float tempsRecharge;

    @Column(name = "degat_attaque")
    private int degatAttaque;

    @Column(name = "cout")
    private int cout;

    @Column(name = "soleil_par_seconde")
    private float soleilParSeconde;

    @Enumerated(EnumType.STRING)
    @Column(name = "effet")
    private Effet effet;

    @Column(name = "chemin_image")
    private String cheminImage;

    @Column(name = "attaque_par_seconde")
    private float attaqueParSeconde;

    // Constructeurs, getters et setters
    public Plante() {
        this.cheminImage = "/images/plante/default.png";
    }

    public Plante(int id, String nom, int pointDeVie, float tempsRecharge, int degatAttaque, int cout, float soleilParSeconde, Effet effet, String cheminImage, float attaqueParSeconde) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.tempsRecharge = tempsRecharge;
        this.degatAttaque = degatAttaque;
        this.cout = cout;
        this.soleilParSeconde = soleilParSeconde;
        this.effet = effet;
        this.cheminImage = determineImagePath(nom, cheminImage);
        this.attaqueParSeconde = attaqueParSeconde;
    }

    private String determineImagePath(String nom, String cheminImage) {
        if (cheminImage != null) {
            return cheminImage;
        }
        switch (nom.toLowerCase()) {
            case "tournesol":
                return "/api/images/plante/tournesol.png";
            case "poistireur":
                return "/api/images/plante/poistireur.png";
            case "noix":
                return "/api/images/plante/noix.png";
            case "glacepois":
                return "/api/images/plante/glacepois.png";
            case "doublepois":
                return "/api/images/plante/doublepois.png";
            default:
                return "/api/images/plante/default.png";
        }
    }

    public Plante(String nom, int pointDeVie, float tempsRecharge, int degatAttaque, int cout, float soleilParSeconde, Effet effet, String cheminImage) {
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.tempsRecharge = tempsRecharge;
        this.degatAttaque = degatAttaque;
        this.cout = cout;
        this.soleilParSeconde = soleilParSeconde;
        this.effet = effet;
        this.cheminImage = cheminImage != null ? cheminImage : "/images/plante/tournesol.png";
    }

    // Getters and setters...
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

    public float getSoleilParSeconde() {
        return soleilParSeconde;
    }

    public void setSoleilParSeconde(float soleilParSeconde) {
        this.soleilParSeconde = soleilParSeconde;
    }

    public Effet getEffet() {
        return effet;
    }

    public void setEffet(Effet effet) {
        this.effet = effet;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public float getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public void setAttaqueParSeconde(float attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }
}
