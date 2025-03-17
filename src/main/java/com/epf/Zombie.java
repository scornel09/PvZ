package com.epf;

public class Zombie {

    private int id;
    private String name;
    private int health;
    private float atkSpeed;
    private float mvtSpeed;
    private int mapId; // Référence à la map associée

    // Constructeurs, getters et setters
    public Zombie() {
    }

    public Zombie(int id, String name, int health, float atkSpeed, float mvtSpeed, int mapId) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.atkSpeed = atkSpeed;
        this.mvtSpeed = mvtSpeed;
        this.mapId = mapId;
    }

    // Getters and setters...
    public void setId(int id) {
        this.id = id;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAtkSpeed(Float atkSpeed) {
        this.atkSpeed = atkSpeed;
    }

    public void setMvtSpeed(Float mvtSpeed) {
        this.mvtSpeed = mvtSpeed;
    }

    public int getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public float getAtkSpeed() {
        return atkSpeed;
    }

    public float getMvtSpeed() {
        return mvtSpeed;
    }

    public String getName() {
        return name;
    }

    public int getMapId() {
        return mapId;
    }
}
