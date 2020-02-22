package com.felix;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author ravudari
 *
 */
public class Main {
	final static char[] vals1 = { ' ', '|', ' ', '|', ' ' };
	final static char[] vals2 = { '-', '+', '-', '+', '-' };
	final static char[][] board = { vals1.clone(), vals2.clone(), vals1.clone(), vals2.clone(), vals1.clone() };

	final static List<Integer> row1 = asList(1, 2, 3);
	final static List<Integer> row2 = asList(4, 5, 6);
	final static List<Integer> row3 = asList(7, 8, 9);

	final static List<Integer> col1 = asList(1, 4, 7);
	final static List<Integer> col2 = asList(2, 5, 8);
	final static List<Integer> col3 = asList(3, 6, 9);

	final static List<Integer> dia1 = asList(1, 5, 9);
	final static List<Integer> dia2 = asList(3, 5, 7);

	public static void main(String[] args) {
		List<Integer> userOptions = new ArrayList<>();
		List<Integer> systemOptions = new ArrayList<>();
		List<Integer> allOptions = new ArrayList<>();

		printBoard(board);

		while (true) {
			System.out.print("Enter a number(1-9): ");
			int number = readUserNumberFromKB();
			if (number < 1 || number > 9) {
				System.out.println("ERROR: You've Entered Invalid Number.");
				continue;
			} else if (allOptions.contains(number)) {
				System.out.println("ERROR: Number already used.");
				continue;
			}
			System.out.println("You've Entered: " + number);
			updateBoard(board, number, '0', userOptions, allOptions);
			printBoard(board);

			if (isWinner(userOptions)) {
				System.out.println("Congratualtions!! You've Won this round!!");
				break;
			}
			if (isDraw(allOptions)) {
				System.out.println("The Game is Draw!!");
				break;
			}

			number = generateSystemNumber(allOptions);
			System.out.println("System Entered: " + number);
			updateBoard(board, number, 'X', systemOptions, allOptions);
			printBoard(board);

			if (isWinner(systemOptions)) {
				System.out.println("System Won this round!!");
				break;
			}
			if (isDraw(allOptions)) {
				System.out.println("The Game is Draw!!");
				break;
			}
		}

	}

	private static int generateSystemNumber(List<Integer> allOptions) {
		while (true) {
			int number = new Random().nextInt(9) + 1;
			if (allOptions.contains(number)) {
//					System.out.println("ERROR: System Entered Already Used Number " + number + ". Entering Again.");
				continue;
			}
			return number;
		}
	}

	private static boolean isDraw(List<Integer> allOptions) {
		return allOptions.size() == 9;
	}

	private static boolean isWinner(List<Integer> playerOptions) {
		return playerOptions.containsAll(row1) || playerOptions.containsAll(row2) || playerOptions.containsAll(row3)
				|| playerOptions.containsAll(col1) || playerOptions.containsAll(col2) || playerOptions.containsAll(col3)
				|| playerOptions.containsAll(dia1) || playerOptions.containsAll(dia2);
	}

	private static void updateBoard(final char[][] board, int number, char ch, List<Integer> playerOptions,
			List<Integer> allOptions) {
		playerOptions.add(number);
		allOptions.add(number);

		switch (number) {
		case 1:
			board[0][0] = ch;
			break;
		case 2:
			board[0][2] = ch;
			break;
		case 3:
			board[0][4] = ch;
			break;
		case 4:
			board[2][0] = ch;
			break;
		case 5:
			board[2][2] = ch;
			break;
		case 6:
			board[2][4] = ch;
			break;
		case 7:
			board[4][0] = ch;
			break;
		case 8:
			board[4][2] = ch;
			break;
		case 9:
			board[4][4] = ch;
			break;
		default:
			System.out.println("ERROR: Something went wrong. ");
		}
	}

	@SuppressWarnings("resource")
	private static int readUserNumberFromKB() {
		return new Scanner(System.in).nextInt();
	}

	private static void printBoard(char[][] board) {
		System.out.println();
		for (char[] row : board) {
			for (char ch : row) {
				System.out.print(ch);
			}
			System.out.println();
		}
		System.out.println();
	}
}
