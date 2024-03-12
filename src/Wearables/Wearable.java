package Wearables;
import java.io.Serializable;

public abstract class Wearable implements Serializable{
    private String name;
    private int price;
    private int attackBonus;
    private int defenseBonus;
    private int healthBonus;
    private int speedBonus;

    public Wearable(String name, int price, int attackBonus, int defenseBonus, int healthBonus, int speedBonus) {
        this.name = name;
        this.price = price;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.healthBonus = healthBonus;
        this.speedBonus = speedBonus;
    }

    public int getPrice() {
        return price;
    }
    public int getPrice(int unitPrice) {
        return (int)(price * 0.2 + unitPrice);
    }

    public int getAttackBonus(int attack) {
        return attack + attackBonus;
    }

    public int getDefenseBonus(int defence) {
        return defenseBonus + defence;
    }

    public float getHealthBonus(float health) {
        return healthBonus + health;
    }

    public int getSpeedBonus(int speed) {
        return speedBonus + speed;
    }

    public String getName() {
        return name;
    }
}
