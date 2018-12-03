/**
 * Zantar
 * A SyFi/fantasy text-based adventure game 
 */

package zantar;                                                                                                                                                                                                                  
                                                                                                                                                                                                                                 
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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
	private static final String game_commands = "quit help";
	private static final String item_commands = "equip backpack";
	private static final String battle_commands = "";
    
	/**
	public static final long delay = 2000;                                                                                                                                                                                       
                                                                                                                                                                                                                                 
	public static final int max_gold = 30;
	*/       
    
	private static boolean runningGame = false;
	private static boolean ranAway = false;
	
	public static final long delay = 2000;                                                                                                                                                                                                                             
	public static final int penance_for_cowardliness = 30;                                                                                                                                                                             
                                                                                                                                                                                                                                 
	public static void main(String[] argument) throws FileNotFoundException {                                                                                                                                                                                  
		
		// Main character                                                                                                                                                                                                        
		Zantar zantar = Zantar.getInstance();
		
		System.out.println("\nWelcome! Zantar and his mighty quest awaits. "
				+ "If you wish to play the game, enter 'start'.\n"
				+ "For help. enter 'help'. To 'quit', enter quit!");
		
		showMenuTillStartOrStop();
		if (runningGame) {
			zantar.printXY();
		}
		                                 
		while (runningGame) {
			String choice = getChoice();
			
			if (movements.contains(choice)) {
				moveZantar(choice);
			} else if (item_commands.contains(choice.split(" ")[0])) {
				if (choice.equals("backpack")) {
					Backpack.getInstance().printBackPack();
				} else if (choice.contains("equip")) {
					if (choice.split(" ").length < 2) {
						System.out.println("Choose an item from backpack to equip!");
						continue;
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
	
	private static void executeGameCommands(String choice) {
		if (choice.equals("quit")) {
			stopGame();
		} else if (choice.equals("help")) {
			showHelp();
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
				if(foundEnemy(enemy)) { 				
				} else {
					if (Zantar.getInstance().isAlive()) {
						Zantar.getInstance().runAway();
						System.out.println("*Zantar takes a deep breath and thinks 'As long"
								+ " as I have my life, I can go back and kill that " + 
								enemy.name() + " anytime!\nI will adventure more and go "
								+ "back to kill "+ enemy.name() + " when I get stronger!'"
								+ "* Let's start over!");
						Backpack.getInstance().printBackPack();
						Zantar.getInstance().printXY();
					} else {
						runningGame = false;
					}
				}
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
				Backpack.getInstance().addItem(new Item(itemName, 25, true, 6, 0.3, 0.6));
				System.out.println("Zantar picked up sword successfully! "
						+ "He placed it in his backpack");
			} else if (itemName.equals("dagger")) {
				Backpack.getInstance().addItem(new Item(itemName, 15, true, 6, 0.5, 0.75));
				System.out.println("Zantar picked up dagger successfully! "
						+ "He placed it in his backpack");
			} else if (itemName.equals("axe")) {
				Backpack.getInstance().addItem(new Item(itemName, 30, true, 6, 0.5, 0.7));
				System.out.println("Zantar picked up axe successfully! "
						+ "He placed it in his backpack");
			} else if (itemName.equals("stick")) {
				Backpack.getInstance().addItem(new Item(itemName, 5, true, 2, 0.5, 0.75));
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
	
	private static boolean foundEnemy(Enemy enemy) {
		System.out.println("Zantar and " + enemy.name() + " are facing each other.");
		System.out.println("Would you like to fight or beg for mercy");
		String choice = getChoice();
		if (choice.equals("fight")) {
			return startBattle(enemy);
		} else if ("beg for mercy".contains(choice)) {
			begForMercy(enemy.name());
			return false;
		} else {
			System.out.println("Zantar doesn't understand.");
			return foundEnemy(enemy);
		}
	}
	
	private static void begForMercy(String enemy) {
		System.out.println("*Zantar starts to kowtow*.\nOh mighty " + enemy + "! "
				+ "Please spare weak little me.");
		System.out.println("*" + enemy + " laughs smugly*\nHAHAHA! Give me " 
				+ penance_for_cowardliness + " gold and I shall "
				+ "spare your puny little life!");
		System.out.println("*Zantar quickly takes out " + penance_for_cowardliness 
				+ " gold and tosses it over " + "to " + enemy + "*");
		Backpack.getInstance().removeCoins(penance_for_cowardliness);
		System.out.println("*Then Zantar runs away*");
	}
                                                                                                                                                                                                                          
	// The battle prompt menu                                                                                                                                                                                                                                                                                                                                                                                                          
	private static boolean startBattle(Enemy enemy)                                                                                                                                                                                             
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
			System.out.println(enemy.name() + " attacked first!");
			enemyAttack();
		}
		while (enemy.isAlive()) {
			System.out.println("Zantar prepares to attack! What will you choose?");
			System.out.println("Attack!");                                                                                                                                                                                   
			System.out.println("Beg for mercy!");      
			String choice = getChoice();
			if ("beg for mercy".contains(choice)) {
				System.out.println("*Zantar drops all defences!*");
				if (RANDOM.nextBoolean()) {
					begForMercy(enemy.name());
					return false;
				} else {
					System.out.println(enemy.name() + " is too enraged to listen to Zantar's "
							+ "plea for life! " + enemy.name() + " kills the defenceless Zantar!");
					Zantar.getInstance().takeDamage(100);
					return false;
				}
			} else if (choice.equals("fight")) {
				
			} else {
				System.out.println("Zantar doesn't understand!");
				continue;
			}
		}
                                        
		return true;
	}      
	
	public static void enemyAttack() {
		if (RANDOM.nextBoolean()) {
			System.out.println("Zantar couldn't dodge the attack!");
			Zantar.getInstance().takeDamage(Enemy.max_attack_damage);
		} else {
			System.out.println("Zantar dodges the attack without taking any damage!");
		}
	}
	
	private static void showHelp() {
		System.out.println("Zantar is a text based adventure in which you control Zantar,"
				+ "a mighty space warrior. \nYou'll navigate around areas, picking up items"
				+ "and battling enemies.\n");
		System.out.println("Enter 'north', 'south', 'east' or 'west' to move.\nEnter 'undo' to undo "
				+ "last action.\nEnter 'pickup' to pickup item.\nEnter 'backpack' to"
				+ " list all items in your backpack.\nEnter 'equip itemname' to equip item");
		System.out.println("Enter 'quit' to quit game");
	}
	
	private static void startGame() {
		// Introduction to the Game       
		System.out.println("Welcome, mighty Zantar, on your quest to save the planet Mangani"
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
	public static void printStatistics(Zantar zantar, Enemy villain )                                                                                                                                                            
	{                                                                                                                                                                                                                            
		// Statistics                                                                                                                                                                                                            
		System.out.println("\f# A " + villain.name() + " appeared #");                                                                                                                                                           
                                                                                                                                                                                                                                 
		System.out.println("\n# You have " + zantar.health() + " HP #");                                                                                                                                                         
		System.out.println("# Enemy has " + villain.health() + " HP #");                                                                                                                                                                                                                                                                                                             
		System.out.println("# Backback has " + zantar.getBackpack().getClass() + " coins #");                                                                                                                                          
		System.out.println("# Enemies killed: " + zantar.enemiesKilled() + " #");
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
                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                 
