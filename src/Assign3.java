/***************************************************************************
 * 
 * Howard Rosenblum
 * CST8110 18S 
 * Assignment 3 
 * Purpose: Main for the misère NIM game
 *
 ****************************************************************************/

public class Assign3 {

	public static void main(String[] args) {  // Program main
		Nim nim = new Nim();
		boolean playerWins=true;

		System.out.println("Welcome to the NIM game");
		System.out.println("We play by the misère rules ");
		do {
			nim.printPiles();
			if(nim.PlayerMove())
			{
				if(nim.done()) {
					playerWins=false;
				} else {
					nim.printPiles();
					nim.computerMove();
				}
			}
		} while (!nim.done());

		if(playerWins) {
			System.out.println("Congrats: you win");
		} else {
			System.out.println("Sorry: you lose");
		}

	}

}
