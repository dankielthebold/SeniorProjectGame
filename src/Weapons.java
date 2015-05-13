/* Daniel Caruso
 * Weapons.java
 * 2/21/15
 * Will not have individual weapon objects. Similar idea to MedpPacks.java*/
public class Weapons extends Items{
	private int weaponDamage;
	public Weapons(){ //Default constructor.
		super();
		weaponDamage = 0;
	}
	public Weapons(String weaponName){ //Constructs all weapons of the game
		super();
		this.itemName = weaponName;
		switch (weaponName){
		case "rifle": weaponDamage  = 2;
					  itemID = 3;
					  itemName = "rifle";
					  quantity = 1;
			break;
		case "uzi": weaponDamage = 3;
					itemID = 4;
					itemName = "uzi";
					quantity = 1;
					
			break;
		case "shotgun": weaponDamage = 5;
					    itemID = 5;
					    itemName = "shotgun";
					    quantity = 1;
			break;
		case "plasmarifle": weaponDamage = 4;
							itemID = 6;
							itemName = "plasmarifle";
							quantity = 1;
			break;
		case "sniper": weaponDamage = 5;
					   itemID = 7;
					   itemName = "sniper";
					   quantity = 1;
			break;
		case "rocketlauncher": weaponDamage = 4;
							   itemID = 8;
							   itemName = "rocketlauncher";
							   quantity = 1;
			break;
		case "grenades": weaponDamage = 4;
						 itemID = 9;
						 itemName = "grenades";
						 quantity = 1;
			break;
		case "op": weaponDamage = 40;
				   itemID = 12;
				   itemName = "op";
				   quantity = 1;
			break;
		default: weaponDamage = 0; //Should be a non-issue and never used
				 itemID = -1;
				 itemName = "null";
				 quantity = 0;
			break;
		}
	}
	public int getWeaponDamage(){
		return weaponDamage;
	}
}
