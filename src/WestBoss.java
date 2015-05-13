/* Daniel Caruso
 * WestBoss.java
 * 2/23/15
 */
public class WestBoss extends NPC{
	public WestBoss(){
		currentNPCHealth = 35;
		experienceToGive = 15;
		attackDamageNPC = 0;
		accuracy = 1;
		armor = 0;
		enemyName = "West Boss";
	}
	public void attackMove(int turnCounter){ //After the first wave of slashers is killed, 2 more spawn every 3 turns
		if(turnCounter%3 == 0){
			rooms[4][0].populateSlashers();
			rooms[4][0].populateSlashers();
		}
	}
}
