package com.epf;

public class Plante {
    private int id;
    private String name;
    private int damage;
    private int health;
    private float atkSpeed;
    private int cost;
    private float sunPerSecond;
    private SlowType slowType; // 🔹 Ajout du type de ralentissement

    // Constructeurs, getters et setters

    public Plante() {}

    public Plante(int id, String name, int health, int damage, float atkSpeed, int cost, float sunPerSecond, SlowType slowType) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.atkSpeed = atkSpeed;
        this.cost = cost;
        this.sunPerSecond = sunPerSecond;
        this.slowType = slowType;
    }

    // Getters and setters...

    public String getName() {
        return name;
    }

    public float getSunPerSecond() {
        return sunPerSecond;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public float getAtkSpeed() {
        return atkSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public SlowType getSlowType() {
        return slowType;
    }

    public void setSlowType(SlowType slowType) {
        this.slowType = slowType;
    }
}
