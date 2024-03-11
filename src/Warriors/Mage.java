package Warriors;
import java.io.Serializable;
import java.util.Locale;

public class Mage extends Warrior implements Serializable{
    public Mage(String name, int price, int attack, int defence, float health, int speed, String tribe) {
        super(name, price, attack, defence, health, speed, tribe);
    }

    public Warrior clone() throws CloneNotSupportedException {
        return new Mage(name, price, attack, defence, health, speed, tribe);
    }

    public static int getBuyingPrice(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "warlock" -> {
                return 100;
            }
            case "illusionist" -> {
                return 120;
            }
            case "enchanter" -> {
                return 160;
            }
            case "conjurer" -> {
                return 195;
            }
            case "eldritch" -> {
                return 270;
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }

    }

    public static Mage create(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "warlock" -> {
                return new Mage("warlock", getBuyingPrice("warlock"),12,7,10,12,"marshlanders");
            }
            case "illusionist" -> {
                return new Mage("illusionist", getBuyingPrice("illusionist"),13,8,12,14,"mystic");
            }
            case "enchanter" -> {
                return new Mage("enchanter", getBuyingPrice("enchanter"),16,10,13,16,"highlanders");
            }
            case "conjurer" -> {
                return new Mage("conjurer", getBuyingPrice("conjurer"),18,15,14,12,"highlanders");
            }
            case "eldritch" -> {
                return new Mage("eldritch", getBuyingPrice("eldritch"),19,17,18,14,"mystic");
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }

    }
}

