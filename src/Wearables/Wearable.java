package Wearables;
import java.io.Serializable;

public class Wearable implements Serializable{
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


}
