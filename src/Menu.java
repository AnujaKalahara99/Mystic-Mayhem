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
            System.out.println("1. Profile");
            System.out.println("2. Army");
            System.out.println("3. Attack");
            //System.out.println("4. Combat Mode (" + (player.getCombatMode() ? "On" : "Off") + ")");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    toggleCombatMode(player);
                    break;
                case 5:
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
        System.out.println("Welcome back " +  player.getUserName() + "!");
        System.out.println("Profile:");
        System.out.println("Name: " + player.getName());
        System.out.println("Username: " + player.getUserName());
        System.out.println("1. Change Name");
        System.out.println("2. Exit");
    }



    private static void profileOption(Scanner scanner, Player player) {
        int choice;
        do {
            profileprint(player);
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice==1) {
                System.out.print("Enter your new name: ");
                String newName = scanner.nextLine();
                player.setName(newName);
                System.out.println("Name changed successfully!");

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
}
