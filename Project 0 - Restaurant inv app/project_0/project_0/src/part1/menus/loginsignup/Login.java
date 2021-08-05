package part1.menus.loginsignup;

import part1.accounts.Accounts;
import part1.utilities.PersistData;
import part1.utilities.PrintMenus;
import part1.utilities.ValidInput;

public class Login {
	public static Accounts login() {
		//#########################################################################################
		//################################ CHECK USERNAME #########################################
		//#########################################################################################
		System.out.println("\n----------------------------------------------------------------------");
		System.out.println(PrintMenus.ANSI_YELLOW + "Please enster your username:" + PrintMenus.ANSI_RESET);
		String user = "";
		for (int i = 1; i <= 5; i++) {
			user = ValidInput.returnValid(1, 2, 25);
			if (user == null) {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				continue;
			} else if (PersistData.userList.contains(user)) {
				break;
			} else if (!PersistData.userList.contains(user)) {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Too many wrong attemps. Going back to main menu"
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(
						PrintMenus.ANSI_RED + "No matching username. Attemp " + i + " of " + 5 + PrintMenus.ANSI_RESET);
			} else
				return null;
		}
		Accounts checkAccount = null;
		for (Accounts temp : PersistData.accountList) {
			if (temp.checkUsername(user))
				checkAccount = temp;
		}
		if (checkAccount == null)
			return null;
		//#########################################################################################
		//################################ CHECK PASSWORD #########################################
		//#########################################################################################
		System.out.println(PrintMenus.ANSI_YELLOW + "Please enster your password:" + PrintMenus.ANSI_RESET);
		String pass = "";
		for (int i = 1; i <= 5; i++) {
			pass = ValidInput.returnValid(1, 4, 25);// --------------------------------------------------------Change length of password input required
			if (pass == null) {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Incorrect password. Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				continue;
			} else if (checkAccount.checkPassword(pass)) {
				return checkAccount;
			} else {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Too many wrong attemps. Going back to main menu"
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Incorrect password. Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				
			}
		}

		return null;
	}

}
