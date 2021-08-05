package part1.fridge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fridge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 98714982441L;

	private int uniqueId;
	private final String owner;
	private List<String> food;

	public Fridge(String owner) {
		super();
		this.owner = owner;
		this.food = new ArrayList<>();
		
	}
	
	public Fridge(int uniqueId, String owner) {
		super();
		this.uniqueId = uniqueId;
		this.owner = owner;
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
	
	
	public int getUniqueId() {
		return uniqueId;
	}
	

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
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
