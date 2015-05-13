/* Daniel Caruso
 * FinalBoss.java
 * 2/23/15
 * */
public class FinalBoss extends NPC{
	public FinalBoss(){
		currentNPCHealth = 50;
		experienceToGive = 50;
		attackDamageNPC = 5;
		accuracy = 1;
		armor = 2;
		enemyName = "Final Boss";
	}
	public void attackMove(){ 
		rooms[0][4].populateSlashers();
		rooms[0][4].populateSlashers();
		rooms[0][4].populateExploders();
	}
}
