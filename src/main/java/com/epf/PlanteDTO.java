package com.epf;

public class PlanteDTO {
    private int id;
    private String name;
    private int health;
    private int damage;
    private float atkSpeed;
    private int cost;
    private float sunPerSecond;
    private SlowType slowType; // Ajout de l'enum

    // Constructeurs, getters, setters
    public PlanteDTO() {}

    public PlanteDTO(int id, String name, int health, int damage, float atkSpeed, int cost, float sunPerSecond, SlowType slowType) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.atkSpeed = atkSpeed;
        this.cost = cost;
        this.sunPerSecond = sunPerSecond;
        this.slowType = slowType;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public float getAtkSpeed() { return atkSpeed; }
    public int getCost() { return cost; }
    public float getSunPerSecond() { return sunPerSecond; }
    public SlowType getSlowType() { return slowType; } // Getter pour SlowType

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setAtkSpeed(float atkSpeed) { this.atkSpeed = atkSpeed; }
    public void setCost(int cost) { this.cost = cost; }
    public void setSunPerSecond(float sunPerSecond) { this.sunPerSecond = sunPerSecond; }
    public void setSlowType(SlowType slowType) { this.slowType = slowType; } // Setter pour SlowType
}
