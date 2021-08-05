package part1.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import part1.accounts.Accounts;
import part1.fridge.Fridge;

public class PersistData {
	private static final String accFilepath = "./accounts.txt";
	private static final String fridgeFilepath = "./fridges.txt";
	
	public static List<Accounts> accountList = new ArrayList<>();
	public static List<Fridge> fridgeList = new ArrayList<>();
	
	public static TreeSet<String> userList = new TreeSet<>();
	public static TreeSet<Integer> fridgeIdList = new TreeSet<>();
	
	//Writes account list to text file
	public static void writeAccounts() {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accFilepath))){
			
			oos.writeObject(accountList); //serialization
			//readAccounts();//---------------------------------------JUST ADDED. UPDATES user ID LIST WHEN FRIDGES ARE ADDED
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Reads accounts list from text file
	@SuppressWarnings("unchecked")
	public static void readAccounts() {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accFilepath));) {
			accountList = (ArrayList<Accounts>) ois.readObject();
			accountList.forEach(temp ->{ userList.add(temp.getUsername());});
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//Writes fridge list to text file
	public static void writeFridge() {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fridgeFilepath))){
			
			oos.writeObject(fridgeList); //serialization
			//readFridge();//---------------------------------------JUST ADDED. UPDATES FRIDGE ID LIST WHEN FRIDGES ARE ADDED
			fridgeList.forEach(temp ->{ fridgeIdList.add(temp.getUniqueId());});
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//reads fridge list from text file
	@SuppressWarnings("unchecked")
	public static void readFridge() {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fridgeFilepath));) {
			fridgeList = (ArrayList<Fridge>) ois.readObject(); //de-serialization
			fridgeList.forEach(temp ->{ fridgeIdList.add(temp.getUniqueId());});
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	


}
