/**
 * Backpack class
 * stores the gold coins for Zantar
 */


package zantar;

public class Backpack{
	
    private int coins;

    public Backpack()
    {
        coins = 0;
    } 

    public int getCoins()
    {
        return coins;
    } 

    public void addCoins(int coins)
    {
        this.coins += coins;
    } 

    public void removeCoins(int coins)
    {
        this.coins -= coins;
    } 

    public void setCoins(int coins)
    {
        this.coins = coins;
    } 
} 
