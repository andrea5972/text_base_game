/**
 * The antagonists whom you must defeat
 * in order to save the world
 */

package zantar;

import java.util.Random;

public class Enemy {
	public static final Random RANDOM = new Random(); 
	
   // An array of possible enemy types
	public static final String[] ENEMY_NAMES = { "Troll", "Chipmunk", "Zombie", "Rabit", "Warrior", "Goblin" };

    public static final int max_attack_damage = 20;
   
    public static final int max_health = 75;

    public static final int min_health = 1;

    private int health;
    private String name;

	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int health() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void takeDamage(int zantarAttack) {
		// TODO Auto-generated method stub
		
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
