package Enums;

public enum ArcherEnum {
    SHOOTER("shooter", 80,11,4,6,9,"highlanders"),
    RANGER("ranger", 115,14,5,8,10,"highlanders"),
    SUNFIRE("sunfire", 160,15,5,7,14,"sunchildren"),
    ZING("zing", 200,16,9,11,14,"sunchildren"),
    SAGGITARIUS("saggitarius", 230,18,7,12,17,"mystics");

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public float getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public String getTribe() {
        return tribe;
    }

    private final String type;
    private final int price;
    private final int attack;
    private final int defence;
    private final float health;
    private final int speed;
    private final String tribe;

    ArcherEnum(String type, int price, int attack, int defence, float health, int speed, String tribe) {
        this.type = type;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
        this.tribe = tribe;
    }
}
