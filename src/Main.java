import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        /////////Anuja: mage wadee harii hri nm hodagnim

       ArrayList<Player> players;
       players = SaveGame.deserializePlayers();
//        //Methana player class eke hashmap eka initialize karagena iwara unata passe login ekata call karanna
       LoginSignupSystem.setPlayerData(players);
       Player activePlayer=LoginSignupSystem.run();
       if(activePlayer == null) return;
       Menu.displayMenu(activePlayer,players);

        //Player player = new Player();
        //player.setUserName("Anuja");
        //player.setGold(1000);
        //Army army = new Army();

//        army.recruitArcher(player);
        //army.recruitWarrior(player);

//        army.recruitArcher(player);
//combat mode ek bool ek tiyenne koheda
//        Player player=LoginSignupSystem.run();1
//        System.out.println(player.getId());
//        LoginSignupSystem.run();
    }
}