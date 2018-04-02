package com.cmgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.CarStyleDao;
import com.cmgm.entity.CarStyle;

/**
 *
 * @author Bio
 * @date 2018年4月2日
 * @time 下午3:55:42
 *
 */

@Service
public class CarStyleService {

	@Autowired
	private CarStyleDao carStyleDao;

	@Transactional(readOnly=true)
	public List<CarStyle> getCarStyle(int pageNO, int pageSize) {
		return carStyleDao.getCarStyle(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountCarStyle() {
		return carStyleDao.getCarStyle();
	}

	@Transactional(readOnly=true)
	public CarStyle getCarStyleById(Integer id) {
		return carStyleDao.getCarStyleById(id);
	}

	@Transactional
	public CarStyle addOrUpdateCarStyle(CarStyle carStyle) {
		return carStyleDao.addOrUpdateCarStyle(carStyle);
	}

	@Transactional
	public String deleteCarStyle(Integer id) {
		return carStyleDao.deleteCarStyle(id);
	}
	
}
