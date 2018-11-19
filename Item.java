package comp730;

public class Item {
	private boolean isconsumable;
	private boolean isunstopable;
	private boolean isheal;
	private boolean isnoncombat;
	private String name;
	private String desc;
	private String message;
	private int power;
	private int charges;
	
	public Item(boolean isconsumable, boolean isunstopable, boolean isheal, boolean isnoncombat, String name,
			String desc, String message, int power, int charges) {
		this.isconsumable = isconsumable;
		this.isunstopable = isunstopable;
		this.isheal = isheal;
		this.isnoncombat = isnoncombat;
		this.name = name;
		this.desc = desc;
		this.message = message;
		this.power = power;
		this.charges = charges;
	} 
	
	public String use()
	{
		if(isconsumable) 
		{
			charges = charges -1;
			
		}
		if(isunstopable || isnoncombat ) 
		{
			return message;
		}
		
		if(isheal) 
		{
			return power + " health restored";
		}
		return"";
	}
	public boolean concheck() {
		return isconsumable;
	}
	public boolean healcheck() {
		return isheal;
	}
	public boolean noncomcheck() {
		return isnoncombat;
	}
	public boolean stopcheck() {
		return isunstopable;
	}
	public int charcheck() {
		return charges;
	}
	public String info() 
	{
		return name + " " + desc +" "+ power;
	}

}
