import Warriors.Healer;
import Warriors.Warrior;

import java.util.Queue;

public class Battle {
    private Player player1; //person who declares war
    private Player player2;
    private String battlefield;

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.battlefield=player2.getHomeGround();
    }


    public void startBattle() {
        System.out.println(player1.getUserName() + " vs. " + player2.getUserName());


        Queue<Warrior>[] player1Army = player1.getArmy().getArmyQueues();
        Queue<Warrior>[] player2Army = player1.getArmy().getArmyQueues();
        Queue currentAttacker;
        Queue currentDefender;
        Player attacker, defender;

        if(battlefield=="hillcrest"){
            hillcrest(player1Army[0]);
            hillcrest(player2Army[0]);
        }
        if(battlefield=="marshland"){
            marshland(player1Army[0]);
            marshland(player2Army[0]);
        }
        if(battlefield=="desert"){
            desert(player1Army[0]);
            desert(player2Army[0]);
        }
        if(battlefield=="arcane"){
            arcane(player1Army[0]);
            arcane(player2Army[0]);
        }



        for (int i = 1; i <= 20; i++) {

            if (i%2==1) {
                currentAttacker = player1Army[0];
                currentDefender = player2Army[1];
                attacker=player1;
                defender=player2;
            }
            else {
                currentAttacker = player2Army[0];
                currentDefender = player1Army[1];
                attacker=player2;
                defender=player1;

            }
            System.out.println("Turn" + i + ":" + attacker.getUserName());
            performTurn(currentAttacker, currentDefender,attacker,defender);

            if(player1Army[1].isEmpty()){
                System.out.println(player2.getName() + " won!");
                player2.setGold((int)(player1.getGold()*0.1+player2.getGold()));
                player1.setGold((int)(player1.getGold()*0.9));
                player2.setXp(player2.getXp()+1);
                break;
            }

            if(player2Army[1].isEmpty()){
                System.out.println(player1.getUserName() + "won!");
                player1.setGold((int)(player2.getGold()*0.1+player1.getGold()));
                player2.setGold((int)(player2.getGold()*0.9));
                player1.setXp(player1.getXp()+1);
                break;
            }
            if(i==20)
                System.out.println("Draw");

        }
        System.out.println(player1.getUserName() +" XP: "+player1.getXp() + "  gold coins: "+player1.getGold());
        System.out.println(player2.getUserName() +" XP: "+player2.getXp() + "  gold coins: "+player2.getGold());

    }

    private void performTurn(Queue<Warrior> attackerQ, Queue<Warrior> defenderQ,Player attacker, Player defender) {
        synchronized (this) {
            Warrior attackingWarrior = attackerQ.remove();
            while (attackingWarrior.getHealth() <= 0)
                attackingWarrior = attackerQ.remove();
            if(battlefield=="arcane" && attackingWarrior.getTribe()=="mystics"){
                attackingWarrior.setHealth(attackingWarrior.getHealth()*110/100);
            }

            //healer functionality
            if(attackingWarrior instanceof Healer){
                Warrior minHealthWarrior=attackingWarrior;
                for(Warrior element:attackerQ){
                    if(element.getHealth()<minHealthWarrior.getHealth()){
                        minHealthWarrior=element;
                    }
                }
                double newHealth = minHealthWarrior.getHealth() +attackingWarrior.getAttack()*0.1;
                minHealthWarrior.setHealth((float) newHealth);

                System.out.println(attacker.getName() + "'s " + attackingWarrior.getName() + " heals " +
                        minHealthWarrior.getName());
                System.out.println(minHealthWarrior.getName() + "'s health: " + newHealth);
                System.out.println(attackingWarrior.getName() + "'s health: " + attackingWarrior.getHealth());

                //2nd turn in arcane for saint
                if(battlefield=="hillcrest" && attackingWarrior.getTribe()=="highlanders"){
                        minHealthWarrior =attackingWarrior;
                    for(Warrior element:attackerQ){
                        if(element.getHealth()<minHealthWarrior.getHealth()){
                            minHealthWarrior=element;
                        }
                    }
                    newHealth = minHealthWarrior.getHealth() +attackingWarrior.getAttack()*0.2*0.1;
                    minHealthWarrior.setHealth((float) newHealth);
                    System.out.println("bonus turn");
                    System.out.println(attacker.getName() + "'s " + attackingWarrior.getName() + " heals " +
                            minHealthWarrior.getName());
                    System.out.println(minHealthWarrior.getName() + "'s health: " + newHealth);
                    System.out.println(attackingWarrior.getName() + "'s health: " + attackingWarrior.getHealth());

                }
                return;
            }  // healer stuff ends

            attackerQ.add(attackingWarrior);
            Warrior defendingWarrior = defenderQ.peek();

            double damage = calculateDamage(attackingWarrior, defendingWarrior);

            double newHealth = defendingWarrior.getHealth() - damage;  //
            defendingWarrior.setHealth((float) newHealth);

            //Print each warrior outputs
            System.out.println(attacker.getName() + "'s " + attackingWarrior.getName() + " attacks " +
                    defender.getName() + "'s " + defendingWarrior.getName());
            System.out.println(defendingWarrior.getName() + "'s health: " + newHealth);
            System.out.println(attackingWarrior.getName() + "'s health: " + attackingWarrior.getHealth());

            if (newHealth <= 0) {
                System.out.println(defendingWarrior.getName() + "died");
                defenderQ.remove();
            }

            //bonus turn attack
            if(battlefield=="hillcrest" && attackingWarrior.getTribe()=="highlanders"){
                damage=0.5*(attackingWarrior.getAttack()*0.2) - 0.1*defenderQ.peek().getDefence();
                if(damage>0){
                    newHealth = defendingWarrior.getHealth() - damage;
                    defenderQ.peek().setHealth((float) newHealth);

                }
                //Print each warrior outputs for bonus turn
                System.out.println(attacker.getName() + "'s " + attackingWarrior.getName() + " attacks " +
                        defender.getName() + "'s " + defenderQ.peek().getName());
                System.out.println(defenderQ.peek().getName() + "'s health: " + defenderQ.peek().getHealth());
                System.out.println(attackingWarrior.getName() + "'s health: " + attackingWarrior.getHealth());

            }
            try {
                wait(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private double calculateDamage (Warrior attackingWarrior, Warrior defendingWarrior){

            return  0.5*(attackingWarrior.getAttack()) - 0.1*defendingWarrior.getDefence();
    }

    private void hillcrest(Queue<Warrior> army){
        for(Warrior element:army){
            if(element.getTribe()=="highlanders"){
                element.updateAttributes(1,1,0,0);
            }
            if(element.getTribe()=="marshlanders"){
                element.updateAttributes(0,0,0,-1);
            }
            if(element.getTribe()=="sunchildren"){
                element.updateAttributes(0,0,0,-1);
            }
                        }

    }
    private void marshland(Queue<Warrior> army){
        for(Warrior element:army){

            if(element.getTribe()=="marshlanders"){
                element.updateAttributes(0,2,0,0);
            }
            if(element.getTribe()=="sunchildren"){
                element.updateAttributes(-1,0,0,0);
            }
            if(element.getTribe()=="mystics"){
                element.updateAttributes(2,0,0,0);
            }
        }

    }
    private void desert(Queue<Warrior> army) {
        for (Warrior element : army) {
            if (element.getTribe() == "highlanders") {
                element.updateAttributes(0, 0, -1, 0);
            }
            if (element.getTribe() == "sunchildren") {
                element.updateAttributes(1, 0, 0, 0);
            }
        }
    }


    private void arcane(Queue<Warrior> army){
        for(Warrior element:army){
            if(element.getTribe()=="mystics"){
                element.updateAttributes(2,0,0,0);
            }
            if(element.getTribe()=="marshlanders"){
                element.updateAttributes(0,-1,0,-1);
            }
            if(element.getTribe()=="highlanders"){
                element.updateAttributes(0,-1,0,-1);
            }

        }

    }

}
