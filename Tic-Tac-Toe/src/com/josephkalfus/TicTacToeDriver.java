/*
 * Author: Joseph Kalfus
 * Date: June-July 2019
 * This is a simple command line tic-tac-toe game. There are two
 * players where one is the computer and the other is the user
 */
package com.josephkalfus;

public class TicTacToeDriver {

	public static void main(String[] args) {

		GameBoard.startGame();

	}
}

/**
 * ============= Lessons Learned ===============
 * Aspect needs to be in same package: When I made my .aj file, I mistakenly put
 * it into the default package folder instead of my com.whatever folder. 
 *  ============= Bug Report =================
 *  For some reason when the grid is full, no draw game
 *  is declared. Otherwise, the aspect invokes the correct
 *  winner. 
 *  
 *  The diagnal winner could be more programatic instead of hard coded
 * 
*/