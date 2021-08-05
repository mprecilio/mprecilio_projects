package part1.menus.loginsignup;

import part1.accounts.Accounts;
import part1.accounts.HealthInspector;
import part1.accounts.Restaurant;
import part1.utilities.PersistData;
import part1.utilities.PersistJDBC;
import part1.utilities.PrintMenus;
import part1.utilities.ValidInput;

public class SignUp {

	public static Accounts signUp() {
		System.out.println("\n----------------------------------------------------------------------");
		String accType = "";
		System.out.println(
				PrintMenus.ANSI_YELLOW + "Which type of account would you like to create? (please select a number)" + PrintMenus.ANSI_RESET);
		System.out.println("\n1 - Restaurant\n2 - Health Inspector\n0 - Cancel");
		for (int i = 1; i <= 5; i++) {
			accType = ValidInput.returnValid(1, 1, 25);
			if (accType == null) {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Attemp " + i + " of " + 5 + PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				continue;
			} else if (accType.equals("0") || (accType.toLowerCase()).equals("resaurant"))
				return null;
			else if (accType.equals("1") || (accType.toLowerCase()).equals("resaurant"))
				break;
			else if (accType.equals("2") || (accType.toLowerCase()).equals("health inspector"))
				break;
			else if (accType != null) {
				System.out.println(PrintMenus.ANSI_RED + "Invalid entry. Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
			}
		}
		System.out.println(PrintMenus.ANSI_YELLOW + "Please choose your username:" + PrintMenus.ANSI_RESET);
		String user = "";
		for (int i = 1; i <= 5; i++) {
			user = ValidInput.returnValid(1, 2, 25);
			if (user == null) {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Attemp " + i + " of " + 5 + PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Attemp " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				continue;
			} else if (PersistData.userList.contains(user)) {
				System.out.println(PrintMenus.ANSI_RED + "That username is taken!" + PrintMenus.ANSI_RESET);
				System.out.println(PrintMenus.ANSI_RED + "Attemp " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Exceded number of attempts. Returning to login." + PrintMenus.ANSI_RESET);
					return null;
				}
				continue;
			} else if (!PersistData.userList.contains(user)) {
				break;
			} else
				return null;
		}
		System.out.println(PrintMenus.ANSI_YELLOW + "Please choose a password:" + PrintMenus.ANSI_RESET);
		String pass = "";
		for (int i = 1; i <= 5; i++) {
			pass = ValidInput.returnValid(1, 4, 25);
			if (pass == null) {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Attemp " + i + " of " + 5 + PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Attemp " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				continue;
			} else if (pass != null) 
				break;
			
		}
		if (accType.equals("1") || (accType.toLowerCase()).equals("resaurant")) {
			PersistJDBC.newRestaurant(user, pass);
			return new Restaurant(user,pass);
		}
		else if (accType.equals("2") || (accType.toLowerCase()).equals("health inspector")) {
			PersistJDBC.newHealthInspector(user, pass);
			return new HealthInspector(user,pass);
		}
		return null;
	}

}
