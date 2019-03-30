/***************************************************************************
 * 
 * Howard Rosenblum
 * CST8110 18S 
 * Assignment 3 
 * Purpose: Class to handle ONE pile 
 *
 ****************************************************************************/

public class Pile {
	private int size;  // The current size of pile
	
	public Pile()  // Default constructor (Non-bonus version)
	{
		size = 10;
	}
	
	public Pile(int initSize)  // Initial constructor (Bonus version)
	{
		size = initSize;
	}
	
	public int getSize() // get current size of pile
	{
		return size;
	}
	
	public void remove(int amount)  // remove the amount from pile
	{
		size -= amount;
	}

}
