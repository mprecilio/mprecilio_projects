package part1.utilities;

public class PrintMenus {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	//Prints out the application name
	public static void printAppName() {
	System.out.println(ANSI_GREEN + "  _______ _                 _           __                 _                     _                 _ _               _____            __  __ " + ANSI_RESET);
	System.out.println(ANSI_GREEN + " |__   __| |               | |         / _|               | |                   | |               | (_)             |  __ \\     /\\   |  \\/  |" + ANSI_RESET);
	System.out.println(ANSI_GREEN + "    | |  | |__   __ _ _ __ | | _____  | |_ ___  _ __    __| | _____      ___ __ | | ___   __ _  __| |_ _ __   __ _  | |__) |   /  \\  | \\  / |" + ANSI_RESET);
	System.out.println(ANSI_GREEN + "    | |  | '_ \\ / _` | '_ \\| |/ / __| |  _/ _ \\| '__|  / _` |/ _ \\ \\ /\\ / / '_ \\| |/ _ \\ / _` |/ _` | | '_ \\ / _` | |  _  /   / /\\ \\ | |\\/| |" + ANSI_RESET);
	System.out.println(ANSI_GREEN + "    | |  | | | | (_| | | | |   <\\__ \\ | || (_) | |    | (_| | (_) \\ V  V /| | | | | (_) | (_| | (_| | | | | | (_| | | | \\ \\  / ____ \\| |  | |" + ANSI_RESET);
	System.out.println(ANSI_GREEN + "    |_|  |_| |_|\\__,_|_| |_|_|\\_\\___/ |_| \\___/|_|     \\__,_|\\___/ \\_/\\_/ |_| |_|_|\\___/ \\__,_|\\__,_|_|_| |_|\\__, | |_|  \\_\\/_/    \\_\\_|  |_|" + ANSI_RESET);
	System.out.println(ANSI_GREEN + "                                                                                                              __/ |                          " + ANSI_RESET);
	System.out.println(ANSI_GREEN + "    (Restaurant Application for Management)                                                                  |___/                           " + ANSI_RESET);
	}
	
	public static void printMainMenu() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(ANSI_YELLOW + "Which service you would like to use today! (Please enter a number)\n"+ANSI_RESET);
		System.out.println("1 - Login");
		System.out.println("2 - Sign up");
		System.out.println("0 - exit");
	}
	
	public static void printInvalidLogin() {
		System.out.println("The username and password you entered were invalid. Would you like to try another username and password?");
		System.out.println("1 - Re-enter username and password\n2 - Go back to main menu\n0 - Exit");
	}
	
	public static void printRestaurantMenu() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(ANSI_YELLOW + "Which service you would like to use today! (Please enter a number)\n"+ANSI_RESET);
		System.out.println("1 - View fridges");
		System.out.println("2 - Add fridge");
		System.out.println("3 - Remove fridge");
		System.out.println("4 - Give fridge premission to a health inspector");
		System.out.println("5 - Add food");
		System.out.println("6 - Remove food");
		System.out.println("7 - Transfer food");
//		System.out.println("8 - ");
		System.out.println("9 - go to login");
		System.out.println("0 - exit");
		
		
	}
	
	public static void printHealthInspectorMenu() {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(ANSI_YELLOW + "Which service you would like to use today! (Please enter a number)\n"+ANSI_RESET);
		System.out.println("1 - View fridges");
		System.out.println("2 - Remove food");
		System.out.println("9 - go to login");
		System.out.println("0 - exit");
		
		
	}

}
