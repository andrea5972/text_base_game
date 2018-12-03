/**
 * The antagonists whom you must defeat
 * in order to save the world
 */

package zantar;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Enemy {
	public static final Random RANDOM = new Random(); 
	
	// An array of possible enemy types
	public static final List<String> ENEMY_NAMES = Arrays.asList(
			"Imp", "Slime", "Troll", "Chipmunk", "Zombie", "Rabbit", "Warrior", "Goblin");
	public static final List<String> ITEM_WEAKNESS = Arrays.asList(
			"sword", "sword", "axe", "dagger", "sword", "stick", "axe", "sword");
	
	public static final int max_attack_damage = 20;
	
	public static final int max_health = 100;
	
	public static final int min_health = 1;
	
	private int health;
	private final String name;
	private final String weakness;
	
	public Enemy() {
		int r = RANDOM.nextInt(8);
		name = ENEMY_NAMES.get(r);
		weakness = ITEM_WEAKNESS.get(r);
		health = max_health;
	}

	public int attack() {
		return max_attack_damage;
	}

	public int health() {
		return health;
	}

	public void takeDamage(int zantarAttack) {
		health -= zantarAttack;		
	}

	public String name() {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
	}
	
	public boolean weakness(String item) {
		return weakness.equals(item);
	}
	
	public boolean isAlive() {
		return health >= min_health; 
	}

}
