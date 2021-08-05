package example.dao;

import java.util.List;

import example.model.SuperVillain;
import example.model.SuperVillain;

public interface SuperVillainDao {
		//CRUD METHODS
	
		//CREATE
		public void insertVillain(SuperVillain sVill);
		public void insertVillainPower(SuperVillain sVill, String newPower);
		
		//READ
		public List<SuperVillain> selectAllVillains();
		public SuperVillain selectVillainById(SuperVillain sVill);
		public SuperVillain selectVillainByName(SuperVillain sVill);
}
