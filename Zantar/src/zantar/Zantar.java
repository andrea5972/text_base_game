/**
 * Zantar the main character in this game.
 */

package zantar;

import java.util.ArrayList;
import java.util.List;

public class Zantar {

	private static Zantar INSTANCE;
	
	private List<String> zantar;
	private Backpack backpack;
	private int xCoord = 0;
	private int yCoord = 0;
	private int health = 100;
	private int index = -1;
	private int attack_strength = 10;
	private double attackProbility = 0.5;
	private String[] enemiesKilled;
	private int numEnemiesKilled = 0;
	
	private boolean itemEquiped = false;
	private Item item;
	
	private Zantar() {
		zantar = new ArrayList<>();
		backpack = Backpack.getInstance();
		enemiesKilled = new String[4];
	}
	
	public static Zantar getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Zantar();
		return INSTANCE;
	}
	
	public boolean move(String word) {
		if (word.equals("north")) {
			if (yCoord == Map.getInstance().NORTH_BOUNDARY) {
				System.out.println("Zantar cannot proceed further north in this world!");
				return false;
			}
			zantar.add(word);
			index++;
			yCoord++;
			System.out.println("Zantar moved north.");
			return true;
		} else if (word.equals("south")) {
			if (yCoord == Map.getInstance().SOUTH_BOUNDARY) {
				System.out.println("Zantar cannot proceed further south in this world!");
				return false;
			}
			zantar.add(word);
			index++;
			yCoord--;
			System.out.println("Zantar moved south.");
			return true;
		} else if (word.equals("east")) {
			if (yCoord == Map.getInstance().EAST_BOUNDARY) {
				System.out.println("Zantar cannot proceed further east in this world!");
				return false;
			}
			zantar.add(word);
			index++;
			xCoord++;
			System.out.println("Zantar moved east.");
			return true;
		} else if (word.equals("west")) {
			if (yCoord == Map.getInstance().WEST_BOUNDARY) {
				System.out.println("Zantar cannot proceed further west in this world!");
				return false;
			}
			zantar.add(word);
			index++;
			xCoord--;
			System.out.println("Zantar moved west.");
			return true;
		} else if (word.equals("undo")) {
			return undoMove();
		} else {
			System.out.println("Zantar could not go that way!");
			return false;
		}
	}
	
	public boolean undoMove() {
		if (index < 0) {
			System.out.println("No more moves left to undo!");
			return false;
		}
		String word = zantar.remove(index);
		index--;
		if (word.equals("north")) {
			yCoord--;
			System.out.println("Zantar moved south.");
		}
		else if (word.equals("south")) {
			yCoord++;
			System.out.println("Zantar moved north.");
		}
		else if (word.equals("east")) {
			xCoord--;
			System.out.println("Zantar moved west.");
		}
		else if (word.equals("west")) {
			xCoord++;
			System.out.println("Zantar moved east.");
		}
		return true;
	}
	
	public int getX() {
		return xCoord;
	}
	
	public int getY() {
		return yCoord;
	}
	
	public void clearList() { 
		zantar.clear();
	}
	
	public void printXY() {
		System.out.println(String.format("Zantar's position is (%s, %s)", xCoord, yCoord));
	}
	
	public int attack() {
		return attack_strength;
	}

	public void takeDamage(int enemyAttack) {
		health -= enemyAttack;	
		System.out.println("Zantar lost " + enemyAttack + " HP! Zantar has " + health + " left!");
	}

	public int health() {
		return health;
	}

	public void runAway() {
		xCoord = 0;
		yCoord = 0;
		zantar.clear();
		index = -1;
	}

	public void increaseEnemiesKilled(String enemy) {
		enemiesKilled[numEnemiesKilled] = enemy;
		numEnemiesKilled++;
	}

	public String enemiesKilled() {
		return String.join(", ", enemiesKilled);
	}
	
	public int getNumEnemiesKilled() {
		return numEnemiesKilled;
	}

	public void removeCoins(int coins) {
		Backpack.getInstance().removeCoins(coins);
	}

	public Backpack getBackpack() {
		return backpack;
	}
	
	public double getAttackProbabilityForEnemy(Enemy e) {
		if (itemEquiped) {
			return item.getHitProbability();
		}
		return attackProbility;
	}
	
	public void setAttackProbability(double p) {
		this.attackProbility = p;
	}
	
	public void equipItem(Item i) {
		this.item = i;
		this.itemEquiped = true;
		item.equip();
	}
	
	public Item getEquipedItem() {
		return this.item;
	}
	
	public void UnEquipItem() {
		this.item = null;
		itemEquiped = false;
	}
	
	public void setAttackStrength(int attackStrength) {
		this.attack_strength = attackStrength;
	}
	
	public boolean isItemEquiped() {
		return itemEquiped;
	}
	
	public boolean isAlive() {
		return health > 0;
	}

}
