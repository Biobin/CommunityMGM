package com.cmgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmgm.VO.CarVO;
import com.cmgm.dao.CarDao;

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

	public List<CarVO> getCars(int pageNO, int pageSize) {
		return carDao.getCars(pageNO,pageSize);
	}
	
}
