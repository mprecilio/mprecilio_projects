package part1.menus;

import part1.ApplicationDriver;
import part1.accounts.Accounts;
import part1.accounts.HealthInspector;
import part1.accounts.Restaurant;
import part1.fridge.Fridge;
import part1.menus.loginsignup.Login;
import part1.menus.loginsignup.SignUp;
import part1.menus.loginsignup.accountactions.HealthInspectorActions;
import part1.menus.loginsignup.accountactions.RestaurantActions;
import part1.utilities.PersistData;
import part1.utilities.PrintMenus;
import part1.utilities.ValidInput;

public class MainMenu {
	
	public static void mainMenuDriver() {
		//used to determine if we continue or exit
		String userIn = "";
		int incorrectEntryCounter = 1;
		//continue running the program until either too many invalid entries or until the user wants to exit
		do {
			PrintMenus.printMainMenu();
			userIn = ValidInput.returnValid(10, 1);
			if(userIn == null) {
				System.out.println(PrintMenus.ANSI_RED + "\nToo many invalid attemps. Exiting program." + PrintMenus.ANSI_RESET);
				break;
			}
			userIn = userIn.toLowerCase();
			if(userIn.equals("exit") || userIn.equals("0")) continue;//--------------------------------------------------------------------------------------If the user wants to exit
			else if(userIn.equals("1") || userIn.equals("2") || (userIn.toLowerCase()).equals("login") || (userIn.toLowerCase()).equals("sign up")) {
				incorrectEntryCounter = 1;//-------------------------------------------Reset number of invalid entries
				//LOGIN
				if(userIn.equals("1") || userIn.equals("login")){//------------------------------------------------------------------------------------------If the user wants to login
					Accounts currUser = Login.login();
					if(currUser == null) continue;
					//System.out.println(currUser.toString());
					String nextAction = null;
					if(currUser instanceof Restaurant) {
						String userAction = RestaurantActions.restaurantActions(currUser);
						if(userAction == null) continue;
						else if(userAction == "0") {
							userIn = userAction;
							continue;
						}
						
					}else if(currUser instanceof HealthInspector) {
						String userAction = HealthInspectorActions.healthInspectorActions(currUser);
						if(userAction == null) continue;
						else if(userAction == "0") {
							userIn = userAction;
							continue;
						}
						
					}else
						continue;
					

				}else if(userIn.equals("2") || userIn.equals("sign up")){//----------------------------------------------------------------------------------If the user wants to sign up
					Accounts currUser = SignUp.signUp();
					if(currUser == null) continue;
					PersistData.accountList.add(currUser);
					PersistData.writeAccounts();
					PersistData.readAccounts();
					//System.out.println(currUser.toString());
					String nextAction = null;
					if(currUser instanceof Restaurant) {
						String userAction = RestaurantActions.restaurantActions(currUser);
						if(userAction == null) continue;
						else if(userAction == "0") {
							userIn = userAction;
							continue;
						}
					}else if(currUser instanceof HealthInspector) {
						String userAction = HealthInspectorActions.healthInspectorActions(currUser);
						if(userAction == null) continue;
						else if(userAction == "0") {
							userIn = userAction;
							continue;
						}	
					}else
						continue;
					
				}else {//--------------------------------------------------------------Edge case invalid input got through
					System.out.println(PrintMenus.ANSI_RED + "An error occured in the main menu driver!" +PrintMenus.ANSI_RESET);
					break;
				}
			}else {//------------------------------------------------------------------Track number of and handle invalid entry attempts
				System.out.println(PrintMenus.ANSI_RED + "Invalid entry. Attempt " + incorrectEntryCounter + " of 5." + PrintMenus.ANSI_RESET);
				if(incorrectEntryCounter == 5) System.out.println(PrintMenus.ANSI_RED + "Too many invalid attemps. Exiting program." + PrintMenus.ANSI_RESET);
				incorrectEntryCounter++;
			}
		} while (!userIn.equals("exit") && !userIn.equals("0") && incorrectEntryCounter <6);
		System.out.println(PrintMenus.ANSI_RED + "\nExiting the program." + PrintMenus.ANSI_RESET);
	}
	
}
