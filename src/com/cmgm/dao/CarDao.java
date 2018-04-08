package com.cmgm.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.CarVO;

/**
 *
 * @author Bio
 * @date   2018年4月8日
 * @time   上午1:02:31
 *
 */

@Repository
public class CarDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<CarVO> getCars(int pageNO, int pageSize) {
		return null;
	}

	public Integer getCountCar() {
		return null;
	}

	public void addCar(Map<String, Object> params) {
		
	}

	public CarVO getCar(Integer id) {
		return null;
	}

	public void updateCar(Map<String, Object> params) {
		
	}

	public void deleteCar(Integer id) {
		
	}
	
}
