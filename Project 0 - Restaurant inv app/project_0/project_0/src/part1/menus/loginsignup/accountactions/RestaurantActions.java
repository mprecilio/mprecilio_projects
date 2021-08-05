package part1.menus.loginsignup.accountactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import part1.accounts.Accounts;
import part1.accounts.HealthInspector;
import part1.accounts.Restaurant;
import part1.fridge.Fridge;
import part1.utilities.PersistData;
import part1.utilities.PrintMenus;
import part1.utilities.ValidInput;

public class RestaurantActions {

	public static String restaurantActions(Accounts currUser) {
		String userAction = "";
		List<String> validInputs = new ArrayList<>();
		validInputs.add("1");
		validInputs.add("2");
		validInputs.add("3");
		validInputs.add("4");
		validInputs.add("5");
		validInputs.add("6");
		validInputs.add("7");
		validInputs.add("9");
		validInputs.add("0");
		int count = 1;
		int numAllowedAttempts = 5;
		do {
			PrintMenus.printRestaurantMenu();
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
				case "2":// add fridge
					addFridge(currUser);
					break;
				case "3":// remove fridge
					removeFridge(currUser);
					continue;
				case "4":// give permissions to fridge
					givePermission(currUser);
					continue;
				case "5":// add food
					addFood(currUser);// String does nothing right now
					continue;
				case "6":// remove food
					removeFood(currUser);
					continue;
				case "7":// transfer food
					transferFood(currUser);
					break;
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
		int counter = 0;
		for (Fridge temp : PersistData.fridgeList) {
			if (temp.getOwner().equals(currUser.getUsername())) {
				System.out.println(temp.toString());
				counter++;
			}
		}
		Scanner sc = new Scanner( System.in);
		String s = sc.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		if(counter != 0) return "this";
		if (counter == 0) {
			System.out.println();
			System.out.println(PrintMenus.ANSI_RED + "You have no fridges!" + PrintMenus.ANSI_RESET);
			return null;
		}
		return "this";
	}

	private static void addFridge(Accounts currUser) {
		System.out.println(PrintMenus.ANSI_CYAN + "\nFridge added!" + PrintMenus.ANSI_RESET);
		PersistData.fridgeList.add(new Fridge(currUser.getUsername()));
		PersistData.writeFridge();
	}

	private static String addFood(Accounts currUser) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(PrintMenus.ANSI_YELLOW
				+ "Which Fridge would you like to add food to? (Red fridges are full)\n" + PrintMenus.ANSI_RESET);
		addFoodView(currUser);
		String userIn = ValidInput.returnValid(5, 1);
		Fridge inUse = findFridge(userIn, currUser);
		if (inUse == null) {
			System.out.println(PrintMenus.ANSI_RED + "Invalid input! Returning to the menu." + PrintMenus.ANSI_RESET);
			return null;// invalid input
		} else if (inUse.numInFridge() >= 3) {
			System.out.println(
					PrintMenus.ANSI_RED + "The fridge is already full! Returning to the menu." + PrintMenus.ANSI_RESET);
			return null;
		} else {
			System.out.println(PrintMenus.ANSI_YELLOW + "What food would you like to add?\n" + PrintMenus.ANSI_RESET);
			String addFood = ValidInput.returnValid(5, 1);
			if (addFood == null)
				return null;
			inUse.addFood(addFood);
			PersistData.writeFridge();
		}

		return null;

	}

	private static void addFoodView(Accounts currUser) {
		PersistData.fridgeList.forEach(temp -> {
			if (temp.getOwner().equals(currUser.getUsername())) {
				if (temp.numInFridge() < 3)
					System.out.println(temp.toString());
				else
					System.out.println(PrintMenus.ANSI_RED + temp.toString() + PrintMenus.ANSI_RESET);
			}
		});
		System.out.println();
	}

	private static Fridge findFridge(String userIn, Accounts currUser) {
		String tempId = "";
		for (Fridge temp : PersistData.fridgeList) {
			tempId = "" + temp.getUniqueId();
			if (tempId.equals(userIn)) {
				if (temp.getOwner().equals(currUser.getUsername()))
					return temp;
			}
		}
		return null;
	}

