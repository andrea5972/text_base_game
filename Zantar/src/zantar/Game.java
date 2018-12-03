/**
 * Zantar
 * A SyFi/fantasy text-based adventure game 
 */

package zantar;                                                                                                                                                                                                                  
                                                                                                                                                                                                                                 
import java.util.Scanner;
import java.io.File;
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
	private static final String game_commands = "quit help";
    
	/**
	public static final long delay = 2000;                                                                                                                                                                                       
                                                                                                                                                                                                                                 
	public static final int max_gold = 30;
	*/       
    
	private static boolean runningGame = false;
	private static boolean ranAway = false;
	
	public static final long delay = 2000;                                                                                                                                                                                                                             
	public static final int penance_for_cowardliness = 5;                                                                                                                                                                             
                                                                                                                                                                                                                                 
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
			System.out.print("\n>> ");
			String choice = SCANNER.nextLine().toLowerCase();
			
			if (movements.contains(choice)) {
				moveZantar(choice);
			} else if (game_commands.contains(choice)) {
				if (choice.equals("quit")) {
					stopGame();
				} else if (choice.equals("help")) {
					showHelp();
				}
			} else {
				System.out.println("Zantar doesn't understand!");
				System.out.println("Enter help to get help.");
			}
			
		}
	}
	
	private static void moveZantar(String choice) {
		Zantar z = Zantar.getInstance();
		z.move(choice);
		z.printXY();

		String locData = Map.getInstance().getLocationData(z.getX(), z.getY());
		
		if (locData != null) {
			System.out.println(String.format("Zantar found %s. What would you like to do?", 
					locData));
		}
	}
                                                                                                                                                                                                                          
	// The battle prompt menu                                                                                                                                                                                                                                                                                                                                                                                                          
	private static void startBattle()                                                                                                                                                                                             
	{                                                                                                                                                                                                                            
		System.out.println("\n1. Attack.");                                                                                                                                                                                      
		System.out.println("2. Use Item");                                                                                                                                                                                    
		System.out.println("3. Run!");                                                                                                                                                                                                                                                                                                                                                                             
		System.out.println("4. Exit Game.");                                                                                                                                                                                     
                                                                                                                                                                                                                                 
		System.out.print("\nChoice? ");                                                                                                                                                                                          
	}            
	
	private static void showHelp() {
		System.out.println("Zantar is a text based adventure in which you control Zantar,"
				+ "a mighty space warrior. \nYou'll navigate around areas, picking up items"
				+ "and battling enemies.\n");
		System.out.println("Enter 'north', 'south', 'east' or 'west' to move.\nEnter 'undo' to undo "
				+ "last action.\nEnter 'pickup' to pickup item.\nEnter 'item list' to"
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
		System.out.print("\n>> ");
		String choice = SCANNER.nextLine();
		if (choice.toLowerCase().equals("start")) {
			startGame();
		} else if (choice.toLowerCase().equals("help")) {
			showHelp();
			showMenuTillStartOrStop();
		}
		else if (choice.toLowerCase().equals("quit")) {
			stopGame();
		} else {
			System.out.println("\nThat command is not acceptable here, please try again!\n");
			showMenuTillStartOrStop();
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
                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                 
