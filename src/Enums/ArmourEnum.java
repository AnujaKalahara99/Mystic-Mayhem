package Enums;

public enum ArmourEnum {
    CHAINMAIL("Chainmail", 70,0,1,0,-1),
    REGALIA("Regalia", 105,0,1,0,0),
    FLEECE("Fleece", 150,0,2,1,-1);

    public final String name;
    public final int price;
    public final int attack;
    public final int defence;
    public final float health;
    public final int speed;

    ArmourEnum(String name, int price, int attack, int defence, float health, int speed) {
        this.name = name;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
    }
}
