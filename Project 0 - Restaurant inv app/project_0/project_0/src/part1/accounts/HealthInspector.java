package part1.accounts;

import java.util.ArrayList;
import java.util.TreeSet;

public class HealthInspector extends Accounts {

	/**
	 * 
	 */
	private static final long serialVersionUID = 98654923645L;
	private TreeSet<Integer> viewableFridges;

	public HealthInspector(String username, String password) {
		super(username, password);
		if(this.viewableFridges == null) viewableFridges = new TreeSet<>();
	}
	
	public void addViewableFridge(int fridgeId) {
		viewableFridges.add(fridgeId);
	}

	public TreeSet<Integer> getViewableFridges() {
		return viewableFridges;
	}
	
	public void removeViewable(Integer removeId) {
		this.viewableFridges.remove(removeId);
	}

	public boolean hasPermission(Integer i) {
		return viewableFridges.contains(i);
	}
}
