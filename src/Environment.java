/* Daniel Caruso
 * Environment.java
 * 2/21/15*/
import java.util.*;
public class Environment extends EventHandler{
	public ArrayList<NPC> enemiesInRoom; //List of enemies found per room
	public ArrayList<Items> itemsInRoom; //List of items found per room
	//private int locationTestR, locationTestC; //TBH I have no clue what those were gonna be used for
	private boolean isRoomVar; //Probably not needed
	public static Environment[][] rooms = new Environment[8][12]; //Essentially the map; hardcoded. 2D array containing Environment objects
	public Environment(){ //Constructs for some default room. Map will be hardcoded. Environment objects contain NPC and Items objects
		enemiesInRoom = new ArrayList<NPC>();
		itemsInRoom = new ArrayList<Items>();
		isRoomVar = true;
	}
	public Environment(int num){ //To create a useless room object that can be used to call getRoom
		if(num == -1)
			isRoomVar = true;
	}
	/*public Environment(int locationR, int locationC){
		locationTestR = locationR;
		locationTestC = locationC;
	}*/
	public void createMap(){ //Creates the map
		rooms[0][0] = null;
		rooms[0][1] = null;
		rooms[0][2] = null;
		rooms[0][3] = null;
		rooms[0][4] = null;
		rooms[0][5] = null;
		rooms[0][6] = null;
		rooms[0][7] = null;
		rooms[0][8] = null;
		rooms[0][9] = null;
		rooms[0][10] = null;
		rooms[0][11] = null;
		rooms[1][0] = null;
		rooms[1][1] = null;
		rooms[1][2] = null;
		rooms[1][3] = null;
		rooms[1][7] = null;
		rooms[1][8] = null;
		rooms[1][9] = null;
		rooms[1][10] = null;
		rooms[1][11] = null;
		rooms[2][0] = null;
		rooms[2][1] = null;
		rooms[2][2] = null;
		rooms[2][3] = null;
		rooms[2][4] = null;
		rooms[2][5] = null;
		rooms[2][8] = null;
		rooms[2][9] = null;
		rooms[2][10] = null;
		rooms[2][11] = null;
		rooms[3][0] = null;
		rooms[3][1] = null;
		rooms[3][2] = null;
		rooms[3][3] = null;
		rooms[3][4] = null;
		rooms[3][5] = null;
		rooms[3][6] = null;
		rooms[3][8] = null;
		rooms[3][9] = null;
		rooms[3][10] = null;
		rooms[3][11] = null;
		rooms[4][0] = null;
		rooms[4][1] = null;
		rooms[4][9] = null;
		rooms[4][10] = null;
		rooms[4][11] = null;
		rooms[5][0] = null;
		rooms[5][3] = null;
		rooms[5][4] = null;
		rooms[5][6] = null;
		rooms[5][7] = null;
		rooms[5][10] = null;
		rooms[5][11] = null;
		rooms[6][0] = null;
		rooms[6][1] = null;
		rooms[6][2] = null;
		rooms[6][3] = null;
		rooms[6][4] = null;
		rooms[6][6] = null;
		rooms[6][7] = null;
		rooms[6][8] = null;
		rooms[6][11] = null;
		rooms[7][0] = null;
		rooms[7][1] = null;
		rooms[7][2] = null;
		rooms[7][3] = null;
		rooms[7][4] = null;
		rooms[7][5] = null;
		rooms[7][6] = null;
		rooms[7][7] = null;
		rooms[7][8] = null;
		rooms[7][9] = null;
		rooms[7][10] = null;
		rooms[7][11] = null;
		rooms[6][5] = new Environment();
		rooms[5][5] = new Environment();
		rooms[4][5] = new Environment();
		rooms[4][5].populateNWA();
		rooms[4][5].populateWeapons("uzi");
		rooms[4][4] = new Environment();
		rooms[4][4].populateNWA();
		rooms[4][4].populateNWA();
		rooms[4][3] = new Environment();
		rooms[4][3].populateNWA();
		rooms[4][3].populateSlashers();
		rooms[4][3].populateSlashers();
		rooms[4][3].populateWeapons("sniper");
		rooms[4][2] = new Environment();
		rooms[4][2].populateWeapons("grenades");
		rooms[4][2].populateNWA();
		rooms[4][2].populateSlashers();
		rooms[5][2] = new Environment();
		rooms[5][2].populateExploders();
		rooms[5][2].populateSpitters();
		rooms[5][2].populateMedPacks(1);
		rooms[5][2].populateMedPacks(1);
		rooms[5][1] = new Environment();
		rooms[5][1].populateMedPacks(1);
		rooms[5][1].populateMedPacks(1);
		rooms[5][1].populateKeys(); //here
		rooms[5][1].populateWeapons("rifle");
		rooms[5][1].populateWeapons("shotgun");
		rooms[5][1].populateWestBoss();
		rooms[4][6] = new Environment();
		rooms[4][6].populateSlashers();
		rooms[4][6].populateSlashers();
		rooms[4][7] = new Environment();
		rooms[4][7].populateReavers();
		rooms[4][7].populateSlashers();
		rooms[4][8] = new Environment();
		rooms[4][8].populateExploders();
		rooms[4][8].populateSpitters();
		rooms[5][8] = new Environment();
		rooms[5][8].populateReavers();
		rooms[5][8].populateSlashers();
		rooms[5][8].populateNWA();
		rooms[5][8].populateMedPacks(1);
		rooms[5][8].populateMedPacks(1);
		rooms[5][8].populateWeapons("rocketlauncher");
		rooms[5][9] = new Environment();
		rooms[5][9].populateBehemoths();
		rooms[6][9] = new Environment();
		rooms[6][9].populateSlashers();
		rooms[6][9].populateSlashers();
		rooms[6][9].populateSlashers();
		rooms[6][9].populateWeapons("grenades");
		rooms[6][9].populateMedPacks(1);
		rooms[6][9].populateMedPacks(1);
		rooms[6][10] = new Environment();
		rooms[6][10].populateEastBoss();
		rooms[6][10].populateKeys();
		rooms[6][10].populateWeapons("plasmarifle");
		rooms[3][7] = new Environment();
		rooms[3][7].populateSlashers();
		rooms[3][7].populateMedPacks(2);
		rooms[3][7].populateMedPacks(2);
		rooms[2][7] = new Environment();
		rooms[2][7].populateSlashers();
		rooms[2][7].populateSpitters();
		rooms[2][7].populateSpitters();
		rooms[2][7].populateWeapons("grenades");
		rooms[2][6] = new Environment();
		rooms[2][6].populateReavers();
		rooms[2][6].populateReavers();
		rooms[2][6].populateExploders();
		rooms[2][6].populateWeapons("grenades");
		rooms[1][6] = new Environment();
		rooms[1][6].populateArmor();
		rooms[1][6].populateMedPacks(3);
		rooms[1][6].populateMedPacks(3);
		rooms[1][5] = new Environment();
		rooms[1][5].populateFinalBoss();
	}
	public void populateBehemoths(){ //Spawns behemoths
		enemiesInRoom.add(new Behemoths());
	}
	public void populateExploders(){ //Spawns exploders
		enemiesInRoom.add(new Exploders());
	}
	public void populateNWA(){ //Spawns NWA
		enemiesInRoom.add(new NWA());
	}
	public void populateReavers(){ //Spawns reavers
		enemiesInRoom.add(new Reavers());
	}
	public void populateSlashers(){ //Spawns Slashers
		enemiesInRoom.add(new Slashers());
	}
	public void populateSpitters(){ //Spawns Spitters
		enemiesInRoom.add(new Spitters());
	}
	public void populateWeapons(String weaponName){ //Spawns weapons @param weaponName
		itemsInRoom.add(new Weapons(weaponName));
	}
	public void populateMedPacks(int medPackSize){ //Spawns medPacks @param medPackSize
		itemsInRoom.add(new MedPacks(medPackSize));
	}
	public void populateKeys(){ //Spawns keys. Should only be called twice
		itemsInRoom.add(new Keys());
	}
	public void populateArmor(){ //Spawns armor. Should only be called once
		itemsInRoom.add(new Armor());
	}
	public void populateWestBoss(){ //Spawns the West Boss
		enemiesInRoom.add(new WestBoss());
		enemiesInRoom.add(new Slashers());
		enemiesInRoom.add(new Slashers());
	}
	public void populateEastBoss(){ //Spawns the East Boss
		enemiesInRoom.add(new EastBoss());
	}
	public void populateFinalBoss(){ //Spawns the Final Boss. Good luck.
		enemiesInRoom.add(new FinalBoss());
	}
	public ArrayList<Items> getItemsInRoom(){ //Getter
		if(itemsInRoom.equals(null))
			itemsInRoom.add(null);
		return itemsInRoom;
	}
	public ArrayList<NPC> getEnemiesInRoom(){ //Getter
		if(enemiesInRoom.equals(null))
			enemiesInRoom.add(null);
		return enemiesInRoom;
	}
	public Environment getRoom(int locationR, int locationC){
		if(rooms[locationR][locationC] != null){
			return rooms[locationR][locationC];
		}
		else
			return null;
	}
}