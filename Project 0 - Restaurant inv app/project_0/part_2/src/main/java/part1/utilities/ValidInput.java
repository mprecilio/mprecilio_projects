package part1.utilities;

import java.util.Scanner;

public final class ValidInput {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static Scanner sc = new Scanner(System.in);

	public static String returnValid(int numAllowableAttemps, int minAllowableSize, int maxAllowableSize) {
		
		
		for(int i = 1; i <= numAllowableAttemps; i++) {
			System.out.print("> ");
			String userIn = sc.nextLine();
			if(userIn == null) {
				if(i == numAllowableAttemps) {
					System.out.println(ANSI_RED +"Too many attemps were made. Returning to previous screen..." + ANSI_RESET);
					return null;
				}
				System.out.println(ANSI_RED +"The string entered was to short. Please enter a sting between "+minAllowableSize+"-"+maxAllowableSize+" characters"+ ANSI_RESET);
			}
			else if(userIn.length() >= minAllowableSize && userIn.length() <= maxAllowableSize) return userIn;
			//string entered was too short
			else if(userIn.length()<minAllowableSize)System.out.println(ANSI_RED +"The string entered was to short. Please enter a sting between "+minAllowableSize+"-"+maxAllowableSize+" characters"+ ANSI_RESET);
			//string entered was too long
			else if(userIn.length()>maxAllowableSize)System.out.println(ANSI_RED +"The string entered was to long. Please enter a sting between "+minAllowableSize+"-"+maxAllowableSize+" characters"+ ANSI_RESET);
			else return null;
		}
		return null;
	}
	
	public static String returnValid(int numAllowableAttemps, int minAllowableSize) {
		for(int i = 0; i <= numAllowableAttemps; i++) {
			System.out.print("> ");
			String userIn = sc.nextLine();
			if(userIn == null) {
				if(i == numAllowableAttemps) {
					System.out.println(ANSI_RED +"Too many attemps were made. Returning to previous screen..."+ ANSI_RESET);
					return null;
				}
				System.out.println(ANSI_RED +"The string entered was to short. Please enter a sting larger than "+minAllowableSize+" characters"+ ANSI_RESET);
			}
			
			else if(userIn.length() >= minAllowableSize) return userIn;
			//string entered was too short
			else if(userIn.length()<minAllowableSize)System.out.println(ANSI_RED +"The string entered was to short. Please enter a sting larger than "+minAllowableSize+" characters"+ ANSI_RESET);
			else return null;
		}
		return null;
	}
	
	public static void openScanner() {
		sc = new Scanner(System.in);
	}
	

}
