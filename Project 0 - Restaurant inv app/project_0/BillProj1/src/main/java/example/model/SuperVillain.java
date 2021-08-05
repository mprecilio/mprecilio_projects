package example.model;

import java.util.ArrayList;
import java.util.List;

public class SuperVillain {
	private int id;
	private String name;
	private List<String> superpower;
	private int bounty;
	
	{
		superpower = new ArrayList<String>();
	}
	
	public SuperVillain() {
	}

	public SuperVillain(int id, String name, List<String> superpower, int bounty) {
		super();
		this.id = id;
		this.name = name;
		this.superpower = superpower;
		this.bounty = bounty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSuperpower() {
		return superpower;
	}

	public void setSuperpower(List<String> superpower) {
		this.superpower = superpower;
	}

	public int getBounty() {
		return bounty;
	}

	public void setBounty(int bounty) {
		this.bounty = bounty;
	}

	@Override
	public String toString() {
		return "\nSuperVillainDB [id=" + id + ", name=" + name + ", superpower=" + superpower + ", bounty=" + bounty + "]";
	}
}
