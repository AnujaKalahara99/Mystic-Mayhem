package Warriors;

import Wearables.Armour;
import Wearables.Artefact;

import java.io.Serializable;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Warrior implements Serializable, Cloneable {
    protected Armour armour;
    protected Artefact artefact;
    protected float health;
    protected String name;
    protected int price;
    protected int attack;
    protected int defence;
    protected int speed;
    protected String tribe;


    public Warrior(String name, int price, int attack, int defence, float health, int speed, String tribe) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.price = price;
        this.tribe = tribe;
    }

    public Armour getArmour() {
        return armour;
    }
    public Artefact getArtefact() {
        return artefact;
    }
    public void setArmour(String type) {
        armour = Armour.create(type);
    }
    public void setArtefact(String type) {
        artefact = Artefact.create(type);
    }

    public void upgrade(Scanner scanner, int wallet, Consumer<Integer> setWallet)
    {
        System.out.println("\nUpgrade " + name);
        System.out.println("1. Armour");
        System.out.println("2. Artefacts");
        System.out.println("98. Back");
        System.out.print("Choose which to upgrade : ");
        int input = scanner.nextInt();
        if(input == 98) return;
        if(input <= 0 || input > 2){
            System.out.println("Invalid Input");
            upgrade(scanner, wallet, setWallet);
        }
        if(input == 1){
            System.out.println("\nExisting Armour : "+ (armour != null ? armour.getName() : "No Armour"));
            upgradeSpecific(scanner,"Armour",Armour.listAll(),(type) -> armour = Armour.create(type), Armour::getBuyingPrice, wallet, setWallet);
            setWallet.accept(wallet - armour.getPrice());
        }
        else if(input == 2){
            System.out.println("\nExisting Artefact : "+ (artefact != null ? artefact.getName() : "No Artefact"));
            upgradeSpecific(scanner,"Artefact",Artefact.listAll(),(type) -> artefact = Artefact.create(type),Artefact::getBuyingPrice, wallet, setWallet);
            setWallet.accept(wallet - artefact.getPrice());
        }
    }

    private void upgradeSpecific(Scanner scanner, String type, String[] arr, Consumer<String> create, Function<String, Integer> getBuyingPrice, int wallet, Consumer<Integer> setWallet){
        System.out.println("\n" + type+"s Available to upgrade ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println((i+1) + ". " + arr[i]);
        }
        System.out.println("98. Back");
        System.out.print("Choose : ");
        int input = scanner.nextInt();
        if(input == 98) return;
        if(input <= 0 || input >arr.length){
            System.out.println("Invalid Input");
            upgradeSpecific(scanner,type,arr, create,getBuyingPrice, wallet, setWallet);
        }
        if(getBuyingPrice.apply(arr[input]) > wallet){
            System.out.println("Not Enough Money");
            upgradeSpecific(scanner,type,arr, create,getBuyingPrice, wallet,setWallet);
        }
        create.accept(arr[input]);
        setWallet.accept(wallet -  getBuyingPrice.apply(arr[input]));

    }

    public String getTribe() {
        return tribe;
    }

    public float getHealth() {
        float h = health;
        if(armour != null)
            h = armour.getHealthBonus(h);
        if(artefact != null)
            h = artefact.getHealthBonus(h);
        return h;

    }
    public void setHealth(float health) {
        this.health = health;
    }
    public int getDefence() {
        int d = defence;
        if(armour != null)
            d = armour.getDefenseBonus(d);
        if(artefact != null)
            d = artefact.getDefenseBonus(d);
        return d;
    }
    public int getAttack() {
        int a = attack;
        if(armour != null)
            a = armour.getAttackBonus(a);
        if(artefact != null)
            a = artefact.getAttackBonus(a);
        return a;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        int p = price;
        if(armour != null)
            p = armour.getPrice(p);
        if(artefact != null)
            p= artefact.getPrice(p);
        return p;
    }
    public int getSpeed() {
        int s = speed;
        if(armour != null)
            s = armour.getSpeedBonus(s);
        if(artefact != null)
            s = artefact.getSpeedBonus(s);
        return s;
    }

    public void updateAttributes(int attack, int defence, float health, int speed)
    {
      this.attack += attack;
      this.defence += defence;
      this.health += health;
      this.speed += speed;
    }

    public abstract Warrior clone() throws CloneNotSupportedException;

    public void printStats() {
        System.out.print(name);
        System.out.print("(" + tribe + ") -> ");
        System.out.print("Price: " + price + " ");
        System.out.print("Atk: " + attack + " ");
        System.out.print("Def: " + defence + " ");
        System.out.print("hp: " + health + " ");
        System.out.print("speed: " + speed + " ");
        if(armour != null)
            System.out.print("        wears Armour : " + armour.getName());
        if(artefact != null)
            System.out.print(" Artefact : " + artefact.getName());
    }
}