	private static String removeFood(Accounts currUser) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(
				PrintMenus.ANSI_YELLOW + "Which Fridge would you like to remove food from? \n" + PrintMenus.ANSI_RESET);
		viewFridges(currUser);
		String userIn = ValidInput.returnValid(5, 1);
		Fridge inUse = findFridge(userIn, currUser);
		if (inUse == null) {
			System.out.println(PrintMenus.ANSI_RED + "Invalid input! Returning to the menu." + PrintMenus.ANSI_RESET);
			return null;// invalid input
		} else {
			if (inUse.getFood().size() > 0) {
				System.out.println(
						PrintMenus.ANSI_YELLOW + "What food would you like to remove?\n" + PrintMenus.ANSI_RESET);
				String removeFood = ValidInput.returnValid(5, 1);
				if (removeFood == null) {
					System.out.println(PrintMenus.ANSI_RED + "Too many invalid input! Returning to the menu."
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				for (int i = 0; i < inUse.getFood().size(); i++) {
					if (inUse.getFood().get(i).toLowerCase().equals(removeFood.toLowerCase())) {
						inUse.getFood().remove(i);
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

	private static String transferFood(Accounts currUser) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(PrintMenus.ANSI_YELLOW + "Which Fridge would you like to transfer food from? \n"
				+ PrintMenus.ANSI_RESET);
		addFoodView(currUser);
		String userIn = ValidInput.returnValid(5, 1);
		Fridge inUse = findFridge(userIn, currUser);
		if (inUse == null) {
			System.out.println(PrintMenus.ANSI_RED + "Invalid input! Returning to the menu." + PrintMenus.ANSI_RESET);
			return null;// invalid input
		} else {
			if (inUse.getFood().size() > 0) {
				System.out.println(
						PrintMenus.ANSI_YELLOW + "What food would you like to transfer?\n" + PrintMenus.ANSI_RESET);
				String transferFood = ValidInput.returnValid(5, 1);
				if (transferFood == null)
					return null;
				boolean b = false;
				for (int i = 0; i < inUse.getFood().size(); i++) {
					if (inUse.getFood().get(i).toLowerCase().equals(transferFood.toLowerCase())) {
						b = true;
						break;
					}
				}
				if (b == false) {
					System.out.println(PrintMenus.ANSI_RED + "No food with that name! Returning to the menu."
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				// -------------------------------------------------------------------------------------------------------------------------
				System.out.println(PrintMenus.ANSI_YELLOW
						+ "Which Fridge would you like to transfer the food to? (Red fridges are full)\n"
						+ PrintMenus.ANSI_RESET);
				addFoodView(currUser);
				String userIn2 = ValidInput.returnValid(5, 1);
				Fridge inUse2 = findFridge(userIn2, currUser);
				if (inUse2 == null) {
					System.out.println(
							PrintMenus.ANSI_RED + "Invalid input! Returning to the menu." + PrintMenus.ANSI_RESET);
					return null;// invalid input
				} else if (inUse.getUniqueId() == inUse2.getUniqueId()) {
					System.out.println(PrintMenus.ANSI_RED
							+ "Cannot transfer to the same fridge! Returning to the menu." + PrintMenus.ANSI_RESET);
					return null;
				} else if (inUse2.numInFridge() >= 3) {
					System.out.println(PrintMenus.ANSI_RED + "The fridge is already full! Returning to the menu."
							+ PrintMenus.ANSI_RESET);
					return null;
				} else {
					System.out.println("im here");
					inUse2.addFood(transferFood);
					for (int i = 0; i < inUse.getFood().size(); i++) {
						if (inUse.getFood().get(i).toLowerCase().equals(transferFood.toLowerCase())) {
							inUse.getFood().remove(i);
							break;
						}
					}
					PersistData.writeFridge();
				}

			} else {
				System.out.println(PrintMenus.ANSI_RED + "Fridge contains no items." + PrintMenus.ANSI_RESET);
			} // ----------------------------------------------------------------------------------------------------------------------------
		}

		return null;
	}

	private static String removeFridge(Accounts currUser) {
		System.out.println("----------------------------------------------------------------------");
		///
		///
		int fridgeCount = 0;
		for (Fridge temp : PersistData.fridgeList) {
			if (temp.getOwner().equals(currUser.getUsername()))
				fridgeCount++;
		}
		if (fridgeCount == 0) {
			System.out.println(PrintMenus.ANSI_RED + "You have no fridges to remove! Returning to the menu."
					+ PrintMenus.ANSI_RESET);

		}
		///
		///
		System.out.println(PrintMenus.ANSI_YELLOW
				+ "Which Fridge would you like to remove?(If you have no fridges, press enter to return to previous menu) \n"
				+ PrintMenus.ANSI_RESET);
		addFoodView(currUser);
		String userIn = ValidInput.returnValid(5, 1);
		Fridge inUse = findFridge(userIn, currUser);
		if (inUse == null) {
			System.out.println(PrintMenus.ANSI_RED + "Invalid input! Returning to the menu." + PrintMenus.ANSI_RESET);
			return null;// invalid input
		} else if (inUse.getFood().size() == 0) {
			PersistData.fridgeList.remove(inUse);
			PersistData.writeFridge();
			System.out.println(
					PrintMenus.ANSI_CYAN + "Fridge has been deleted! 0 items were lost." + PrintMenus.ANSI_RESET);
			return null;
		} else {
			//temp is the fridge to transfer to
			//inUse is fridge being removed
			for (Fridge temp : PersistData.fridgeList) {
				if ((temp.getOwner().equals(currUser.getUsername())) && !("" + temp.getUniqueId()).equals(userIn))
					while (temp.getFood().size() < 3) {
						System.out.println(PrintMenus.ANSI_PURPLE + inUse.getFood().get(0)
								+ " has been transfered to fridge " + temp.getUniqueId() + PrintMenus.ANSI_RESET);
						temp.addFood(inUse.getFood().get(0));
						inUse.getFood().remove(0);
						PersistData.writeFridge();
						if (inUse.getFood().size() == 0) {
							System.out.println(PrintMenus.ANSI_CYAN + "Fridge "+ inUse.getUniqueId() + " has been deleted! 0 items were lost."
									+ PrintMenus.ANSI_RESET);
							PersistData.fridgeList.remove(inUse);
							PersistData.writeFridge();
							return null;
						}
					}
			}
			int numDeleted = inUse.getFood().size();
			for (int n = 0; n < numDeleted; n++) {
				System.out.println(PrintMenus.ANSI_RED + "*" + inUse.getFood().get(n) + " has been deleted!"
						+ PrintMenus.ANSI_RESET);
			}
			System.out.println(PrintMenus.ANSI_CYAN + "Fridge "+ inUse.getUniqueId() +" has been deleted! " + numDeleted + " item(s were lost."
					+ PrintMenus.ANSI_RESET);
			PersistData.fridgeList.remove(inUse);
			PersistData.writeFridge();
			return null;

		}
	}

	private static String givePermission(Accounts currUser) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println(PrintMenus.ANSI_YELLOW
				+ "Please enter the username of the Health Inspector you would like to give permission to? \n"
				+ PrintMenus.ANSI_RESET);
		String userIn = "";//Valid health inspector
		first:
		for (int i = 1; i <= 5; i++) {
			userIn = ValidInput.returnValid(1, 2, 25);
			if (userIn == null) {
				if (i == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Attempt " + i + " of " + 5 + ". Returning to the menu"
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				continue;
			} else if (!PersistData.userList.contains(userIn)) {
				System.out.println(
						PrintMenus.ANSI_RED + "User DOES NOT exist! Attempt " + i + " of " + 5 + PrintMenus.ANSI_RESET);
				continue;
			} else {
				for (Accounts temp : PersistData.accountList) {
					if (temp.getUsername().equals(userIn) && (temp instanceof Restaurant)) {
						System.out.println(PrintMenus.ANSI_RED + "User entered is NOT a Health inspector! Attempt " + i
								+ " of " + 5 + PrintMenus.ANSI_RESET);
						continue;
					} else {
						break first;
					}
				}
				continue;
			}
		}

		System.out.println(PrintMenus.ANSI_YELLOW + "\nWhich fridge would you like to give permission to? "
				+ PrintMenus.ANSI_RESET);
		String check = viewFridges(currUser);
		if(check == null) {
			System.out.println();
			System.out.println(PrintMenus.ANSI_RED + "Returning to the menu. Baka!"
					+ PrintMenus.ANSI_RESET);
			return null;
		}
		System.out.println();
		for (int n = 0; n <= 5; n++) {
			String userIn2 = ValidInput.returnValid(5, 1); //get fridge id
			Fridge inUse = findFridge(userIn2, currUser);
			if (inUse == null) {
				if (n == 5) {
					System.out.println(PrintMenus.ANSI_RED + "Invalid input. Attempt " + n + " of " + 5 + ". Returning to the menu"
							+ PrintMenus.ANSI_RESET);
					return null;
				}
				System.out.println(PrintMenus.ANSI_RED + "Invalid input. Attempt " + n + " of " + 5 + ". Returning to the menu"
						+ PrintMenus.ANSI_RESET);
				continue;
			}else {
				for (Accounts temp : PersistData.accountList) {
//					System.out.println(temp.getUsername().toLowerCase() +":"+userIn.toLowerCase()+(temp instanceof HealthInspector));
					if (temp.getUsername().toLowerCase().equals(userIn.toLowerCase()) && (temp instanceof HealthInspector)) {
						HealthInspector tempInspector = (HealthInspector) temp;
//						if(tempInspector.hasPermission(covertToInt(userIn2))) {
//							System.out.println();
//							System.out.println(PrintMenus.ANSI_RED + temp.getUsername() + " already has access to fridge " + userIn2 + PrintMenus.ANSI_RESET);
//							return null;
//						}
						tempInspector.addViewableFridge(covertToInt(userIn2));
						System.out.println();
						System.out.println(PrintMenus.ANSI_CYAN + "Permissions granted to " +temp.getUsername() + " for fridge " + userIn2 + PrintMenus.ANSI_RESET);
						PersistData.writeAccounts();
						return "";
					}
				}
				System.out.println(PrintMenus.ANSI_RED + "An error occured when trying to grant permission to health inspector"
						+ PrintMenus.ANSI_RESET);
				return null;
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
