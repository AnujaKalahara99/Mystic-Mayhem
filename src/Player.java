import java.util.ArrayList;
import java.util.List;
import  java.util.Objects;
import  java.io.Serializable;
import java.util.Random;

//import SaveGame.*;
public class Player implements Serializable{
    private static int count=0;
    private int id;
    private String userName;
    private String name;
    private int gold;
    private float xp;
    private String homeGround;
    private String password;
    private Army army;
    private boolean combatMode = true;

    public Player(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.army = new Army();
        this.id=++count;
        this.gold=500;
        this.xp=0;



    }
    public Player(Army army) {
        this.army = army;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public  boolean isPassword(String password) {
        if (Objects.equals(this.password, password))
            return true;
        else
            return false;
    }

    public Army getArmy(){
        return army;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXp(float xp) {
        this.xp = xp;
    }

    public boolean getCombatMode() {
        if(homeGround == null || homeGround.isEmpty()) return false;
        if(!army.isArmyReady()) return false;
        return combatMode;
    }

    public void setCombatMode(Boolean combatMode) {
        this.combatMode = combatMode;
    }

    public Player selectOpponent(ArrayList<Player> players){
        if(!army.isArmyReady() ){
            System.out.println("Not Enough Warriors in your team");
            return null;
        }
        if(this.homeGround == null || this.homeGround.isEmpty()){
            System.out.println("HomeGround Not Selected");
            return null;
        }
        ArrayList<Player> playerscopy= new ArrayList<Player>(players);
        playerscopy.remove(this);

        if (playerscopy.isEmpty()) {
            System.out.println("No users available to fight");
            return null;
        }

        Player opponent = null;
        Random random=new Random();
        while (!playerscopy.isEmpty()){
            int randomNumber= random.nextInt(playerscopy.size());
            opponent = playerscopy.get(randomNumber);
            playerscopy.remove(randomNumber);
            if(opponent.getCombatMode()) break;
        }

        if(opponent == null) System.out.println("No users available to fight");
        return opponent;

    }

    public float getXp() {
        return xp;
    }

    public void setHomeGround(int choice){
        switch (choice){
            case 1:
                this.homeGround = "hillcrest";
                break;
            case 2:
                this.homeGround = "marshland";
                break;
            case 3:
                this.homeGround = "dessert";
                break;
            case 4:
                this.homeGround = "arcane";
                break;
        }
    }
    public String getHomeGround() {
        return homeGround;
    }

}

