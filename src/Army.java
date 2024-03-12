import Warriors.*;
import java.io.Serializable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Army implements Serializable {
    private Archer archer;
    private Knight knight;
    private Mage mage;
    private Healer healer;
    private MCreature mCreature;

    private static final List<String> predefinedAttackingSequence = Arrays.asList("healer", "mage", "mCreature", "knight", "archer");
    private static final List<String> predefinedDefendingSequence = Arrays.asList("mage", "knight", "archer", "mCreature", "healer");

    public void viewArmy() {
        System.out.println("Your Army");
        System.out.print("Archer : ");
        if (archer != null) archer.printStats();
        System.out.println();
        System.out.print("Knight : ");
        if (knight != null) knight.printStats();
        System.out.println();
        System.out.print("Mage : ");
        if (mage != null) mage.printStats();
        System.out.println();
        System.out.print("Healer : ");
        if (healer != null) healer.printStats();
        System.out.println();
        System.out.print("Mythical Creature : ");
        if (mCreature != null) mCreature.printStats();
        System.out.println();
    }

    public Queue<Warrior>[] getArmyQueues() {
        if (archer == null || knight == null || mage == null || healer == null || mCreature == null) {
            System.out.println("Not Enough Warriors in your team");
            return null;
        }

        Queue<Warrior> attackingCrew = new LinkedList<>();
        Queue<Warrior> defendingCrew = new LinkedList<>();

        try {
            Warrior[] unorganizedCrew = {archer.clone(), knight.clone(), mage.clone(), healer.clone(), mCreature.clone()};
            Arrays.sort(unorganizedCrew, Comparator.comparing(Warrior::getSpeed)
                    .reversed()
                    .thenComparing((w1, w2) -> {
                        int index1 = predefinedAttackingSequence.indexOf(w1.getName());
                        int index2 = predefinedAttackingSequence.indexOf(w2.getName());
                        return Integer.compare(index1, index2);
                    })
            );
            Collections.addAll(attackingCrew, unorganizedCrew);
            Arrays.sort(unorganizedCrew, Comparator.comparing(Warrior::getDefence)
                    .thenComparing((w1, w2) -> {
                        int index1 = predefinedDefendingSequence.indexOf(w1.getName());
                        int index2 = predefinedDefendingSequence.indexOf(w2.getName());
                        return Integer.compare(index1, index2);
                    })
            );
            Collections.addAll(defendingCrew, unorganizedCrew);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        Queue<Warrior>[] crew = new Queue[2];

        for (Warrior x : attackingCrew)
            System.out.println(x.getName());
        crew[0] = attackingCrew;
        crew[1] = defendingCrew;
        return crew;
    }

    private void recruit(Player player, String name, String[] nameList, Warrior existinigWarrior, Function<String, Integer> getBuyingPrice, Consumer<String> createWarrior) {
        //user inputs the name , shooter or ranger bla bla bla
        Scanner scanner = new Scanner(System.in);

        if (existinigWarrior != null) {
            int i = 0;
            do {
                System.out.print(name + " : ");
                existinigWarrior.printStats();
                System.out.println();
                System.out.println("1. Recruit New");
                System.out.println("2. Upgrade");
                System.out.println("3. Back");
                System.out.print("Choose : ");
                i = scanner.nextInt();
                System.out.println();
            } while (i <= 0 && i > 3);

            if (i == 3) return;
            if (i == 2) {
                existinigWarrior.upgrade(scanner, player.getGold(), player::setGold);
                return;
            }
        }
        System.out.print("Recruit an " + name + "       my wallet(" + player.getGold() + "gc): \n");
        for (int i = 0; i < nameList.length; i++) {
            System.out.println((i + 1) + ". " + nameList[i] + " (" + getBuyingPrice.apply(nameList[i]) + "gc)");
        }
        System.out.println("98. Back");
        System.out.print("Who to recruit : ");

        int input = 0;
//        try {
//            input = Integer.parseInt(scanner.nextLine());
//            System.out.println();
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid Input");
//            recruit(player,name,nameList,existinigWarrior,getBuyingPrice,createWarrior);
//            return;
//        }

        input = scanner.nextInt();
        System.out.println();


        if (input == 98) return;
        if (input <= 0 || input > nameList.length) {
            System.out.println("Invalid Input");
            recruit(player, name, nameList, existinigWarrior, getBuyingPrice, createWarrior);
            return;
        }
        String typeToCreate = nameList[input - 1];

        int initCost = getBuyingPrice.apply(typeToCreate);
        int sell = 0;
        if (existinigWarrior != null)
            sell = (int) (0.9 * existinigWarrior.getPrice());
        int cost = initCost - sell;

        System.out.println("----------- Bill ------------");
        System.out.println("You have                      " + player.getGold() + "gc");
        System.out.println("The " + typeToCreate + " costs              -" + initCost + "gc");
        if (existinigWarrior != null)
            System.out.println("Selling existing " + existinigWarrior.getName() + " -" + sell + "gc");
        else
            System.out.println("No existing " + name + " to sell " + "+0gc");
        System.out.println("Total Cost                 " + cost + "gc");
        System.out.println("-----------------------------\n");

        if (cost <= player.getGold()) {
            createWarrior.accept(typeToCreate);
            player.setGold(player.getGold() - cost);
        } else {
            System.out.println("No Enough Money!!");
            recruit(player, name, nameList, existinigWarrior, getBuyingPrice, createWarrior);
        }
    }

    public void recruitWarrior(Player player) {

        Scanner scanner = new Scanner(System.in);
        viewArmy();

        System.out.println("\nRecruit to army" + "        my wallet(" + player.getGold() + "gc)");
        System.out.println("1. Archer " + (archer != null ? "(Upgrade)" : ""));
        System.out.println("2. Knight " + (knight != null ? "(Upgrade)" : ""));
        System.out.println("3. Mage " + (mage != null ? "(Upgrade)" : ""));
        System.out.println("4. Healer " + (healer != null ? "(Upgrade)" : ""));
        System.out.println("5. Mythical Creature " + (mCreature != null ? "(Upgrade)" : ""));
        System.out.println("98. Back");
        System.out.print("Recruit Type : ");

        int input = 0;
        try {
            input = Integer.parseInt(scanner.nextLine());
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            recruitWarrior(player);
            return;
        }

        if (input == 98) return;

        if (input == 1) {
            //Archer
            String[] nameList = {"Shooter", "Ranger", "Sunfire", "Zing", "Saggitarius"};
            recruit(player, "Archer", nameList, archer, Archer::getBuyingPrice, (type) -> archer = Archer.create(type));
        } else if (input == 2) {
            //Knight
            String[] nameList = {"Squire", "Cavalier", "Templar", "Zoro", "Swiftblade"};
            recruit(player, "Knight", nameList, knight, Knight::getBuyingPrice, (type) -> knight = Knight.create(type));
        } else if (input == 3) {
            //Mage
            String[] nameList = {"Warlock", "Illusionist", "Enchanter", "Conjurer", "Eldritch"};
            recruit(player, "Mage", nameList, mage, Mage::getBuyingPrice, (type) -> mage = Mage.create(type));
        } else if (input == 4) {
            //Healer
            String[] nameList = {"Soother", "Medic", "Alchemist", "Saint", "Lightbringer"};
            recruit(player, "Healer", nameList, healer, Healer::getBuyingPrice, (type) -> healer = Healer.create(type));
        } else if (input == 5) {
            //Mythical Creature
            String[] nameList = {"Dragon", "Basilisk", "Hydra", "Phoenix", "Pegasus"};
            recruit(player, "Mythical Creature", nameList, mCreature, MCreature::getBuyingPrice, (type) -> mCreature = MCreature.create(type));
        }
        recruitWarrior(player);
    }

    public void recruitKnight(Player player) {
        //user inputs the name , shooter or ranger bla bla bla
        String[] nameList = {"Squire", "Cavalier", "Templar", "Zoro", "Swiftblade"};
        recruit(player, "Knight", nameList, knight, Knight::getBuyingPrice, (type) -> knight = Knight.create(type));
    }

    public boolean isArmyReady() {
        if (archer == null || knight == null || mage == null || healer == null || mCreature == null) {
            return false;

        }
        return true;

    }
}

