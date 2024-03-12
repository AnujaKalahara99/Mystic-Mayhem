import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// menu
//1. Profile ---shows profile details and can change name--- need to add combat mode bool to player class or where suitable
//2. View Army ---- should be implemented to show the army
//3. Attack ----- should be implemented to start attack
//4. Combat Mode (Off) -----changes combat mode
//5. Exit

public class Menu {

    public static void displayMenu(Player player, ArrayList<Player> players) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("Welcome back" + player.getUserName() + "!");
            System.out.println("Gold coins - " + player.getGold());
            System.out.println("XP - " + player.getXp());
            System.out.println("*****************************************************");
            System.out.println("Menu");
            System.out.println("1. View Profile");
            System.out.println("2. View Army");
            System.out.println("3. Attack");
            System.out.println("4. Leaderboard");
            //System.out.println("4. Combat Mode (" + (player.getCombatMode() ? "On" : "Off") + ")");
            System.out.println("5. HomeGround");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
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
//                    toggleCombatMode(player);
                    for (int i = 0; i < players.size(); i++) {
                        System.out.println("Player " + players.get(i).getUserName() + "  has XP " + players.get(i).getXp());
                    }
                    break;
                case 5:
                    selectHomeGround(player);
                case 6:
                    Main.loop(players);
                    break;
                case 7:
                    running = false;
                    SaveGame.serializePlayerProfiles();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void profileprint (Player player) {
        System.out.println("*****************************************************");
        System.out.println("Welcome back " +  player.getUserName() + "!");
        System.out.println("Profile:");
        System.out.println("Name: " + player.getName());
        System.out.println("Username: " + player.getUserName());
        System.out.println("1. Change Name");
        System.out.println("2. Back");
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
                String confirm = scanner.nextLine();
                if(confirm == "y") {
                    player.setName(newName);
                    System.out.println("Name changed successfully!");
                }
                choice = 2;

            }
            else if(choice!=2){
                System.out.println("Invalid choice. Please try again.");
            }
        }
        while (choice != 2);

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
            System.out.println("Opponent Name: "+opponent.getName());
            System.out.println("XP Level     : "+opponent.getXp());
            //System.out.println("Opponent Name: "+opponent.getName()+"/n");
            System.out.println("1. Challenge "+opponent.getName());
            System.out.println("2. Select Another Player");
            System.out.println("3. Back");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice==1) {
                Battle battle=new Battle(player,opponent);
                battle.startBattle();
                break;

            }

        }while (choice!=3);

        // Code to handle attack option
    }

    private static void toggleCombatMode(Player player) {
        player.setCombatMode(!player.getCombatMode());
        System.out.println("Combat Mode is now " + (player.getCombatMode() ? "On" : "Off"));
    }
    private static void selectHomeGround(Player player){
        boolean check = false;
        do {
            System.out.println("Select home ground : ");
            System.out.println("1.Hillcrest");
            System.out.println("2.Marshland");
            System.out.println("3.Dessert");
            System.out.println("4.Arcane");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            player.setHomeGround(num);
            if(num > 0 && num <= 4){
                check = true;
            }
        }
        while (check);
    }
}


