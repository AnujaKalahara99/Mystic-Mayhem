package Warriors;
import java.io.Serializable;
import java.util.Locale;

public class Healer extends Warrior implements Serializable{

    public Healer(String name, int price, int attack, int defence, float health, int speed, String tribe) {
        super(name, price, attack, defence, health, speed, tribe);
    }

    public Warrior clone() throws CloneNotSupportedException {
        return new Healer(name, price, attack, defence, health, speed, tribe);
    }

    public static int getBuyingPrice(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "soother" -> {
                return 95;
            }
            case "medic" -> {
                return 125;
            }
            case "alchemist" -> {
                return 150;
            }
            case "saint" -> {
                return 200;
            }
            case "lightbringer" -> {
                return 260;
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }

    public static Healer create(String type){
        type = type.toLowerCase(Locale.ROOT);
        switch (type) {
            case "soother" -> {
                return new Healer("soother", getBuyingPrice("soother"),10,8,9,6,"sunchildren");
            }
            case "medic" -> {
                return new Healer("medic", getBuyingPrice("medic"),12,9,10,7,"highlanders");
            }
            case "alchemist" -> {
                return new Healer("alchemist", getBuyingPrice("alchemist"),13,13,13,13,"marshlanders");
            }
            case "saint" -> {
                return new Healer("saint", getBuyingPrice("saint"),16,14,17,9,"mystics");
            }
            case "lightbringer" -> {
                return new Healer("lightbringer", getBuyingPrice("lightbringer"),17,15,19,12,"sunchildren");
            }
            default -> throw new IllegalStateException("Unexpected type: " + type);
        }
    }
}

//Archer.create("shooter")
