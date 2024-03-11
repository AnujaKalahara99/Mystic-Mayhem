package Warriors;
import java.io.Serializable;
import java.util.Locale;

public class Knight extends Warrior implements Serializable{
    public Knight(String name, int price, int attack, int defence, float health, int speed, String tribe) {
        super(name, price, attack, defence, health, speed, tribe);
    }

    public Warrior clone() throws CloneNotSupportedException {
        return new Knight(name, price, attack, defence, health, speed, tribe);
    }

    public static int getBuyingPrice(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "squire" -> {
                return 85;
            }
            case "cavalier" -> {
                return 110;
            }
            case "templar" -> {
                return 155;
            }
            case "zoro" -> {
                return 180;
            }
            case "swiftblade" -> {
                return 250;
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    public static Knight create(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "squire" -> {
                return new Knight("squire", getBuyingPrice("squire"),8,9,7,8,"marshlanders");
            }
            case "cavalier" -> {
                return new Knight("cavalier", getBuyingPrice("cavalier"),10,12,7,10,"highlanders");
            }
            case "templar" -> {
                return new Knight("templar", getBuyingPrice("templar"),14,16,12,12,"sunchildren");
            }
            case "zoro" -> {
                return new Knight("zoro", getBuyingPrice("zoro"),17,16,13,14,"highlanders");
            }
            case "swiftblade" -> {
                return new Knight("swiftblade", getBuyingPrice("swiftblade"),18,20,17,13,"marshlanders");
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }
}
