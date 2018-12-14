/**
 * Zantar
 * A SyFi/fantasy text-based adventure game
 */

package zantar;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;


public class Game {
	public static final Random RANDOM = new Random();
	public static final Scanner SCANNER = new Scanner(System.in);

	/** The menu options
	public static final int attack = 1;
	public static final int use_item = 2;
	public static final int run = 3;
	public static final int exit_game = 4;
    */

	// The string which contains all accepted answers to yes or no questions
	private static final String confirmation = "yes no";
	private static final String movements = "north south east west undo";
	private static final String game_commands = "quit help stats";
	private static final String item_commands = "equip backpack";
	private static final String battle_commands = "beg_for_mercy attack";

	/**
	public static final long delay = 2000;

	public static final int max_gold = 30;
	*/

	private static boolean runningGame = false;
	private static boolean ranAway = false;

	public static final long delay = 2000;
	public static final int penance_for_cowardliness = 30;
	public static final int blessings_for_victory = 40;

	private static Enemy villain;

	public static void main(String[] argument) throws FileNotFoundException {

		// Main character
		Zantar zantar = Zantar.getInstance();

		System.out.println("\nWelcome, Zantar on your mighty quest to save the world!\n "
				+ "If you wish to play the game- enter 'start'\n"
				+ "For help and list possible option- enter 'help'\n If you want to quit- enter 'quit'\n");

		showMenuTillStartOrStop();
		if (runningGame) {
			zantar.printXY();
		}

		while (runningGame) {
			String choice = getChoice();

			if (movements.contains(choice)) {
				moveZantar(choice);
			} else if (item_commands.contains(choice.split(" ")[0])) {
				processItemCommands(choice);
			} else {
				System.out.println("Zantar doesn't understand!");
				System.out.println("Enter help to get help.");
			}

		}

		if (!Zantar.getInstance().isAlive()) {
			System.out.println("Zantar failed in his quest to save the world and became one of the"
					+ " many brave souls who gave up their \nlives to save the world! \nBut, there "
					+ "is still hope for the world in warriors like you if you decide to "
					+ "try again.");
		}
	}

	private static void processItemCommands(String choice) {
		if (choice.equals("backpack")) {
			Backpack.getInstance().printBackPack();
		} else if (choice.contains("equip")) {
			if (choice.split(" ").length < 2) {
				System.out.println("Choose an item from backpack to equip!");
				return;
			}
			String itemName = choice.split(" ")[1];
			if (!itemName.isEmpty()) {
				if(Backpack.getInstance().equip(itemName)) {
					System.out.println(itemName + " successfully equiped!");
				} else {
					System.out.println("Equip of " + itemName + " failed! "
							+ "No such item exists in your backpack!");
				}
			} else {
				System.out.println("Zantar doesn't understand!");
			}
		}
	}

	private static void executeGameCommands(String choice) {
		if (choice.equals("quit")) {
			stopGame();
		} else if (choice.equals("help")) {
			showHelp();
		} else if (choice.equals("stats")) {
			printStatistics();
		}
	}

	private static void moveZantar(String choice) {
		Zantar z = Zantar.getInstance();
		z.move(choice);
		z.printXY();

		String locData = Map.getInstance().getLocationData(z.getX(), z.getY());

		if (locData != null) {
			if (locData.equals("enemy")) {
				Enemy enemy = new Enemy();
				villain = enemy;
				EncounterStatus e_status = foundEnemy(enemy);
				if(e_status == EncounterStatus.FIGHT) {
					BattleStatus b_status = startBattle(enemy);
					if (b_status == BattleStatus.ZANTAR_DIES) {
						runningGame = false;
						System.out.println("\nZantar is DEAD!\n");
						return;
					} else if (b_status == BattleStatus.ENEMY_DIES) {
						Zantar.getInstance().increaseEnemiesKilled(enemy.name());
						Backpack.getInstance().addCoins(blessings_for_victory);
						System.out.println("Zantar successfully slayed " + enemy.name() + "!");
						System.out.println("So far, Zantar has slayed " + z.enemiesKilled() + "!");
						System.out.println("Zantar continues on his journey to save the world!");

						Map.getInstance().removeLocationData(z.getX(), z.getY());
					} else if (b_status == BattleStatus.RUNS_AWAY) {
					}
				} else if (e_status == EncounterStatus.RUN_AWAY) {
					runAwayFrom(enemy);
				}

				villain = null;
				Backpack.getInstance().printBackPack();
				Zantar.getInstance().printXY();
			} else {
				if (foundItem(locData)) {
					Map.getInstance().removeLocationData(z.getX(), z.getY());
				}
				Zantar.getInstance().printXY();
			}
		}
	}

