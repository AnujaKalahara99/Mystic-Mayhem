import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class SaveGame {
    private static ArrayList<Player> players = new ArrayList<>();

    public static void addPlayer(Player newPlayer){
        players.add(newPlayer);
    }
    public  static  void serializePlayerProfiles(){

       try (FileOutputStream fileOut = new FileOutputStream("players.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(players);
            System.out.println("Game saved successfully!");

       } catch (IOException error){
           System.out.println("I/O Error");
           error.printStackTrace();
       }
    }

    public static ArrayList<Player> deserializePlayers() {
        try (FileInputStream fileIn = new FileInputStream("players.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            players = (ArrayList<Player>)in.readObject();
            System.out.println("Game loaded successfully!\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("I/O Error");
            e.printStackTrace();
        }
        return players;
    }
}
