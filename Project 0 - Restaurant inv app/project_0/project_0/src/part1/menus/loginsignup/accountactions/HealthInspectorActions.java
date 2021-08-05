package part1.menus.loginsignup.accountactions;

import java.util.ArrayList;
import java.util.List;

import part1.accounts.Accounts;
import part1.accounts.HealthInspector;
import part1.fridge.Fridge;
import part1.utilities.PersistData;
import part1.utilities.PrintMenus;
import part1.utilities.ValidInput;

public class HealthInspectorActions {

	public static String healthInspectorActions(Accounts currUser) {
		String userAction = "";
		List<String> validInputs = new ArrayList<>();
		validInputs.add("1");
		validInputs.add("2");
		validInputs.add("9");
		validInputs.add("0");
		int count = 1;
		int numAllowedAttempts = 5;
		do {
			PrintMenus.printHealthInspectorMenu();
			userAction = ValidInput.returnValid(1, 1, 1);
			if (userAction == null || !validInputs.contains(userAction)) {
				System.out.println(PrintMenus.ANSI_RED + "Invalid input. Attemp " + count + " of " + numAllowedAttempts
						+ PrintMenus.ANSI_RESET);
				count++;
				if (count == numAllowedAttempts) {
					System.out.println(PrintMenus.ANSI_RED + "Too many invalid attempts. Returning to login."
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				continue;
			} else {
				count = 1;
				switch (userAction) {
				case "1": // view fridges
					System.out.println("----------------------------------------------------------------------");
					viewFridges(currUser);
					continue;
				case "2":// remove food
					removeFromFridge(currUser);
					continue;
				case "9":
					return null;
				case "0":
					return "0";

				}
			}
		} while (userAction != "9" && userAction != "0");

		return "0";
	}

	private static String viewFridges(Accounts currUser) {
		System.out.println();
		HealthInspector currInspector = (HealthInspector) currUser;
		
//		if (currInspector.getViewableFridges().size() == 0) {
//			System.out.println();
//			System.out.println(PrintMenus.ANSI_RED + "You do not have access to any fridges!" + PrintMenus.ANSI_RESET);
//			return null;
//		}
		
		for(Integer temp : currInspector.getViewableFridges()) {
			if(!PersistData.fridgeIdList.contains(temp)) currInspector.removeViewable(temp);
		}
		if (currInspector.getViewableFridges().size() == 0) {
			System.out.println();
			System.out.println(PrintMenus.ANSI_RED + "You do not have access to any fridges!" + PrintMenus.ANSI_RESET);
			return null;
		}

		for(Integer tempOuter : currInspector.getViewableFridges()) {
			for(Fridge tempInner : PersistData.fridgeList) {
				if(tempInner.getUniqueId() == tempOuter) System.out.println(tempInner.toString());
			}
		}
		System.out.println();

		return null;
	}
	
	private static String removeFromFridge(Accounts currUser) {
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println(
				PrintMenus.ANSI_YELLOW + "Which Fridge would you like to remove food from? " + PrintMenus.ANSI_RESET);
		viewFridges(currUser);
		String userIn = ValidInput.returnValid(5, 1);
		Integer isValid = covertToInt(userIn);
		if(isValid == null) {
			System.out.println(PrintMenus.ANSI_RED + "Invalid input! Returning to the menu." + PrintMenus.ANSI_RESET);
			return null;// invalid input
		}
		HealthInspector currHI = (HealthInspector) currUser;
		if (!currHI.hasPermission(isValid)) {
			System.out.println(PrintMenus.ANSI_RED + "Invalid input! Returning to the menu." + PrintMenus.ANSI_RESET);
			return null;// invalid input
		} else {
			Fridge inUse = null;
			for(Fridge temp: PersistData.fridgeList) {
				if(temp.getUniqueId() == isValid) inUse = temp;
			}
			if(inUse == null) {
				System.out.println(PrintMenus.ANSI_RED + "AN ERROR OCCURED! OH NO! Returning to the menu." + PrintMenus.ANSI_RESET);
				return null;// invalid input
			}
			if (inUse.getFood().size() > 0) {
				System.out.println(
						PrintMenus.ANSI_YELLOW + "\nWhat food would you like to remove?\n" + PrintMenus.ANSI_RESET);
				String removeFood = ValidInput.returnValid(5, 1);
				if (removeFood == null) {
					System.out.println(PrintMenus.ANSI_RED + "Too many invalid input! Returning to the menu."
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				for (int i = 0; i < inUse.getFood().size(); i++) {
					if (inUse.getFood().get(i).toLowerCase().equals(removeFood.toLowerCase())) {
						inUse.getFood().remove(i);
						System.out.println(PrintMenus.ANSI_PURPLE + "\n*" + removeFood + " was removed from fridge " + isValid + PrintMenus.ANSI_RESET);
						PersistData.writeFridge();
						return null;
					}
				}
			} else {
				System.out.println(PrintMenus.ANSI_RED + "Fridge contains no items." + PrintMenus.ANSI_RESET);
			}
		}

		
		return null;
	}
	
	
	private static Integer covertToInt(String s1) {
		List<Character> validNum = new ArrayList<>();
		validNum.add('1');
		validNum.add('2');
		validNum.add('3');
		validNum.add('4');
		validNum.add('5');
		validNum.add('6');
		validNum.add('7');
		validNum.add('8');
		validNum.add('9');
		validNum.add('0');
		int total = 0;
		for(int i = 0; i < s1.length(); i++) {
			if(!validNum.contains(s1.charAt(i))) return null;
		}
		for(int i = 0; i < s1.length(); i++) {
			total += (((int) Math.pow(10, i))*( ((int) s1.charAt(s1.length()-1-i)) -48));
		}
		return total;
	}

}