	private static boolean foundItem(String itemName) {
		System.out.println(String.format("Zantar found %s. What would you like to do?",
				itemName));
		String choice = getChoice();
		if (choice.equals("pickup")) {
			if (itemName.equals("sword")) {
				Backpack.getInstance().addItem(new Item(itemName, 25, true, 6, 0.65,
						0.75, "slashed"));
				System.out.println("Zantar picked up sword successfully! "
						+ "He placed it in his backpack");
			} else if (itemName.equals("dagger")) {
				Backpack.getInstance().addItem(new Item(itemName, 20, true, 6, 0.7, 0.75,
						"stabbed"));
				System.out.println("Zantar picked up dagger successfully! "
						+ "He placed it in his backpack");
			} else if (itemName.equals("axe")) {
				Backpack.getInstance().addItem(new Item(itemName, 30, true, 6, 0.65, 0.7,
						"chopped at"));
				System.out.println("Zantar picked up axe successfully! "
						+ "He placed it in his backpack");
			} else if (itemName.equals("stick")) {
				Backpack.getInstance().addItem(new Item(itemName, 5, true, 2, 0.6, 0.75,
						"hit"));
				System.out.println("Zantar picked up stick successfully! "
						+ "He placed it in his backpack");
			}
			return true;
		} else if (choice.equals("ignore")) {
			System.out.println("*Zantar ignores " + itemName + " and continues on his journey.");
			return false;
		} else {
			System.out.println("Zantar doesn't understand.");
			return foundItem(itemName);
		}
	}

	private static EncounterStatus foundEnemy(Enemy enemy) {
		System.out.println("Zantar and " + enemy.name() + " are facing each other.");
		System.out.println("Would you like to fight or beg for mercy");
		String choice = getChoice();
		if (choice.equals("fight")) {
			return EncounterStatus.FIGHT;
		} else if ("beg for mercy".contains(choice)) {
			return EncounterStatus.RUN_AWAY;
		} else {
			System.out.println("Zantar doesn't understand.");
			return foundEnemy(enemy);
		}
	}

	private static void runAwayFrom(Enemy enemy) {
		System.out.println("*Zantar starts to kowtow*.\nOh mighty " + enemy + "! "
				+ "Please spare weak little me.");
		System.out.println("*" + enemy + " laughs smugly*\nHAHAHA! Give me "
				+ penance_for_cowardliness + " gold and I shall "
				+ "spare your puny little life!");
		System.out.println("*Zantar quickly takes out " + penance_for_cowardliness
				+ " gold and tosses it over " + "to " + enemy + "*");
		Backpack.getInstance().removeCoins(penance_for_cowardliness);
		System.out.println("*Then Zantar runs away*");
		System.out.println("*Zantar takes a deep breath and thinks 'As long"
				+ " as I have my life, I can go back and kill that " +
				enemy.name() + " anytime!\nI will adventure more and go "
				+ "back to kill "+ enemy.name() + " when I get stronger!'"
				+ "* Let's start over!");

		Zantar.getInstance().runAway();
	}

	// The battle prompt menu
	private static BattleStatus startBattle(Enemy enemy)
	{
		System.out.println("*Zantar yells out*\n'Hey stupid " + enemy.name() +
				"! Come here and let Mighty Zantar put an end to your puny life!");
		System.out.print("*" + enemy.name() + " is enraged and roars* YOU PUNY LITTLE HUMAN, "
				+ "I AM GOING TO KILL YOU!\n*Enemy charges towards Zantar*\n"
				+ "*Zantar charges towards enemy");
		if (Zantar.getInstance().isItemEquiped()) {
			System.out.print(" with " + Zantar.getInstance().getEquipedItem().name() + " in "
					+ "hand.");
		} else {
			System.out.println("!");
		}
		boolean enemyAttacksFirst = RANDOM.nextBoolean();
		if (enemyAttacksFirst) {
			enemyAttacks(enemy);
		}
		while (enemy.isAlive()) {
			System.out.println("Zantar prepares to attack! What will you choose?");
			System.out.println("To attack, enter attack");
			System.out.println("To beg for mercy, enter beg for mercy!");
			String choice = getChoice();
			if (item_commands.contains(choice.split(" ")[0])) {
				processItemCommands(choice);
			} else if (battle_commands.contains(choice.replaceAll(" ", "_"))) {
			} else {
				System.out.println("Zantar doesn't understand!");
				continue;
			}

			if ("beg for mercy".contains(choice)) {
				System.out.println("*Zantar drops all defences!*");
				if (RANDOM.nextBoolean()) {
					runAwayFrom(enemy);
					return BattleStatus.RUNS_AWAY;
				} else {
					System.out.println(enemy.name() + " is too enraged to listen to Zantar's "
							+ "plea for life! " + enemy.name() + " kills the defenceless Zantar!");
					Zantar.getInstance().takeDamage(100);
					return BattleStatus.ZANTAR_DIES;
				}
			} else if (choice.equals("attack")) {
				zantarAttacks(enemy);
				if (!enemy.isAlive())
					return BattleStatus.ENEMY_DIES;
				System.out.println("Zantar gets careless in his attack allowing " + enemy.name()
				+ " to start attacking!");
				enemyAttacks(enemy);
				if (!Zantar.getInstance().isAlive())
					return BattleStatus.ZANTAR_DIES;
			}
		}

		return BattleStatus.ENEMY_DIES;
	}

