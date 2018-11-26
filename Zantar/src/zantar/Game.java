/**
 * Zantar
 * A SyFi/fantasy text-based adventure game 
 */

package zantar;                                                                                                                                                                                                                  
                                                                                                                                                                                                                                 
import java.util.Scanner;
import java.io.File;
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
	
	/** The string which contains all accepted answers to yes or no questions                                                                                                                            
	public static final String confirmation = "yes no";                                                                                                                                                                          
                                                                                                                                                                                                                                 
	public static final long delay = 2000;                                                                                                                                                                                       
                                                                                                                                                                                                                                 
	public static final int max_gold = 30;
	*/       
	
	public static final long delay = 2000;                                                                                                                                                                                                                             
	public static final int penance_for_cowardliness = 5;                                                                                                                                                                             
                                                                                                                                                                                                                                 
	public static void main(String[] argument) {                                                                                                                                                                                  
		
		// Main character                                                                                                                                                                                                        
		Zantar zantar = Zantar.getInstance();
		//Backpack backpack = zantar.getBackpack();
		Map map = Map.getInstance();
		//System.out.println(new File(".").getAbsoluteFile());
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		boolean running = true;                                                                                                                                                                                                  
		boolean ranAway = false;                                                                                                                                                                                                 
                                                                                                                                                                                                                                 
		// Introduction to the Game                                                                                                                                                                                              
		System.out.println("Welcome, mighty Zantar, on your quest to save the planet Mangani.\n"
				+ "You arrived last night in the middle of a jungle, but your\n"
				+ "items were stolen while you slept! You'll need to search\n"
				+ "and find them to have any chance of defeating\n"
				+ "the evil King. What will you do first?\n\n");
				//+ "There are openings to the north, east, south, and west.");
	}
                                                                                                                                                                                                                                                                                                                                                                                                                                                
	private static void printStatistics(Zantar zantar, Enemy villain) {
			
	}
                                                                                                                                                                                                                          
	// The battle prompt menu                                                                                                                                                                                                                                                                                                                                                                                                          
	public static void startBattle()                                                                                                                                                                                             
	{                                                                                                                                                                                                                            
		System.out.println("\n1. Attack.");                                                                                                                                                                                      
		System.out.println("2. Use Item");                                                                                                                                                                                    
		System.out.println("3. Run!");                                                                                                                                                                                                                                                                                                                                                                             
		System.out.println("4. Exit Game.");                                                                                                                                                                                     
                                                                                                                                                                                                                                 
		System.out.print("\nChoice? ");                                                                                                                                                                                          
	}                                                                                                                                                                                                   
                                                                                                                                                                                                                                 
	/**                                                                                                                                                                                                                          
	 * Prints the statistics of the game                                                                                                                                           
	 */                                                                                                                                                                                                                          
	public static void printStatistics1(Zantar zantar, Enemy villain )                                                                                                                                                            
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
                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                 
