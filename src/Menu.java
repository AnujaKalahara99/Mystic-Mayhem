import Warriors.Warrior;

import java.util.*;

// menu
//1. Profile ---shows profile details and can change name--- need to add combat mode bool to player class or where suitable
//2. View Army ---- should be implemented to show the army
//3. Attack ----- should be implemented to start attack
//4. Combat Mode (Off) -----changes combat mode
//5. Exit

public class Menu {

    //This for static method to wait and loop
    private static final Object lock = new Object();

    public static Player displayMenu(Player player, ArrayList<Player> players) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("You are " + player.getName());
            System.out.println("Gold coins - " + player.getGold());
            System.out.println("XP - " + player.getXp());
            System.out.println("*****************************************************");
            System.out.println("Menu");
            System.out.println("1. View Profile");
            System.out.println("2. Army");
            System.out.println("3. Attack");
            System.out.println("4. Leaderboard");
            //System.out.println("4. Combat Mode (" + (player.getCombatMode() ? "On" : "Off") + ")");
            System.out.println("5. HomeGround");
            System.out.println("6. Logout");
            System.out.println("99. Exit");
            System.out.println("*****************************************************");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();

            switch (choice) {
                case 1:
                    profileOption(scanner,player);
                    break;
                case 2:
                    armyOption(player);
                    break;
                case 3:
                    attackOption(player,players,scanner);
                    break;
                case 4:
                    players.sort(Comparator.comparing(Player::getXp).reversed());
                    System.out.println("Leaderboard ranked to XP");
                    synchronized (lock) {
                        for (Player value : players) {
                            System.out.println("Player " + value.getName() + "  has XP " + value.getXp()
                                    + (value == player ? " (You)" : ""));
                            try {
                                lock.wait(500);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                    System.out.println();
                    break;
                case 5:
                    selectHomeGround(player);
                    break;
                case 6:
                    SaveGame.serializePlayerProfiles();
                    player=null;
                    return player;
                   // break;
                case 99:
                    running = false;
                    SaveGame.serializePlayerProfiles();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        return player;
    }

    private static void profileprint (Player player) {
        System.out.println("*****************************************************");
        System.out.println("Profile:");
        System.out.println("Name: " + player.getName());
        System.out.println("Username: " + player.getUserName());
        System.out.println("Wallet: " + player.getGold());
        System.out.println("XP : " + player.getXp());
        System.out.println("1. Change Name");
        System.out.println("98. Back");
        System.out.println("*****************************************************");
        System.out.print("Choose an option : ");

    }



    private static void profileOption(Scanner scanner, Player player) {
        int choice;
        do {
            profileprint(player);
            choice = scanner.nextInt();
            System.out.println();
            scanner.nextLine();
            if (choice==1) {
                System.out.print("Enter your new name: ");
                String newName = scanner.nextLine();
                System.out.println("Confirm " + newName + " as your new name? (y/n)");
                String confirm = scanner.nextLine().toLowerCase();

                if(confirm.equals("y")) {
                    player.setName(newName);
                    System.out.println("Name changed successfully!");
                }
                choice = 98;

            }
            else if(choice!=98){
                System.out.println("Invalid choice. Please try again.");
            }
        }
        while (choice != 98);

    }

    private static void armyOption(Player player) {
        player.getArmy().recruitWarrior(player);
        // Code to display player's army
    }

    private static void attackOption(Player player, ArrayList<Player> players, Scanner scanner) {
        int choice;
        System.out.println("Attack:");
        do{
            Player opponent=player.selectOpponent(players);
            if(opponent == null){
                System.out.println("Cannot battle until you select a homeground.");
                return;
            }
            System.out.println("Opponent Name: "+opponent.getName());
            System.out.println("XP Level     : "+opponent.getXp());
            //System.out.println("Opponent Name: "+opponent.getName()+"/n");
            System.out.println("1. Challenge "+opponent.getName());
            System.out.println("2. Select Another Player");
            System.out.println("98. Back");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice==1) {
                Battle battle=new Battle(player,opponent);
                battle.startBattle();
                break;

            }
            else if(choice > 2 && choice != 98|| choice < 0 ){
                System.out.println("Invalid input! Try again");
            }

        }while (choice!=98);

        // Code to handle attack option
    }

    private static void selectHomeGround(Player player){
        boolean check = true;
        do {
            if(player.getHomeGround() == null){
                System.out.println("You haven't selected a homeground yet");
            }
            else{
                System.out.println("Your current home ground is " + player.getHomeGround());
            }
            System.out.println("*****************************************************");
            System.out.println("Select your home ground : ");
            System.out.println("1. Hillcrest");
            System.out.println("2. Marshland");
            System.out.println("3. Dessert");
            System.out.println("4. Arcane");
            System.out.println("98. Back");
            System.out.println("*****************************************************");

            Scanner scanner = new Scanner(System.in);
            int num;
            try {
                num = scanner.nextInt();
                if(num > 0 && num <= 4){
                    player.setHomeGround(num);
                    System.out.println("You have successfully set homeground to " + player.getHomeGround());
                    return;
                }
                else if (num == 98){
                    //check = false;
                    return;
                }
            }
            catch (Exception e){
                System.out.println("Invalid input! Please try again.");
            }
        }
        while (true);
    }
}
