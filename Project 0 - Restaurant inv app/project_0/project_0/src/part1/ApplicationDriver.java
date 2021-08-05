package part1;

import part1.fridge.Fridge;
import part1.menus.MainMenu;
import part1.utilities.PersistData;
import part1.utilities.PrintMenus;

public class ApplicationDriver {
	final static String ESC = "\u001B[0j";
	
	public static void main(String[] args) {
		ApplicationDriver.initializeVars();
//		printUsers();
		System.out.println(PersistData.fridgeList.toString());
		PrintMenus.printAppName();
		MainMenu.mainMenuDriver();

	}
	
	public static void printUsers() {
		PersistData.userList.forEach(temp -> {System.out.println(temp);});
	}
	
	
	public static void initializeVars() {
		PersistData.readAccounts();
		PersistData.readFridge();
		if(PersistData.fridgeList != null) {
			Fridge tempFridge = PersistData.fridgeList.get(PersistData.fridgeList.size()-1);
			Fridge.setCounter(tempFridge.getUniqueId());
		}
	}

	
}
