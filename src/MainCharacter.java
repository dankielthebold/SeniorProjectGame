/* Daniel Caruso
 * MainCharacter.java
 * 2/3/2015
 * */
import java.util.*;
public class MainCharacter extends Environment{
	private int currentPlayerHealth, totalPlayerHealth, currentExperience, experienceNeeded, level, locationR, locationC, keyCount;
	private double accuracy;
	private Items[] inventory = new Items[13]; //Includes weapons, keys, and med packs
	/* Item id's:
	 * Small MedPack = 0
	 * Medium MedPack = 1
	 * Large MedPack = 2
	 * Rifle = 3
	 * Uzi = 4
	 * Shotgun = 5
	 * PlasmaRifle = 6
	 * Sniper = 7
	 * RocketLauncher = 8
	 * Grenades = 9
	 * Armor = 10
	 * L. Key = 11
	 * R. Key = 12
	 * O.P. = 13
	 * */
	private Weapons[] equippedWeapon = new Weapons[1]; //One is a weapon, other is armor
	/* equippedItems[0] is weapon
	 * equippedItems[1] is armor
	 * */
	private NPC enemyObj; //NPC.java object. Can be initialized for appropriate mob
	private MedPacks healItem;
	private Weapons playerWeapon;
	public static MainCharacter playerCharacter = new MainCharacter();
	public MainCharacter(){ //Default constructor
		totalPlayerHealth = 10;
		currentPlayerHealth = 10;
		currentExperience = 0;
		experienceNeeded = 10;
		accuracy = 0.7;
		level = 1;
		locationR = 6;
		locationC = 5;
		keyCount = 0;
	}
	public void levelUp(){ //Levels up character
		totalPlayerHealth+=2;
		currentExperience = 0;
		experienceNeeded+=2;
		accuracy+=0.05;
		level++;
	}
	public boolean hitAttempt(double playerAccuracy){ //Checks if hit goes through for player, @param MainCharacter.accuracy
		double shotRoll = Math.random();
		if(shotRoll <= playerAccuracy)
			return true;
		else
			return false;
	}
	public int attackNPC(NPC target, int playerAttackDamage){ //Deals damage to target NPC, @param enemyObj, @param playerWeapon.getWeaponDamage()
		int remainingHealth = playerAttackDamage; //Honestly what was I thinking with this line?
		if(playerAttackDamage-target.getArmor() <= 0)
			remainingHealth = target.getHealth();
		else 
			remainingHealth = target.getHealth() - playerAttackDamage;
		return remainingHealth; //Where will this be stored? attackHandler() in EventHandler maybe?
	}
	public int heal(int sizeOfHeal){ //Heal player
		healItem = new MedPacks(sizeOfHeal); //Initializes MedPacks object healItem
		return healItem.useItem(healItem); //Uses healItem to call useItem(Medpacks sizeOfHeal) with itself as the parameter. A weird solution, will get back to later
	}
	public void equipWeapon(int itemID){ //Equip a new weapon from inventory
		if(inventory[itemID].getQuantity() == 0){
			System.out.println("You don't have that item!"); //Just in case. Shouldn't be a problem though
		}
		else{
			if(equippedWeapon[0].getItemID() == itemID)
				System.out.println("You already have that equipped!"); //Ya dingus
			else
				equippedWeapon[0] = new Weapons(inventory[itemID].getItemName()); //equippedItems[0] is initialized as a new Weapons object with @param inventory[itemID].getItem()
		}
	}
	public void moveCharacter(int newRow, int newColumn){ //Moves main character from place to place. 
		if(newRow == 0 && newColumn == 4){ //Checks to see if next location is boss room
			if(keyCount != 2){ //Checks to see if you have keys
				System.out.println("The door is locked. There are 2 key holes in a large padlock over the handle.");
			}
			else{ //Move into the boss room
				System.out.println("You put both keys into the lock. It turns with a heavy clunk. You enter into the next room.");
				locationR = 0;
				locationC = 4;
			}
		}
		else if(isRoom(newRow, newColumn)){ //Move if the room is for real for real
			locationR = newRow;
			locationC = newColumn;
		}
	}
	public boolean examineRoom(){ //Returns any items that can be found in the room
		System.out.print("In the room you find: \n");
		if(Environment.rooms[locationR][locationC].getItemsInRoom().size() == 0){
			System.out.println("nothing.");
			return false;
		}
		else{
			for(int i = 0; i < rooms[locationR][locationC].getItemsInRoom().size(); i++){
				System.out.println(i+1 + ") " + MainCharacter.playerCharacter.getRoom(locationR, locationC).getItemsInRoom().get(i).getItemName());  //Hwhat a doozy. You should see the lines in EventHandler.java
			}
			System.out.println("\n");
			return true;
		}
	}
	public ArrayList<Items> examineRoomList(){ 
		return MainCharacter.playerCharacter.getRoom(locationR, locationC).getItemsInRoom();
	}
	public void pickUpItem(int itemToGrab){ //Picks up an item from the ground
		inventory[rooms[getLocationR()][getLocationC()].getItemsInRoom().get(itemToGrab).getItemID()] = new Weapons(rooms[getLocationR()][getLocationC()].getItemsInRoom().get(itemToGrab).getItemName());
		System.out.println("You pick up the " + inventory[rooms[getLocationR()][getLocationC()].getItemsInRoom().get(itemToGrab).getItemID()].getItemName());
	}
	public boolean checkForArmor(){ //What's the point of this?
		return Armor.getHasArmor();	
	}
	//Bunch of getters, self-explanatory. I hope.
	public int getCurrentPlayerHealth(){
		return currentPlayerHealth;
	}
	public int getTotalPlayerHealth(){
		return totalPlayerHealth;
	}
	public String getHealthStatus(){
		return "Health: " + currentPlayerHealth + "/" + totalPlayerHealth;
	}
	public String getExperienceStatus(){
		return "Experience: " + currentExperience + "/" + experienceNeeded;
	}
	public double getAccuracy(){
		return accuracy;
	}	
	public int getLocationR(){
		return locationR;
	}
	public int getLocationC(){
		return locationC;
	}
	public Items[] getInventory(){
		return inventory;
	}
	public Items getItemInInventory(int itemID){
		return inventory[itemID];
	}
	public Weapons getEquippedWeapon(){
		return equippedWeapon[0];
	}
}