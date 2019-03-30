import java.util.Random;
import java.util.Scanner;
/***************************************************************************
 * 
 * Howard Rosenblum
 * CST8110 18S 
 * Assignment 3 
 * Purpose: Class for the game 
 *
 ****************************************************************************/

public class Nim {
	private static final boolean BONUS=true; // only for testing
	private Pile pileA;  // First pile
	private Pile pileB;  // Second pile
	private Pile pileC;  // Third pile
	private Random rnd = new Random(); // Random number generator
	private Scanner input = new Scanner(System.in);  // Scanner for all user input

	public Nim() // Default constructor, constructs the three piles
	{
		if(BONUS) {
			pileA = new Pile(rnd.nextInt(11)+10);
			pileB = new Pile(rnd.nextInt(11)+10);
			pileC = new Pile(rnd.nextInt(11)+10);
		} else {
			pileA = new Pile();
			pileB = new Pile();
			pileC = new Pile();			
		}
	}

	public boolean PlayerMove()  // All the rules to handle user input
	{
		System.out.print("\nSelect a pile: ");
		char pile = input.nextLine().toLowerCase().charAt(0);

		Pile selectedPile;
		if(pile=='a') {
			selectedPile=pileA;
		} else if(pile=='b') {
			selectedPile=pileB;
		} else if(pile=='c') {
			selectedPile=pileC;
		} else {
			System.out.println("Invalid pile letter, select a, b or c ");
			return false;
		}

		int size = selectedPile.getSize();
		if(size==0) {
			System.out.println("Pile "+pile+" is empty, pick another ");
			return false;
		}

		System.out.print("How many do you want to remove? ");
		int amount = input.nextInt();
		input.nextLine();

		if(amount<1 || amount>size) {
			System.out.println("Pick a number between 1 and "+size);
			return false;
		}

		selectedPile.remove(amount);

		return true;
	}

	private void computerRandomMove()  // Computer move if done randomly, is computerMove() in non-bonus version
	{
		Pile selectedPile;
		int size;
		char pileLetter;

		do {
			int pile=rnd.nextInt(3);
			switch(pile)
			{
			case 0:
				selectedPile = pileA;
				pileLetter = 'A';
				break;
			case 1:
				selectedPile = pileB;
				pileLetter = 'B';
				break;
			default:
				selectedPile = pileC;
				pileLetter = 'C';
				break;
			}
			size = selectedPile.getSize();
		} while (size==0);

		int removed=rnd.nextInt(size)+1;
		selectedPile.remove(removed);
		System.out.println("Computer takes "+removed+" from pile "+pileLetter);
	}

	public void computerMove()  // All the rules to handle computer move
	{
		int nimSum = pileA.getSize() ^ pileB.getSize() ^ pileC.getSize();
		// player is in winning position or non-bonus version, play random move
		if(nimSum == 0 || !BONUS)
		{
			computerRandomMove();
			return;
		}
		char pileLetter='X';
		int removed=0;

		// endgame
		if(pileB.getSize()+pileC.getSize()==1) { // if one pile has zero and the second has one, remove all of the third pile 
			pileLetter = 'A';
			removed = pileA.getSize();
			pileA.remove(removed);							
		} else if(pileA.getSize()+pileC.getSize()==1) {
			pileLetter = 'B';
			removed = pileB.getSize();
			pileB.remove(removed);							
		} else if(pileA.getSize()+pileB.getSize()==1) {
			pileLetter = 'C';
			removed = pileC.getSize();
			pileC.remove(removed);							
		} else if(pileB.getSize()==1 && pileC.getSize()==1) { // if two piles have one, remove all but one of the third pile 
			pileLetter = 'A';
			removed = pileA.getSize()-1;
			pileA.remove(removed);							
		} else if(pileA.getSize()==1 && pileC.getSize()==1) {
			pileLetter = 'B';
			removed = pileB.getSize()-1;
			pileB.remove(removed);							
		} else if(pileA.getSize()==1 && pileB.getSize()==1) {
			pileLetter = 'C';
			removed = pileC.getSize()-1;
			pileC.remove(removed);							
		}

		// normal round
		if(removed==0) {
			int remain = pileA.getSize() ^ nimSum;
			if(remain < pileA.getSize())
			{
				pileLetter = 'A';
				removed = pileA.getSize()-remain;
				pileA.remove(removed);
			} else {
				remain = pileB.getSize() ^ nimSum;
				if(remain < pileB.getSize())
				{
					pileLetter = 'B';
					removed = pileB.getSize()-remain;
					pileB.remove(removed);
				} else {
					remain = pileC.getSize() ^ nimSum;
					pileLetter = 'C';
					removed = pileC.getSize()-remain;
					pileC.remove(removed);				
				}

			}
		}

		System.out.println("Computer takes "+removed+" from pile "+pileLetter);
	}


	public boolean done()  // Is the game done?
	{
		return pileA.getSize()+pileB.getSize()+pileC.getSize()==0;
	}

	public void printPiles()  // Print the current state of the piles
	{
		System.out.println("\tA\tB\tC");
		System.out.println("\t"+pileA.getSize()+"\t"+pileB.getSize()+"\t"+pileC.getSize());
	}
}
