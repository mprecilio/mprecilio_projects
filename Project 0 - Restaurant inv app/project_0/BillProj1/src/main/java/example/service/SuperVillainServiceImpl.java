package example.service;

import java.util.List;

import example.dao.SuperVillainDao;
import example.dao.SuperVillainDaoImpl;
import example.model.SuperVillain;

public class SuperVillainServiceImpl implements SuperVillainService {

	SuperVillainDao myDao = new SuperVillainDaoImpl();
	
	@Override
	public List<SuperVillain> getAllVillains() {
		return myDao.selectAllVillains();
	}

	@Override
	public SuperVillain getVillainById(SuperVillain sVill) {
		return myDao.selectVillainById(sVill);
	}

	@Override
	public SuperVillain getVillainByName(SuperVillain sVill) {
		return myDao.selectVillainByName(sVill);
	}

}
