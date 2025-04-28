package com.epf.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zombie")
public class Zombie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zombie")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "point_de_vie")
    private int pointDeVie;

    @Column(name = "attaque_par_seconde", precision = 5, scale = 2)
    private BigDecimal attaqueParSeconde;

    @Column(name = "degat_attaque")
    private int degatAttaque;

    @Column(name = "vitesse_de_deplacement")
    private float vitesseDeDeplacement;

    @Column(name = "chemin_image")
    private String cheminImage;

    @Column(name = "id_map")
    private Integer idMap;

    // Constructeurs, getters et setters
    public Zombie() {
        this.cheminImage = "/images/zombie/default.png";
    }

    public Zombie(int id, String nom, int pointDeVie, BigDecimal attaqueParSeconde, int degatAttaque, float vitesseDeDeplacement, String cheminImage, Integer idMap) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = determineImagePath(nom, cheminImage);
        this.idMap = idMap;
    }

    private String determineImagePath(String nom, String cheminImage) {
        if (cheminImage != null) {
            return cheminImage;
        }
        switch (nom.toLowerCase()) {
            case "zombie":
                return "/api/images/zombie/zombie.png";
            case "football":
                return "/api/images/zombie/football.png";
            case "conehead":
                return "/api/images/zombie/conehead.png";
            case "buckethead":
                return "/api/images/zombie/buckethead.png";
            case "runner":
                return "/api/images/zombie/runner.png";
            default:
                return "/api/images/zombie/default.png";
        }
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

    public BigDecimal getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public void setAttaqueParSeconde(BigDecimal attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }

    public int getDegatAttaque() {
        return degatAttaque;
    }

    public void setDegatAttaque(int degatAttaque) {
        this.degatAttaque = degatAttaque;
    }

    public float getVitesseDeDeplacement() {
        return vitesseDeDeplacement;
    }

    public void setVitesseDeDeplacement(float vitesseDeDeplacement) {
        this.vitesseDeDeplacement = vitesseDeDeplacement;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public Integer getIdMap() {
        return idMap;
    }

    public void setIdMap(Integer idMap) {
        this.idMap = idMap;
    }
}
