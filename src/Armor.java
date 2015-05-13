/* Daniel Caruso
 * Armor.java
 * 2/23/15
 */
public class Armor extends Items{
	private int armorValue = 2; //May not need this
	private static boolean hasArmor;
	//Armor playerArmor = new Armor(); //For some reason this line causes a stack overflow at launch
	public Armor(){ //Constructs a new armor object. Should only be called once in room [0][5]
		armorValue = 2;
		hasArmor = false;
		itemName = "Armor";
	}
	public static void activateArmor(){ //Puts armor on and off. For the people who want to make this game even harder.
		hasArmor = !hasArmor;
	}
	public static boolean getHasArmor(){
		return hasArmor;
	}
}
