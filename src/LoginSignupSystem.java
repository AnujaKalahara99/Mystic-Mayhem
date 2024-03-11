import java.lang.reflect.Array;
import java.util.*;

//the login/signup menu, need to create an object in
//main and use player=object.run(); will return the current player.

public class LoginSignupSystem {
    private static HashMap<String,Player> users=new HashMap<>();

    private static Player player;
    public static Player run() {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");
            System.out.println("4. ListAllPlayers");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    player=login(scanner);
                    break;
                case 2:
                    player=signup(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                case 4:  ///////////////////////////delete this later
                    listPlayers();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if(player!=null)
                return player;
        }
        scanner.close();
        return null;
    }

    private static Player login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).isPassword(password)) {
            System.out.println("Login successful!");

            return users.get(username);
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return null;
        }
    }

    private static Player signup(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter a unique username: ");
        String username = scanner.nextLine();
        while ( users.containsKey(username)) {
            System.out.println("Username already taken. Please choose another one.");
            System.out.print("Enter a unique username: ");
            username = scanner.nextLine();
        }

        System.out.print("Set a password: ");
        String password = scanner.nextLine();
        Player newPlayer = new Player(username, name, password);
        users.put(username, newPlayer);

        System.out.println("Signup successful!");
        SaveGame.addPlayer(newPlayer);
        return newPlayer;
    }

    private static void listPlayers() {
        System.out.println(users);
//        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
    }
    public static void setPlayerData(List<Player> players){
        for(Player savedPlayer :players){
            users.put(savedPlayer.getUserName(),savedPlayer);
        }
    }
}
