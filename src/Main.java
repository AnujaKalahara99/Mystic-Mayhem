import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

       ArrayList<Player> players;
       players = SaveGame.deserializePlayers();

       System.out.println();
       System.out.println("Welcome to Mystic Mayhem! where realms colide and legends rise. Forge your destiny amidst the chaos, where armies clash and alliances are forged.");
       System.out.println("Will you conquer the unknown, or succumb to the mystic mayham? Choose your path wisely, for the fate of realms rests in your hands.");
       LoginSignupSystem.setPlayerData(players);
       loop(players);
    }

    public static void loop(ArrayList<Player> players){
       Player activePlayer=LoginSignupSystem.run();
       if(activePlayer == null) return;
       System.out.println();
       Menu.displayMenu(activePlayer,players);
    }
}