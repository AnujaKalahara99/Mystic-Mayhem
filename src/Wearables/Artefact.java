package Wearables;

import java.util.Locale;
import java.io.Serializable;

    public class  Artefact extends Wearable implements Serializable{
        public Artefact(String name, int price, int attackBonus, int defenceBonus, int healthBonus, int speedBonus) {
            super(name, price, attackBonus, defenceBonus, healthBonus, speedBonus);
        }

        public static String[] listAll()
        {
            return new String[]{"Excalibur", "Amulet", "Crystal"};
        }

        public static int getBuyingPrice(String type){
            type = type.toLowerCase(Locale.ROOT);
            switch (type) {
                case "excalibur" -> {
                    return 150;
                }
                case "amulet" -> {
                    return 200;
                }
                case "crystal" -> {
                    return 210;
                }
                default -> throw new IllegalStateException("Unexpected type: " + type);
            }
        }

        public static Artefact create(String type){
            type = type.toLowerCase(Locale.ROOT);
            switch (type) {
                case "excalibur" -> {
                    return new Artefact("excalibur", getBuyingPrice("excalibur"),2,0,0,0);
                }
                case "amulet" -> {
                    return new Artefact("amulet", getBuyingPrice("amulet"),1,-1,1,1);
                }
                case "crystal" -> {
                    return new Artefact("crystal", getBuyingPrice("crystal"),2,1,-1,-1);
                }
                default -> throw new IllegalStateException("Unexpected type: " + type);
            }
        }

        public static String stats(String type){
            type = type.toLowerCase(Locale.ROOT);
            String s = switch (type) {
                case "excalibur" -> "Excalibur : " + "Price(" + getBuyingPrice("excalibur") + ") Atk(2) Def(0) Hth(0) Spd(0)";
                case "amulet" -> "Amulet : " + "Price(" + getBuyingPrice("amulet") + ") Atk(1) Def(-1) Hth(1) Spd(1)";
                case "crystal" -> "Crystal : " + "Price(" + getBuyingPrice("crystal") + ") Atk(2) Def(1) Hth(-1) Spd(-1)";
                default -> throw new IllegalStateException("Unexpected type: " + type);
            };
            return s;
        }
    }




