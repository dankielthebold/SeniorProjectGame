/* Daniel Caruso
 * MedPacks.java
 * 2/21/15
 * */
public class MedPacks extends Items{
	private int amountHealed, sizeOfHeal; //itemID used to find location in MainCharacter.inventory. i.e. MainCharacter.inventory[getItemID]
	private MainCharacter player;
	public MedPacks(){
		super();
		amountHealed = 0;
		sizeOfHeal = 0;
	}
	public MedPacks(int sizeOfHeal){ //Constructs MedPack objects of various sizes
		super();
		this.sizeOfHeal = sizeOfHeal;
		switch (sizeOfHeal){
			case 1: amountHealed = 3;
						  itemID = 0;
						  itemName = "Small Medical Pack";
				break;
			case 2: amountHealed = 6;
						   itemID = 1;
						   itemName = "Medium Medical Pack";
				break;
			case 3: amountHealed = 9;
						  itemID = 2;
						  itemName = "Large Medical Pack";
				break;
			default: amountHealed = 0; //Should be a non-issue and never used
					 itemID = -1;
					 itemName = null;
				break;
		}
	}
	public int getAmountHealed(){
		return amountHealed;
	}
	/*public String getSizeName(){
		return sizeName;
	}*/
	public int useItem(MedPacks healItem){ //Uses a MedPack object to increase MainCharacter.currentPlayerHealth
		int newHealth = 0;
		player = MainCharacter.playerCharacter;
		if(healItem.getQuantity() == 0) //If you're all out, tough shit
			System.out.println("You're all out!");
		//Move forward the command process
		else{
			if(player.getCurrentPlayerHealth() + healItem.getAmountHealed() > player.getTotalPlayerHealth()) //Makes sure you can't exceed your maximum health
				newHealth = player.getTotalPlayerHealth(); 
			else
				newHealth = player.getCurrentPlayerHealth() + healItem.getAmountHealed(); //Regularly adds more health
			healItem.quantityDecrement(1);
		}
		return newHealth;
	}
}
