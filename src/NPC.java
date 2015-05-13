/* Daniel Caruso
 * NPC.java
 * 2/3/2015
 */
import java.util.*;
public class NPC extends Environment{
	protected int currentNPCHealth, experienceToGive, attackDamageNPC, armor;
	protected String enemyName;
	protected double accuracy;
	public NPC(){ //Default constructors
		currentNPCHealth = -1;
		experienceToGive = 0;
		attackDamageNPC = 0;
		accuracy = 1;
		armor = 0;
		enemyName = "null";
	}
	private MainCharacter player; //To use getters from MainCharacter.java
	public boolean hitAttempt(double mobAccuracy){ //Checks if hit goes through for mobs @param mobAccuracy, inherited from subclasses, may not need that param at all. Check back
		double shotRoll = Math.random();
		if(shotRoll <= mobAccuracy)
			return true;
		else
			return false;
	}
	public int attackPlayer(){ //Deals damage to player, @param attackDamageNPC. Handling death conditions will be done in EventHandler.java Don't actually need to return anything but I'm too lazy to change the return statements
		player = MainCharacter.playerCharacter;
		if(hitAttempt(getAccuracy())){ //Rolls accuracy, then attacks. 
			if(Armor.getHasArmor()){
				return player.getCurrentPlayerHealth()-(attackDamageNPC-2); //Subtracts damage with armor. If this doesn't work, then player.getCurrentPlayerHealth+2-attackDamageNPC;
			}
			else
				return player.getCurrentPlayerHealth()-attackDamageNPC;
		}
		else
			return 0;
	}
	public int getHealth(){ //currentNPCHealth is assigned in the subclass
		return currentNPCHealth;
	}
	public int getExpToGive(){ //experienceToGive is assigned in the subclass
		return experienceToGive;
	}
	public int getArmor(){ //armor is assigned in the subclass
		return armor;
	}
	public int getAttackDamage(){
		return attackDamageNPC;
	}
	public String getName(){
		return enemyName;
	} 
	public double getAccuracy(){
		return accuracy;
	}
}

