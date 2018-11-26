/**
 * Zantar the main character in this game.
 */

package zantar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Zantar {

	private static Zantar INSTANCE;
	private List<String> zantar;
	private Backpack backpack;
	private int xCoord = 0;
	private int yCoord = 0;
	private int health = 20;
	private int index = -1;
	
	private Zantar() {
		zantar = new ArrayList<>();
		backpack = Backpack.getInstance();
	}
	
	public static Zantar getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Zantar();
		return INSTANCE;
	}
	
	public boolean move(String word) {
		//System.out.println(word);
		if (word.toLowerCase().equals("north")) {
			zantar.add(word);
			yCoord++;
			System.out.println("Zantar moved north. xCoord: " + xCoord + ", yCoord: " + yCoord + "\n");
			return true;
		}
		else if (word.toLowerCase().equals("south")) {
			zantar.add(word);
			yCoord--;
			System.out.println("Zantar moved south. xCoord: " + xCoord + ", yCoord: " + yCoord + "\n");
			return true;
		}
		else if (word.toLowerCase().equals("east")) {
			zantar.add(word);
			xCoord++;
			System.out.println("Zantar moved east. xCoord: " + xCoord + ", yCoord: " + yCoord + "\n");
			return true;
		}
		else if (word.toLowerCase().equals("west")) {
			zantar.add(word);
			xCoord--;
			System.out.println("Zantar moved west. xCoord: " + xCoord + ", yCoord: " + yCoord + "\n");
			return true;
		}
		else {
			System.out.println("Zantar could not go that way. xCoord: " + xCoord + ", yCoord: " + yCoord + "\n");
			return false;
		}
	}
	
	public void undoMove(String word) {
		zantar.remove(word);
		if (word == "north") {
			yCoord--;
			System.out.println("Zantar moved south. xCoord: " + xCoord + ", yCoord: " + yCoord);
		}
		else if (word == "south") {
			yCoord++;
			System.out.println("Zantar moved north. xCoord: " + xCoord + ", yCoord: " + yCoord);
		}
		else if (word == "east") {
			xCoord--;
			System.out.println("Zantar moved west. xCoord: " + xCoord + ", yCoord: " + yCoord);
		}
		else if (word == "west") {
			xCoord++;
			System.out.println("Zantar moved east. xCoord: " + xCoord + ", yCoord: " + yCoord);
		}
	}
	
	public void clearList() { 
		zantar.clear();
	}
	
	public void printXY() {
		System.out.println("Zantar's position is: xCoord: " + xCoord + ", yCoord: " + yCoord);
	}
	
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void takeDamage(int enemyAttack) {
		// TODO Auto-generated method stub
		
	}

	public Object getPouch() {
		// TODO Auto-generated method stub
		return null;
	}

	public int health() {
		// TODO Auto-generated method stub
		return health;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void increaseEnemiesKilled() {
		// TODO Auto-generated method stub
		
	}

	public String enemiesKilled() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeCoins(int penanceForCowardliness) {
		// TODO Auto-generated method stub
		
	}

	public Backpack getBackpack() {
		// TODO Auto-generated method stub
		return backpack;
	}

}
