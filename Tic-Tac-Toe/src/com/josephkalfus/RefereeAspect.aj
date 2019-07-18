package com.josephkalfus;

public aspect RefereeAspect {
	pointcut logWinner() : call (public static void determineWinner());

	pointcut getPlayerStatus() : get(boolean GameBoard.playerWinner);

	after( ) returning(Boolean value) : getPlayerStatus( ) {
		if (value == true) {
			System.out.println("You have won!");
			System.out.println("Quitting...");
			System.exit(0);
		}
	}

	pointcut getComputerStatus() : get(boolean GameBoard.computerWinner);

	after( ) returning(Boolean value) : getComputerStatus( ) {
		if (value == true) {
			System.out.println("The computer won.");
			System.out.println("Quitting...");
			System.exit(0);
		}
	}

	pointcut getDrawStatus() : get(boolean GameBoard.drawWinner);

	after( ) returning(Boolean value) : getDrawStatus( ) {
		if (value == true) {
			System.out.println("There was a draw.");
			System.out.println("Quitting...");
			System.exit(0);
		}
	}
}
