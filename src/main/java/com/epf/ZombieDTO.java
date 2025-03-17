package com.epf;

public class ZombieDTO {

    private int id;
    private String name;
    private int health;
    private float atkSpeed;
    private float mvtSpeed;
    private int mapId;

    // Constructeurs, getters, setters
    public ZombieDTO() {
    }

    public ZombieDTO(int id, String name, int health, float atkSpeed, float mvtSpeed, int mapId) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.atkSpeed = atkSpeed;
        this.mvtSpeed = mvtSpeed;
        this.mapId = mapId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMapId() {
        return mapId;
    }

    public float getAtkSpeed() {
        return atkSpeed;
    }

    public float getMvtSpeed() {
        return mvtSpeed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public void setAtkSpeed(Float atkSpeed) {
        this.atkSpeed = atkSpeed;
    }

    public void setMvtSpeed(Float mvtSpeed) {
        this.mvtSpeed = mvtSpeed;
    }

}
