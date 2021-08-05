package example.service;

import java.util.List;

import example.model.SuperVillain;

public interface SuperVillainService {

	//READ
	public List<SuperVillain> getAllVillains();
	public SuperVillain getVillainById(SuperVillain sVill);
	public SuperVillain getVillainByName(SuperVillain sVill);
}
