package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.CarVO;
import com.cmgm.dao.CarDao;
import com.cmgm.entity.Car;
import com.cmgm.entity.CarStyle;
import com.cmgm.entity.Owner;

/**
 *
 * @author Bio
 * @date   2018年4月8日
 * @time   上午1:02:11
 *
 */

@Service
public class CarService {

	@Autowired
	private CarDao carDao;

	@Transactional(readOnly=true)
	public List<Owner> getOwnerList() {
		return carDao.getOwnerList();
	}
	
	@Transactional(readOnly=true)
	public List<CarStyle> getCarStyleList() {
		return carDao.getCarStyleList();
	}
	
	@Transactional(readOnly=true)
	public List<Car> getPlateNumberList(Integer ownerId) {
		return carDao.getPlateNumberList(ownerId);
	}

	@Transactional(readOnly=true)
	public List<CarVO> getCars(int pageNO, int pageSize) {
		return carDao.getCars(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountCar() {
		return carDao.getCountCar();
	}

	@Transactional
	public void addCar(Map<String, Object> params) {
		carDao.addCar(params);
	}

	@Transactional(readOnly=true)
	public CarVO getCar(Integer id) {
		return carDao.getCar(id);
	}

	@Transactional
	public void updateCar(Map<String, Object> params) {
		carDao.updateCar(params);
	}

	@Transactional
	public void deleteCar(Integer id) {
		carDao.deleteCar(id);
	}

	@Transactional(readOnly=true)
	public CarVO getOwnerVOByOwnerId(Integer ownerId) {
		return carDao.getOwnerVOByOwnerId(ownerId);
	}

}
