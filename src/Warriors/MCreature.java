package Warriors;
import java.io.Serializable;
import java.util.Locale;

public class MCreature extends Warrior implements Serializable{
    public MCreature(String name, int price, int attack, int defence, float health, int speed, String tribe) {
        super(name, price, attack, defence, health, speed, tribe);
    }

    public Warrior clone() throws CloneNotSupportedException {
        return new MCreature(name, price, attack, defence, health, speed, tribe);
    }

    public static int getBuyingPrice(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "dragon" -> {
                return 120;
            }
            case "basilisk" -> {
                return 165;
            }
            case "hydra" -> {
                return 205;
            }
            case "phoenix" -> {
                return 275;
            }
            case "pegasus" -> {
                return 340;
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    public static MCreature create(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "dragon" -> {
                return new MCreature("dragon", getBuyingPrice("dragon"),12,14,15,8,"sunchildren");
            }
            case "basilisk" -> {
                return new MCreature("basilisk", getBuyingPrice("basilisk"),15,11,10,12,"marshlanders");
            }
            case "hydra" -> {
                return new MCreature("hydra", getBuyingPrice("hydra"),12,16,15,11,"marshlanders");
            }
            case "phoenix" -> {
                return new MCreature("phoenix", getBuyingPrice("phoenix"),17,13,17,19,"sunchildren");
            }
            case "pegasus" -> {
                return new MCreature("pegasus", getBuyingPrice("pegasus"),14,18,20,20,"mystics");
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }
}
