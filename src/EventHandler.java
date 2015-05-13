/* Daniel Caruso
 * EventHandler.java
 * 3/1/15
 * */
import java.util.*;
public class EventHandler extends MainClass{
	//Probably not needed, but we'll see
	//int[] locations = {MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC(), MainCharacter.playerCharacter.getLocationR()+1, MainCharacter.playerCharacter.getLocationR()-1, MainCharacter.playerCharacter.getLocationC()+1, MainCharacter.playerCharacter.getLocationC()-1};
	//List<Integer> possibleLocations;
	private ArrayList<NPC> currentEnemies;
	private String playerResponse; 
	private boolean canMove = true;
	int optionNumber;
	Scanner input = new Scanner(System.in);
	public void takePlayerResponse(){
		System.out.println("What would you like to do?");
		playerResponse = input.next();
	}
	public void printMainOptions(){ //Generates main options
		System.out.println("1) Move"); //User responds with a String literal containing a number 0-4.
		System.out.println("2) Use"); //Whichever option that number corresponds with, the next menu or action will occur
		System.out.println("3) Attack"); 
		System.out.println("4) Examine");
		System.out.println("5) Pick up");
		System.out.println("0) Quit"); //Loser
		takePlayerResponse();
		processMainOptions();
	}
	public void processMainOptions(){ //Processes response for main options
		if(playerResponse.equals("0")){ //Handles quitters. Losers.
			System.out.println("Are you sure you want to quit? Y/N?");
			playerResponse = input.next();
			if(playerResponse.toLowerCase().equals("y")){
				System.out.println("You quit.");
				System.exit(0);
			}
			else if(playerResponse.toLowerCase().equals("n")){
				System.out.println("Good choice.");
				takePlayerResponse();
				processMainOptions();
			}
			else{
				System.out.println("That's not an option! Try Again.");
				takePlayerResponse();
				processMainOptions();
			}
		} 
		else{ //Parses options, generate appropriate lists, which in turn will call the appropriate methods
			if(playerResponse.equals("1")){
				generateMoveOptions();
			}
			else if(playerResponse.equals("2")){
				generateUseOptions();
			}
			else if(playerResponse.equals("3")){
				generateAttackOptions();
			}
			else if(playerResponse.equals("4")){
				examineAction();
			}
			else if(playerResponse.equals("5")){
				pickUpAction();
			}
			else{
				System.out.println("That's not an option! Try again."); //For the morons who can't count
				takePlayerResponse();
				processMainOptions();
			}
		}
	}
	public void generateMoveOptions(){ //Generate options to move
		if(MainCharacter.playerCharacter.getRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()).getEnemiesInRoom().size() != 0)
			canMove = false; //Player can only move if there are no more monsters in the room
		optionNumber = 1; //To format print statements
		if(isRoom(MainCharacter.playerCharacter.getLocationR()-1, MainCharacter.playerCharacter.getLocationC())){ //isRoom() method returns boolean, parameters will be playerCharchter.getLocation();
			System.out.println(optionNumber + ") North");
			optionNumber++;
		}
		if(isRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()-1)){
			System.out.println(optionNumber + ") West");
			optionNumber++;
		}
		if(isRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()+1)){
			System.out.println(optionNumber + ") East");
			optionNumber++;
		}
		if(isRoom(MainCharacter.playerCharacter.getLocationR()+1, MainCharacter.playerCharacter.getLocationC())){ 
			System.out.println(optionNumber + ") South");
		}
		takePlayerResponse();
		processMoveOptions();
	}
	public void processMoveOptions(){ //Carry out action of moving. Also updates currentEnemies AL for each room
		if(!canMove){
			System.out.println("There are too many enemies in this room, you must clear them out before proceeding.");
			generateAttackOptions();
		}
		else if(playerResponse.equals("1")){
			MainCharacter.playerCharacter.moveCharacter(MainCharacter.playerCharacter.getLocationR()-1, MainCharacter.playerCharacter.getLocationC());
			currentEnemies = MainCharacter.playerCharacter.getRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()).getEnemiesInRoom();
			printMainOptions();
		}
		else if(playerResponse.equals("2")){
			MainCharacter.playerCharacter.moveCharacter(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()-1);
			currentEnemies = MainCharacter.playerCharacter.getRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()).getEnemiesInRoom();
			printMainOptions();
		}
		else if(playerResponse.equals("3")){
			MainCharacter.playerCharacter.moveCharacter(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()+1);
			currentEnemies = MainCharacter.playerCharacter.getRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()).getEnemiesInRoom();
			printMainOptions();
		}
		else if(playerResponse.equals("4")){
			MainCharacter.playerCharacter.moveCharacter(MainCharacter.playerCharacter.getLocationR()+1, MainCharacter.playerCharacter.getLocationC());
			currentEnemies = MainCharacter.playerCharacter.getRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()).getEnemiesInRoom();
			printMainOptions();
		}
		else{
			System.out.println("That's not an option! Try again.");
			takePlayerResponse();
			processMoveOptions(); //Recursive only if else state is triggered. Shouldn't cause any issues
		}
	}
	public void generateUseOptions(){ //Generate options to use
		System.out.println("1) Heal");
		System.out.println("2) Weapons");
		System.out.println("3) Armor");
		takePlayerResponse();
		processUseOptions();
	}
	public void processUseOptions(){
		if(playerResponse.equals("1")){
			System.out.println("1) Small");
			System.out.println("2) Medium");
			System.out.println("3) Large");
			takePlayerResponse();
			if(Integer.parseInt(playerResponse) > 0 && Integer.parseInt(playerResponse) < 4) //Makes sure the response entered works
				MainCharacter.playerCharacter.heal(Integer.parseInt(playerResponse)); //Carries out the actual heal
			else{
				System.out.println("That's not an option! Try again.");
				playerResponse = "1"; //Resets options for medpack size. medpack size list is within processUseOptions so it must be reset differently
				processUseOptions();
			}
		}
		else if(playerResponse.equals("2")){
			System.out.println("Which weapon would you like to equip?");
			for(int i = 2; i < 11; i++){ //list out all the weapons, if you have it equip it, if you don't then say you don't have it
				if(MainCharacter.playerCharacter.getItemInInventory(i).getQuantity() != 0){
					System.out.println("Weapon: " + i+1 + MainCharacter.playerCharacter.getItemInInventory(i).getItemName()); //i+1 may need to be just i, the itemID nonsense is screwing with me. 
				}
			}
			System.out.println("Which would you like to equip?");
			playerResponse = input.next();
			if(Integer.parseInt(playerResponse) > 0 && Integer.parseInt(playerResponse) < 13)
				MainCharacter.playerCharacter.equipWeapon(Integer.parseInt(playerResponse));
			else{
				System.out.println("That's not an option! Try again.");
				playerResponse = "2"; //Resets options for weapon choice. weapon choice list is within processUseOptions so it must be reset differently
				processUseOptions();
			}
		}
		else if(playerResponse.equals("3")){
			Armor.activateArmor();
			MainCharacter.playerCharacter.checkForArmor();
		}
		else{
			System.out.println("That's not an option! Try again.");
			takePlayerResponse();
			processUseOptions(); 
		}
	}
	public void generateAttackOptions(){ //Generate options to attack
		//int numberOfEnemies =  
		currentEnemies = MainCharacter.playerCharacter.getRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()).getEnemiesInRoom();
		if(currentEnemies.size() == 0){
			System.out.println("There's nothing to fight. \n");
			printMainOptions();
		}
		else{
			System.out.println("Which enemy would you like to attack?");
			for(int i = 0; i < currentEnemies.size(); i++){
				System.out.println(i+1 + ")" + currentEnemies.get(i).getName() + " Health: " + currentEnemies.get(i).getHealth());
			}
			playerResponse = input.next();
			processAttackOptions();
		}
	}
	public void processAttackOptions(){ //Carries out attacks for the player
		int newNPCHealth = -1;
		if(Integer.parseInt(playerResponse) < 0 || Integer.parseInt(playerResponse) > currentEnemies.size()){ //If playerResponse is not in the list of target NPCs
			System.out.println("That is not an option! Try again");
			playerResponse = input.next();
			processAttackOptions();
		}
		else{ //Attacks enemy, enemy attacks back
			newNPCHealth = MainCharacter.playerCharacter.attackNPC(currentEnemies.get(Integer.parseInt(playerResponse)), MainCharacter.playerCharacter.getEquippedWeapon().getWeaponDamage()); //Actual attack is carried out
			System.out.println("You do " + MainCharacter.playerCharacter.getEquippedWeapon().getWeaponDamage() + " damage to " + currentEnemies.get(Integer.parseInt(playerResponse)).getName() + ".");
			if(newNPCHealth == 0){ //Removes enemy from list if they die
				MainCharacter.playerCharacter.getRoom(MainCharacter.playerCharacter.getLocationR(), MainCharacter.playerCharacter.getLocationC()).getEnemiesInRoom().remove(Integer.parseInt(playerResponse));
				System.out.println("You killed " + currentEnemies.get(Integer.parseInt(playerResponse)).getName() + ".");
			}
			else{ //NPC attacks back
				NPCAttack(currentEnemies.get(Integer.parseInt(playerResponse)));
				System.out.println("The " + currentEnemies.get(Integer.parseInt(playerResponse)).getName() + " deals " + currentEnemies.get(Integer.parseInt(playerResponse)).getAttackDamage() + " to you.");
			}
			if(currentEnemies.size() != 0) //Continue attacking
				generateAttackOptions();
			else{ //All done with the NPC in the room
				System.out.println("There's nothing left to fight.");
				printMainOptions();
			}
		}
	}
	public void examineAction(){ //No need for another list
		MainCharacter.playerCharacter.examineRoom();
		printMainOptions();
	}
	public void pickUpAction(){ //Picks up items. Generate and processes the action
		if(MainCharacter.playerCharacter.examineRoom()){
			System.out.println("What would you like to pick up?");
			playerResponse = input.next();
			MainCharacter.playerCharacter.pickUpItem(Integer.parseInt(playerResponse) - 1);
			printMainOptions();
			processMainOptions();
		}
		else{
			System.out.println("There's nothing to pick up.");
			printMainOptions();
			processMainOptions();
		}
	}
	public void NPCAttack(NPC fighter){ //@param will be the last enemy attacked by the player
		int damageDone = fighter.attackPlayer();
		System.out.println(fighter.getName() + " does " + damageDone + " damage.");
	}
	public boolean isRoom(int locationR, int locationC){
		if(MainCharacter.playerCharacter.getRoom(locationR, locationC) == null)
			return false;
		else
			return true;
	} 
}
