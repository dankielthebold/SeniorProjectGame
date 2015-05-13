/* Daniel Caruso
 * Items.java
 * 2/21/15
 * */
public class Items extends Environment{
	protected int quantity, itemID;
	protected String itemName;
	public Items(){
		quantity = 0;
		itemID = -1;
		itemName = "null";
	}
	public int getQuantity(){ //Getter
		return quantity;
	}
	public void quantityIncrement(int change){ //Increase quantity. Needed for medpacks
		quantity+=change;
	}
	public void quantityDecrement(int change){ //Decrease quantity. Needed for medpacks
		quantity-=change;
	}
	public int getItemID(){ //Getter
		return itemID;
	}
	public String getItemName(){ //Getter
		return itemName;
	}
}