	public static void zantarAttacks(Enemy enemy) {
		Zantar z = Zantar.getInstance();
		int attackStrength = z.attack();
		System.out.println(String.format("Zantar %s the enemy%s!", z.getAttackName(),
			z.isItemEquiped() ? " using his " + z.getEquipedItem().name() : ""));

		boolean willAttackLand = (RANDOM.nextInt(100) + 1) <
			(int) (z.getAttackProbabilityForEnemy(enemy) * 100);
		if (willAttackLand) {
			System.out.println(enemy.name() + " gets hit by Zantar's attack and takes " +
				attackStrength + " damage!");
			enemy.takeDamage(attackStrength);
		} else {
			System.out.println(enemy.name() + " dodges Zantar's attack!");
			return;
		}

		if (!enemy.isAlive())
			return;

		System.out.println("Zantar sees " + enemy.name() + " in a daze and attacks again!");
		zantarAttacks(enemy);
	}

	public static void enemyAttacks(Enemy enemy) {
		System.out.println(enemy.name() + " attacks Zantar!");

		boolean willAttackLand = (RANDOM.nextInt(100) + 1) <
			(int) (Zantar.getInstance().getAttackProbabilityForEnemy(enemy) * 100);

		if (willAttackLand) {
			System.out.println("Zantar couldn't dodge the attack!");
			Zantar.getInstance().takeDamage(enemy.attack());
		} else {
			System.out.println("Zantar dodges the attack without taking any damage!");
			return;
		}

		if (!Zantar.getInstance().isAlive())
			return;

		System.out.println(enemy.name() + " sees Zantar in a daze and attacks again!");
		enemyAttacks(enemy);
	}

	private static void showHelp() {
		System.out.println("Zantar is a text based adventure in which you control Zantar,"
				+ "a mighty space warrior. \nYou'll navigate around areas, picking up items"
				+ "and battling enemies.\n");
		System.out.println("Enter 'north', 'south', 'east' or 'west' to move.\nEnter 'undo' to undo "
				+ "last action.\nEnter 'pickup' to pickup item.\nEnter 'backpack' to"
				+ " list all items in your backpack.\nEnter 'equip itemname' to equip item");
		System.out.println("Enter 'stats' to view your current stats");
		System.out.println("Enter 'quit' to quit game");
	}

	private static void startGame() {
		// Introduction to the Game
		System.out.println("Welcome, mighty Zantar, on your quest to save the world"
				+ "You arrived last night in the middle of a jungle, but your \n"
				+ "items were stolen while you slept! You'll need to search "
				+ "and find them to have any chance of defeating\n"
				+ "the Evil King. What will you do first?\n\n"
				+ "There are openings to the north, east, south, and west.\n");
		runningGame = true;
	}

	private static void stopGame() {
		System.out.println("\nZantar will await your return, but be careful not to be away too long,\n"
				+ "as the Evil King will not wait on his journey for control of the planet!\n");
		runningGame = false;
	}

	private static void showMenuTillStartOrStop() {
		String choice = getChoice();
		if (choice.toLowerCase().equals("start")) {
			startGame();
		} else if (choice.toLowerCase().equals("help")) {
			showHelp();
			showMenuTillStartOrStop();
		}
		else if (choice.toLowerCase().equals("quit")) {
			stopGame();
		} else {
			System.out.println("Zantar doesn't understand!");
			showMenuTillStartOrStop();
		}
	}

	private static String getChoice() {
		System.out.print("\n>> ");
		String choice = SCANNER.nextLine().toLowerCase();
		if (game_commands.contains(choice)) {
			executeGameCommands(choice);
			return getChoice();
		} else {
			return choice;
		}
	}

	/**
	 * Prints the statistics of the game
	 */
	public static void printStatistics()
	{
		// Statistics
		if (!runningGame)
			return;

		System.out.println("\n# You have " + Zantar.getInstance().health() + " HP #");
		if (villain != null) {
			System.out.println("# Currently facing " + villain.name() + " #");
			System.out.println("# Enemy has " + villain.health() + " HP #");
		}
		Backpack.getInstance().printBackPack();
		System.out.println("# Enemies killed " + Zantar.getInstance().enemiesKilled() + " #");
	}

	public static void delay()
	{
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException exception)
		{
			System.out.println("\fThe game experienced an interrupted exception.");
			System.out.println("Please restart the game.");

			System.exit(0);
		}
	}
}
