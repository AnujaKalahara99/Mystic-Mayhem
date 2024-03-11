import Warriors.Warrior;

import java.util.ArrayList;
import java.util.Queue;

public class Battle {
    private Player player1; //person who declares war
    private Player player2;

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startBattle() {
        System.out.println(player1.getUserName() + " vs. " + player2.getUserName());


        Queue<Warrior>[] player1Army = player1.getArmy().getArmyQueues();
        Queue<Warrior>[] player2Army = player1.getArmy().getArmyQueues();
        Queue currentAttacker;
        Queue currentDefender;
        Player attacker, defender;


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
                break;
            }

            if(player2Army[1].isEmpty()){
                System.out.println(player1.getUserName() + "won");
                break;
            }
            if(i==20)
                System.out.println("Draw");

        }

    }

    private void performTurn(Queue<Warrior> attackerQ, Queue<Warrior> defenderQ,Player attacker, Player defender) {
        Warrior attackingWarrior = attackerQ.remove();
        if(attackingWarrior.getHealth() <= 0)
            attackingWarrior = attackerQ.remove();
        attackerQ.add(attackingWarrior);
        Warrior defendingWarrior = defenderQ.peek();

        double damage = calculateDamage(attackingWarrior, defendingWarrior);

        double newHealth = defendingWarrior.getHealth() - damage;  //
        defendingWarrior.setHealth((float)newHealth);

        //Print each warrior outputs
        System.out.println(attacker.getName() + "'s " + attackingWarrior.getName() + " attacks " +
                defender.getName() + "'s " + defendingWarrior.getName());
        System.out.println(defendingWarrior.getName() + "'s health: " + newHealth);
        System.out.println(attackingWarrior.getName() + "'s health: " + attackingWarrior.getHealth());

        if (newHealth <= 0) {
            System.out.println(defendingWarrior.getName() + "died");
            defenderQ.remove();
        }

    }
    private double calculateDamage (Warrior attackingWarrior, Warrior defendingWarrior){

            return  0.5*(attackingWarrior.getAttack()) - 0.1*defendingWarrior.getdefence();
    }

}
