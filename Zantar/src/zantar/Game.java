/**
 * Zantar
 * A SyFi/fantasy text-based adventure game 
 */

package zantar;                                                                                                                                                                                                                  
                                                                                                                                                                                                                                 
import java.util.Scanner;                                                                                                                                                                                                        
import java.util.Random;                                                                                                                                                                                                         
                                                                                                                                                                                                    
                                                                                                                                                                                                                                 
public class Game {                                                                                                                                                                                                              
	public static final Random RANDOM = new Random();                                                                                                                                                                            
	public static final Scanner SCANNER = new Scanner(System.in);                                                                                                                                                                
                                                                                                                                                                                                                                 
	/** The menu options*/                                                                                                                                                                                                       
	public static final int attack = 1;                                                                                                                                                                                          
	public static final int use_item = 2;                                                                                                                                                                                        
	public static final int run = 3;                                                                                                                                                                                             
	public static final int exit_game = 4;                                                                                                                                                                                       
                                                                                                                                                                                                                                 
	/** The string which contains all accepted answers to yes or no questions */                                                                                                                            
	public static final String confirmation = "yes no";                                                                                                                                                                          
                                                                                                                                                                                                                                 
	public static final long delay = 2000;                                                                                                                                                                                       
                                                                                                                                                                                                                                 
	public static final int max_gold = 30;                                                                                                                                                                                       
                                                                                                                                                                                                                                 
	public static final int penance_for_cowardliness = 5;                                                                                                                                                                             
                                                                                                                                                                                                                                 
	public static void main(String[] argument) {                                                                                                                                                                                  
		
		// Main character                                                                                                                                                                                                        
		Zantar zantar = new Zantar();
		Backpack backpack = zantar.getBackpack();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		boolean running = true;                                                                                                                                                                                                  
		boolean ranAway = false;                                                                                                                                                                                                 
                                                                                                                                                                                                                                 
		// Introduction to the Game                                                                                                                                                                                              
		System.out.println("\fWelcome mighty Zantar on your quests to save the world.");                                                                                                                                                  
                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	while(running) {
		
		//The antagonists
		Enemy villain = new Enemy();                                                                                                                                                                                             
                                                                                                                                                                                                                                 
		while (villain.health() > 0) {                                                                                                                                                                                            
                                                                                                                                                                                                                                 
			printStatistics(zantar, villain);                                                                                                                                                                                    
			
			startBattle();                                                                                                                                                                                                       
                                                                                                                                                                                                                               
			int choice;                                                                                                                                                                                                          
                                                                                                                                                                                                                                 
			try {                                                                                                                                                                                                                  
			                                                                                                                                                                                                                   
				choice = Integer.parseInt(SCANNER.nextLine());                                                                                                                                                                   
			}                                                                                                                                                                                                                    
			catch (NumberFormatException exception)                                                                                                                                                                              
			{                                                                                                                                                                                                                    
				choice = run;                                                                                                                                                                                                    
			}                                                                                                                                                                                                                    
                                                                                                                                                                                                                                 
			switch (choice) {                                                                                                                                                                                                     
			                                                                                                                                                                                                                   
			case attack:                                                                                                                                                                                                         
				ranAway = false;                                                                                                                                                                                                 
				int zantarAttack = zantar.attack();                                                                                                                                                                              
				int enemyAttack = villain.attack();                                                                                                                                                                              
                                                                                                                                                                                                                                 
				System.out.println("\nYou dealt " + zantarAttack + " damage.");                                                                                                                                                  
				System.out.println("You took " + enemyAttack + " damage.");                                                                                                                                                      
                                                                                                                                                                                                                                 
				villain.takeDamage(zantarAttack);                                                                                                                                                                                
				zantar.takeDamage(enemyAttack);                                                                                                                                                                                  
                                                                                                                                                                                                                                 
				delay();                                                                                                                                                                                                         
				break;                                                                                                                                                                                                           
                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			case run:                                                                                                                                                                                                            
				// Penalty for running away
				
				if (zantar.getBackpack().getCoins() > penance_for_cowardliness)                                                                                                                                                                
				{                                                                                                                                                                                                                
					System.out.println("\n" + penance_for_cowardliness + " coins were stolen by the " + villain.name());                                                                                                              
					
					zantar.removeCoins(penance_for_cowardliness);                                                                                                                                                                      
				}                                                                                                                                                                                                                
				
				/* If Zantar does not have enough coins
				   Take away health instead of coins. */                                                                                                                                      
				
				else                                                                                                                                                                                                             
				{                                                                                                                                                                                                                
					System.out.println("\nThe enemy did " + penance_for_cowardliness + " damage before you managed to escape");                                                                                                       
					zantar.takeDamage(penance_for_cowardliness);                                                                                                                                                                      
				}                                                                                                                                              
                                                                                                                                                                                                                                 
				System.out.println("\nYou successfully ran from your fears...");                                                                                                                                                              
				delay();                                                                                                                                                                                                         
                                                                                                                                                                                                                                 
				// Kill the antagonists by dealing damage equivalent to its health
				
				villain.takeDamage(villain.health());                                                                                                                                                                            
                                                                                                                                                                                                                                 
				ranAway = true;                                                                                                                                                                                                  
				break;                                                                                                                                                                                                           
                                                                                                                                                                                                                                 
			case exit_game:                                                                                                                                                                                                           
				System.out.println("\fExiting the game...sadface");                                                                                                                                                                        
                                                                                                                                                                                                                                 
			    if (confirmation.contains(SCANNER.nextLine()))
                  {
     
                  } 

                  running = false;
                  return;
          }
			
			
				if (zantar.health() <= 0) {
					                                                                                                                                                                                                                
					System.out.println("\nYou have died, and didn't save the world...game over for us all.");                                                                                                                                                    
                                                                                                                                                                                                                                 
					System.out.print("Zantar would you like to try again? ");                                                                                                                                                             
					String continueGame = SCANNER.nextLine();                                                                                                                                                                    
                                                                                                                                                                                                                                 
					if (confirmation.contains(continueGame)) {
						
						running = true;                                                                                                                                                                                          
						zantar.reset();                                                                                                                                                                                          
					}                                                                                                                                                                           
					else                                                                                                                                                                                                         
					{                                                                                                                                                                                                            
						System.out.println("\nGame has been terminated, the world has ended");                                                                                                                                                             
					
						// Kill the antagonists by dealing damage                                                                                                                                        
						villain.takeDamage(villain.health());                                                                                                                                                                    
						running = false;                                                                                                                                                                                         
						return;
					}
				}
		}
		
		if (!ranAway)
		{
			 // The the antagonists has died, reward Zantar. */
            zantar.increaseEnemiesKilled();

            /* Give the player some gold for killing the enemy. */
            backpack.addCoins(RANDOM.nextInt(max_gold));
            delay();
		}
	}
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
                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                 
