package com.josephkalfus;

import java.util.Random;
import java.util.Scanner;

public class GameBoard {

	/**
	 * Made static 2-D Array because only one game board will be in memory at one
	 * time
	 */

	private static char[][] matrix = new char[3][3];

	final static char COMPUTER_SYMBOL = 'O';
	final static char PLAYER_SYMBOL = 'X';

	public static boolean playerTurn = false; // false for computer's turn; true for player's turn
	private static GameBoard gameObject = null;

	public static boolean computerWinner = false;
	public static boolean playerWinner = false;
	public static boolean drawWinner = false;

	/**
	 * Constructor that sets each grid square to a blank space character ' '
	 */
	public GameBoard() {

		for (int row = 0; row < matrix.length; row++) {

			for (int column = 0; column < matrix[row].length; column++) {
				matrix[row][column] = ' ';
			}

		}
	}

	/**
	 * This method converts the user input columns and rows to the 2-D array format.
	 * For example, if the user wants to place their symbol on grid square C1, it
	 * will convert this to array_index[2][1] using ASCII math. It will also
	 * validate the input and ensure that the grid square is empty
	 * 
	 * @param column
	 * @param row
	 */
	public static void setMatrix(char column, char row) {

		char charcolumn = column;
		char charrow = row;

		int columnnumber = Character.toUpperCase(charcolumn) - 'A';
		int rownumber = charrow - '0';

		while (playerTurn) {

			if (rownumber < matrix.length && columnnumber < matrix[1].length
					&& matrix[rownumber][columnnumber] == ' ') {
				matrix[rownumber][columnnumber] = PLAYER_SYMBOL;
				playerTurn = false;
				break;
			} else {
				System.out.println("Position is already taken!");
				playerTurn = true;
				gameObject.playerTurn();
				break;
			}
		}
	}

	/**
	 * * This method dynamically prints the grid based on the array size that is set
	 * at the top. It will print letters on the top row. It will print numbers on
	 * the right-most column. These letter and number designations will make it
	 * easier to have the user choose where they want to insert their symbol via the
	 * terminal.
	 */
	public void displayMatrix() {

		int topRowLetterCount = 0;
		for (char row = 'A'; topRowLetterCount < matrix.length; row++) {
			System.out.print(Character.toString(row) + ' ');
			topRowLetterCount++;
		}
		System.out.println("\n");

		for (int row = 0; row < matrix.length; row++) {

			for (int column = 0; column < matrix[row].length; column++) {
				System.out.print(matrix[row][column] + "|");
			}
			System.out.println(row + "\n"); // prints row# for user selection
		}
		determineWinner();
	}

	/**
	 * This method determines the computer random placement of their symbol and also
	 * checks to ensure that a symbol is not already placed in that grid square. A
	 * blank square is denoted by a space character ' '
	 */
	public void computerTurn() {

		Random randomnumber = new Random();

		boolean continues = true;

		while (continues == true) {
			int rownumber = randomnumber.nextInt(matrix.length);
			int columnnumber = randomnumber.nextInt(matrix[1].length);
			if (matrix[rownumber][columnnumber] == ' ') {
				matrix[rownumber][columnnumber] = COMPUTER_SYMBOL;
				continues = false;
				playerTurn = true;
			}
		}
		displayMatrix();
		playerTurn();

	}

	public void playerTurn() {

		Scanner playerInput = new Scanner(System.in);

		boolean badInput = false;
		boolean quitInput = false;
		do {

			System.out.println("Enter your grid; q to quit:");
			String inputs = playerInput.nextLine();
			if (inputs.matches("Q|q")) {
				System.out.print("quitting\n");
				quitInput = true;
				break;

			} else if (inputs.matches("[a-zA-Z][0-9]")) {
				char firstCharInput = inputs.charAt(0);
				char secondCharInput = inputs.charAt(1);
				setMatrix(firstCharInput, secondCharInput);

				badInput = false;
				computerTurn();
			} else {
				System.out.println("Invalid Input!");
				badInput = true;
			}
			System.out.println(inputs);
		} while (badInput == true && quitInput == false);

	}

	public static boolean determineWinner() {

		for (int row = 0; row < matrix.length; row++) {
			if ((matrix[row][0] == PLAYER_SYMBOL && matrix[row][1] == PLAYER_SYMBOL && matrix[row][2] == PLAYER_SYMBOL)
					|| (matrix[0][0] == PLAYER_SYMBOL && matrix[1][1] == PLAYER_SYMBOL && matrix[2][2] == PLAYER_SYMBOL)
					|| (matrix[0][2] == PLAYER_SYMBOL && matrix[1][1] == PLAYER_SYMBOL
							&& matrix[2][0] == PLAYER_SYMBOL)) {
				playerWinner = true;
				break;
			} else {
				playerWinner = false;
			}
		}

		for (int row = 0; row < matrix.length; row++) {
			if ((matrix[row][0] == COMPUTER_SYMBOL && matrix[row][1] == COMPUTER_SYMBOL
					&& matrix[row][2] == COMPUTER_SYMBOL)
					|| (matrix[0][0] == COMPUTER_SYMBOL && matrix[1][1] == COMPUTER_SYMBOL
							&& matrix[2][2] == COMPUTER_SYMBOL)
					|| (matrix[0][2] == COMPUTER_SYMBOL && matrix[1][1] == COMPUTER_SYMBOL
							&& matrix[2][0] == COMPUTER_SYMBOL)) {
				computerWinner = true;
				break;
			} else {
				computerWinner = false;
			}
		}

		if (playerWinner == false && computerWinner == false) {
			for (int row = 0; row < matrix.length; row++) {

				for (int column = 0; column < matrix[row].length; column++) {
					if (matrix[row][column] == ' ') {
						drawWinner = false;
						break;
					} else {
						drawWinner = true;
					}
				}

			}
		}

		return false;

	}

	public static void startGame() {

		gameObject = new GameBoard();
		System.out.println("you will be playing a great game");
		System.out.println("The computer will place their symbol " + COMPUTER_SYMBOL + " on the board");
		System.out.println("Place your " + PLAYER_SYMBOL
				+ " on the board by typing the letter and number position you want like C2");

		gameObject.computerTurn();
	}

}
