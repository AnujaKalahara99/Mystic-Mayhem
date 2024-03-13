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
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();

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
            Player player = users.get(username);
            System.out.println("Signup successful! Welcome " + player.getName());
            return player;
        } else {
            System.out.println("Invalid username or password. Please try again.\n");
            return null;
        }
    }

    private static Player signup(Scanner scanner) {
        boolean check;
        String name , username , password;
        do{

            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            if(name.equals("")){
                System.out.println("Name cannot be null");
                check = true;
            }
            else{ check = false;}
        }
        while(check);
        do{
            System.out.print("Enter a unique username: ");
            username = scanner.nextLine();
            if(username.equals("")){
                System.out.println("Username cannot be null");
                check = true;
            }
            else{check = false;}
        }
        while(check);

        while ( users.containsKey(username)) {
            System.out.println("Username already taken. Please choose another one.");
            System.out.print("Enter a unique username: ");
            username = scanner.nextLine();
        }

        do {
            System.out.print("Set a password: ");
            password = scanner.nextLine();
            if(password.equals("")){
                check = true;
            }
        }
        while(check);
        Player newPlayer = new Player(username, name, password);
        users.put(username, newPlayer);

        System.out.println("Signup successful! Welcome " + newPlayer.getName());
        SaveGame.addPlayer(newPlayer);
        return newPlayer;
    }

    public static void setPlayerData(List<Player> players){
        for(Player savedPlayer :players){
            users.put(savedPlayer.getUserName(),savedPlayer);
        }
    }
}
