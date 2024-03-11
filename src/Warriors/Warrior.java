package Warriors;

import Wearables.Armour;
import Wearables.Artefact;
import java.io.Serializable;

public abstract class Warrior implements Serializable, Cloneable {
    protected Armour armour;
    protected Artefact artefact;
    protected float health;
    protected String name;
    protected int price;
    protected int attack;
    protected int defence;
    protected int speed;
    protected String tribe;


    public Warrior(String name, int price, int attack, int defence, float health, int speed, String tribe) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.price = price;
        this.tribe = tribe;
    }

    public float getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    public int getdefence() {
        return defence;
    }
    public int getAttack() {
        return attack;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getSpeed() {
        return speed;
    }

    public abstract Warrior clone() throws CloneNotSupportedException;

    public void printStats() {
        System.out.print(name);
        System.out.print("(" + tribe + ") -> ");
        System.out.print("Price: " + price + " ");
        System.out.print("Atk: " + attack + " ");
        System.out.print("Def: " + defence + " ");
        System.out.print("hp: " + health + " ");
        System.out.print("speed: " + speed + " ");
    }
}


