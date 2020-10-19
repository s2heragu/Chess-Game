package chess;

import java.util.Scanner;
/**
 * 
 * Driver class to play chess
 * @author Jonathan Wong, Shreyas Heragu
 *
 */
public class Chess {
	/**
	 * Main method to start chess program
	 * @param args input arguments in command line
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Board board = new Board();
		boolean dontDisplay = false;
		while(true) {
			
			//Checkmate
			//There is a checkmate
			if(board.isCheckMate(board.isWhiteTurn)) {
				System.out.println("Checkmate");
				//White was checkmated
				if(board.isWhiteTurn) {
					System.out.println("Black Wins");
				}
				//Black was checkmated
				else {
					System.out.println("White Wins");
				}
				break;
			}

			
			//Print board
			//Do not print board if previous input was illegal
			if(!dontDisplay) {
				System.out.println(board);
			}
			
			//Check
			if(board.kingChecked(board.isWhiteTurn) && !dontDisplay) {
				System.out.println();
				System.out.println("Check");
			}
			
			//Get input from user
			String input = getInput(scanner, board);
			
			//Resignation and Draw
			if(gameFinished(input, board)) {
				break;
			}
			
			//Validate input
			if(board.parseMove(input)) {
				//Valid input
				dontDisplay = false;
				//Do the move, enpassant should be set up here
				board.performMove(input);
				//Forfeit enpassant on opposing team pawns
				board.clearEnpassant(!board.isWhiteTurn);
				// Swap turn
				board.isWhiteTurn = !board.isWhiteTurn;
			}
			else {
				//Illegal move
				System.out.println("Illegal move, try again");
				dontDisplay = true;
			}
			
			
		}
	}
	/**
	 * Method to handle resignations, draws to end the game
	 * @param input Move from user input
	 * @return boolean to determine whether the game is finished, true if game is finished, false otherwise
	 */
	public static boolean gameFinished(String input, Board board) {
		//Resign
		if(input.equals("resign")) {
			if(board.isWhiteTurn) {
				System.out.println("Black wins");
			}
			else {
				System.out.println("White wins");
			}
			return true;
		}
		//Draw
		else if(input.equals("draw")) {
			System.out.println("draw");
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the user input to move a piece "FileRank FileRank otherInfo"
	 * Does not perform any action on the board
	 * @param scanner Input scanner to get user input
	 * @param board the current game state
	 * @return String that represents the next userInput
	 */
	public static String getInput(Scanner scanner, Board board) {
		System.out.println();
		if(board.isWhiteTurn) {
			System.out.print("White's Move: ");
		}
		else {
			System.out.print("Black's Move: ");
		}
		String input = scanner.nextLine();
		System.out.println();
		return input;
	}

}
