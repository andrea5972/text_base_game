package comp730;

import java.awt.List;
import java.util.ArrayList;

public class PlayerInventory {
 private static PlayerInventory INSTANCE;
	 
	 public static PlayerInventory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlayerInventory();
        }
         
        return INSTANCE;
        } 
	 private  ArrayList<Item> backpack = new ArrayList<Item>();
	 public void additem(Item item) 
	    {
		 	backpack.add(item);
	    	
	    	
	    }
	    public void removeitem(Item item) 
	    {
	    	if(item.concheck() && item.charcheck() == 0) 
	    	{backpack.remove( item);}
	    	
	    }
	    
	    public String printinven() {
	    	String hold = null;
	    	for(Item item : backpack) 
	    	{
	    		hold = hold + item.info() + System.lineSeparator();
	    	}
	    	return hold;
	    } 
	    public String use(Item item) 
	    {
	    	String hold = item.use();
	    	removeitem(item);
	    	return hold;
	    }

}
