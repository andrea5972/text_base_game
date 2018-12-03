/*
 * Author: Travis
 * Modified By: Raghava
 */

package zantar;

public class Item {

	private final String name;
	private final int power;
	private final boolean isconsumable;
	private final double hitProbability;
	private final double weaknessOfVillianHitProbability;
	
	private boolean isunstopable = false;
	private boolean isheal = false;
	private boolean isnoncombat = false;
	private String desc;
	private String message;
	private boolean equiped = false;
	
	private int charges;
	
	public Item(String name, int power, boolean isConsumable, int charges, double hitProbability,
			double weaknessOfVillianHitProbability) {
		this.name = name;
		this.power = power;
		this.isconsumable = isConsumable;
		if (isConsumable) {
			this.charges = charges;
		}
		this.hitProbability = hitProbability;
		this.weaknessOfVillianHitProbability = weaknessOfVillianHitProbability;
	}

	public Item(boolean isconsumable, boolean isunstopable, boolean isheal, boolean isnoncombat, 
			String name, String desc, String message, int power, int charges, double hitProbability,
			double weaknessOfVillianHitProbability) {
		this.isconsumable = isconsumable;
		this.isunstopable = isunstopable;
		this.isheal = isheal;
		this.isnoncombat = isnoncombat;
		this.name = name;
		this.desc = desc;
		this.message = message;
		this.power = power;
		this.charges = charges;
		this.hitProbability = hitProbability;
		this.weaknessOfVillianHitProbability = weaknessOfVillianHitProbability;
	} 
	
	public String use()
	{
		if(isconsumable) 
		{
			charges = charges - 1;
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
	
	public String info() {
		return name + " " + desc +" "+ power;
	}
	
	public double getHitProbability() {
		return hitProbability;
	}
	
	public double getHitProbabilityForEnemy(Enemy e) {
		if (e.weakness(name)) {
			return weaknessOfVillianHitProbability;
		} else {
			return hitProbability;
		}
	}
	
	public double getWeaknessOfVillianHitProbability() {
		return weaknessOfVillianHitProbability;
	}
	
	public String name() {
		return name;
	}
	
	public String toString() {
		String ret = name + " (Uses left: ";
		ret += charges;
		ret += ", hit probability: ";
		ret += hitProbability;
		ret += ", weakness of villian hit probability: ";
		ret += weaknessOfVillianHitProbability;
		ret += ", Equiped: ";
		ret += Boolean.toString(equiped);
		ret += ")";
		return ret;
	}
	
	public void equip() {
		this.equiped = true;
	}

}