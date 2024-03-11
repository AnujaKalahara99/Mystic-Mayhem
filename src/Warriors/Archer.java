package Warriors;

import java.util.Locale;
import java.io.Serializable;
public class Archer extends Warrior implements Serializable{
    public Archer(String name, int price, int attack, int defence, float health, int speed, String tribe) {
        super(name, price, attack, defence, health, speed, tribe);
    }

    @Override
    public Warrior clone() throws CloneNotSupportedException {
        return new Archer(name, price, attack, defence, health, speed, tribe);
    }

    public static int getBuyingPrice(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "shooter" -> {
                return 80;
            }
            case "ranger" -> {
                return 115;
            }
            case "sunfire" -> {
                return 160;
            }
            case "zing" -> {
                return 200;
            }
            case "saggitarius" -> {
                return 230;
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    public static Archer create(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "shooter" -> {
                return new Archer("shooter", getBuyingPrice("shooter"),11,4,6,9,"highlanders");
            }
            case "ranger" -> {
                return new Archer("ranger", getBuyingPrice("ranger"),14,5,8,10,"highlanders");
            }
            case "sunfire" -> {
                return new Archer("sunfire", getBuyingPrice("sunfire"),15,5,7,14,"sunchildren");
            }
            case "zing" -> {
                return new Archer("zing", getBuyingPrice("zing"),16,9,11,14,"sunchildren");
            }
            case "saggitarius" -> {
                return new Archer("saggitarius", getBuyingPrice("saggitarius"),18,7,12,17,"mystics");
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }
}

//Archer.create("shooter")