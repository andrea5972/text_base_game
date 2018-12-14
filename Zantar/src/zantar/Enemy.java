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
	private static final List<String> ENEMY_NAMES = Arrays.asList(
			"Imp", "Slime", "Troll", "Chipmunk", "Zombie", "Rabbit", "Warrior", "Goblin");
	private static final List<Integer> ATTACK_STRENGTH = Arrays.asList(
			20, 15, 25, 10, 20, 10, 25, 20);
	private static final List<Double> HIT_PROBABILITY = Arrays.asList(
			0.4, 0.25, 0.55, 0.2, 0.4, 0.2, 0.55, 0.4);
	private static final List<String> ITEM_WEAKNESS = Arrays.asList(
			"sword", "sword", "axe", "dagger", "sword", "stick", "axe", "sword");
	
	
	public static final int max_health = 100;
	
	public static final int min_health = 1;
	
	private int health;
	private final String name;
	private final String weakness;
	private final int max_attack_damage;
	private final double hit_prob;
	
	public Enemy() {
		int r = RANDOM.nextInt(8);
		name = ENEMY_NAMES.get(r);
		max_attack_damage = ATTACK_STRENGTH.get(r);
		weakness = ITEM_WEAKNESS.get(r);
		hit_prob = HIT_PROBABILITY.get(r);
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
		if (health < 0)
			health = 0;
		System.out.println(name + " lost " + zantarAttack + " HP! " + 
			name + " has " + health + " left!");
	}
	
	public double getHitProbability() {
		return hit_prob;
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
