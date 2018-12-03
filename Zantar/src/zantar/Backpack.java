/**
 * Backpack class
 * stores the gold coins for Zantar
 */


package zantar;

import java.util.ArrayList;
import java.util.List;

public class Backpack{
	
	private static Backpack INSTANCE;
	private List<String> backpack;
	private int health = 20;
	private int coins = 30;
	
	private final List<Item> itemList;
	
	private Backpack() {
		backpack = new ArrayList<>();
		itemList = new ArrayList<>();
	}
	
	public static Backpack getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Backpack();
		return INSTANCE;
	}

    public int getCoins()
    {
        return coins;
    }

    public void addCoins(int coins)
    {
        this.coins += coins;
    }

    public void removeCoins(int coins)
    {
        this.coins -= coins;
    }

    public void setCoins(int coins)
    {
        this.coins = coins;
    }
    
    public void addItem(Item i) {
    	itemList.add(i);
    }
    
    public void printBackPack() {
    	System.out.println("Currently your backpack contains:");
    	System.out.println("Gold: " + coins);
    	for (Item item : itemList) {
    		System.out.println(item.toString());
    	}
    }
}
