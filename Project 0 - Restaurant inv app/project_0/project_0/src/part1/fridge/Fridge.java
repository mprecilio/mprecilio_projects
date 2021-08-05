package part1.fridge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fridge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 98714982441L;
	//add fridge unique id var
	private static int counter = 0;
	private int uniqueId;
	private final String owner;
	private List<String> food;

	public Fridge(String owner) {
		super();
		this.owner = owner;
		counter++;
		uniqueId = counter;
		this.food = new ArrayList<>();
		
	}
	
	//returns the number of items stored in the fridge
	public int numInFridge() {
		return food.size();
	}
	
	//add foodItem to food List
	public void addFood(String foodItem) {
		food.add(foodItem);
	}
	
	public static int getCounter() {
		return counter;
	}
	
	public static void setCounter(int i) {
		counter = i;
	}
	
	public int getUniqueId() {
		return uniqueId;
	}
	
	
	
	



	
	

	@Override
	public String toString() {
		return "Fridge [uniqueId=" + uniqueId + ", owner=" + owner + ", food=" + food + "]";
	}

	public List<String> getFood() {
		return food;
	}

	public String getOwner() {
		return owner;
	}

	
}
