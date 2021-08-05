package part1;

import part1.menus.MainMenu;
import part1.utilities.PersistData;
import part1.utilities.PersistJDBC;
import part1.utilities.PrintMenus;

public class ApplicationDriver {
	
	public static void main(String[] args) {
		ApplicationDriver.initializeVars();
//		printUsers();
		PrintMenus.printAppName();
		MainMenu.mainMenuDriver();
		
	}
	
	public static void printUsers() {
		PersistData.userList.forEach(temp -> {System.out.println(temp);});
	}
	
	
	public static void initializeVars() {
		PersistData.accountList.addAll(PersistJDBC.selectAllRestaurants());
		PersistData.accountList.addAll(PersistJDBC.selectAllHealthInspectors());
		PersistData.fridgeList.addAll(PersistJDBC.selectAllFridges());
		PersistJDBC.selectAllFood();
		PersistJDBC.selectAllHealthInspectorFridges();
//		System.out.println(PersistData.accountList.toString());
//		System.out.println(PersistData.fridgeList.toString());

	}

	
}
