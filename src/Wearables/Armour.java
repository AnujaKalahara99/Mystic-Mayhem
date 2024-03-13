package Wearables;

import Enums.ArmourEnum;

import java.util.Locale;
import java.io.Serializable;

public class Armour extends  Wearable implements Serializable{
    public Armour(String name, int price, int attackBonus, int defenceBonus, int healthBonus, int speedBonus) {
        super(name, price, attackBonus, defenceBonus, healthBonus, speedBonus);
    }

    public static String[] listAll()
    {
        return new String[]{"Chainmail","Regalica", "Fleece"};
    }

    public static int getBuyingPrice(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "chainmail" -> {
                return 70;
            }
            case "regalica" -> {
                return 105;
            }
            case "fleece" -> {
                return 150;
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    public static Armour create(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "chainmail" -> {
                return new Armour("chainmail", getBuyingPrice("chainmail"),0,1,0,-1);
            }
            case "regalica" -> {
                return new Armour("regalica", getBuyingPrice("regalica"),0,1,0,0);
            }
            case "fleece" -> {
                return new Armour("fleece", getBuyingPrice("fleece"),0,2,1,-1);
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    public static String stats(String type){
        type = type.toLowerCase(Locale.ROOT);
        String s = switch (type) {
            case "chainmail" -> "Chainmail : " + "Price(" + getBuyingPrice("chainmail") + ") Atk(0) Def(1) Hth(0) Spd(-1)";
            case "regalica" -> "Regalica : " + "Price(" + getBuyingPrice("regalica") + ") Atk(0) Def(1) Hth(0) Spd(0)";
            case "fleece" -> "Fleece : " + "Price(" + getBuyingPrice("fleece") + ") Atk(0) Def(2) Hth(1) Spd(-1)";
            default -> throw new IllegalStateException("Unexpected type: " + type);
        };
        return s;
    }
}
