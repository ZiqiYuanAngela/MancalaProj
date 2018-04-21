package mancalagame;

import java.util.Scanner;

public class MancalaTest {
	
	public static void main(String[] args) {
		System.out.println("Enter the starting stone number (3/4).");
		Scanner in = new Scanner(System.in);
		int stoneNum = in.nextInt();
		
		while (stoneNum > 4 || stoneNum < 3) {
			System.out.println("Please enter a valid starting stone number (3/4)."); //bug them until they answer correctly
			Scanner inp = new Scanner(System.in);
			stoneNum = inp.nextInt();
		}
		
		
		
	}

}
