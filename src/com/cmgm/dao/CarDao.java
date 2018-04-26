package com.cmgm.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.CarVO;
import com.cmgm.common.DateUtils;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Car;
import com.cmgm.entity.CarStyle;
import com.cmgm.entity.Owner;

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

	@SuppressWarnings("unchecked")
	public List<Owner> getOwnerList() {
		String jpql = "SELECT new Owner(o.id, o.name, o.phone, o.email) FROM Owner o ";
		List<Owner> owners = entityManager.createQuery(jpql).getResultList();
		return owners;
	}
	
	@SuppressWarnings("unchecked")
	public List<CarStyle> getCarStyleList() {
		String jqpl = "SELECT new CarStyle(cs.id,cs.name) FROM CarStyle cs ";
		List<CarStyle> carStyles = entityManager.createQuery(jqpl).getResultList();
		return carStyles;
	}
	
	@SuppressWarnings("unchecked")
	public List<Car> getPlateNumberList(Integer ownerId) {
		String jpql = "SELECT new Car(c.id,c.plateNumber) FROM Car c LEFT JOIN c.owner co WHERE co.id = :ownerId ";
		List<Car> plateNumbers = entityManager.createQuery(jpql).setParameter("ownerId", ownerId).getResultList();
		return plateNumbers;
	}
	
	@SuppressWarnings("unchecked")
	public List<CarVO> getCars(int pageNO, int pageSize, Integer ownerId, String plateNumber, Integer carStyleId) {
		String jqpl = "SELECT c.id, c.plateNumber, ccs.id, ccs.name, co.id, co.name, co.phone, co.email, to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') "
				+ "FROM Car c LEFT JOIN c.carStyle ccs LEFT JOIN c.owner co WHERE (co.id = :ownerId or :ownerId is null ) AND "
				+ "(c.plateNumber like :plateNumber or :plateNumber is null ) AND (ccs.id = :carStyleId or :carStyleId is null ) ";
		Query query = entityManager.createQuery(jqpl);
		plateNumber = plateNumber == null ? "" : plateNumber;
		query.setParameter("plateNumber", "%"+plateNumber+"%");
		query.setParameter("ownerId", ownerId);
		query.setParameter("carStyleId", carStyleId);
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<CarVO> carVOs = new ArrayList<>();
		CarVO carVO = null;
		for (Object[] object : objects) {
			carVO = new CarVO();
			carVO.setId(StringUtils.getInteger(object[0]));
			carVO.setPlateNumber(StringUtils.getString(object[1]));
			carVO.setCarStyleId(StringUtils.getInteger(object[2]));
			carVO.setCarStyleName(StringUtils.getString(object[3]));
			carVO.setOwnerId(StringUtils.getInteger(object[4]));
			carVO.setOwnerName(StringUtils.getString(object[5]));
			carVO.setOwnerPhone(StringUtils.getString(object[6]));
			carVO.setOwnerEmail(StringUtils.getString(object[7]));
			carVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[8]));
			carVOs.add(carVO);
		}
		return carVOs;
	}

	public Integer getCountCar() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM Car c ");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addCar(Map<String, Object> params) {
		Car car = new Car();
		Integer ownerId = StringUtils.getInteger(params.get("ownerId"));
		Integer carStyleId = StringUtils.getInteger(params.get("carStyleId"));
		Owner owner = null;
		CarStyle carStyle = null;
		if (ownerId != null) {
			owner  = entityManager.find(Owner.class, ownerId);
		}
		car.setOwner(owner);
		if (carStyleId != null) {
			carStyle = entityManager.find(CarStyle.class, carStyleId);
		}
		car.setCarStyle(carStyle);
		car.setPlateNumber(StringUtils.getString(params.get("plateNumber")));
		String createTime = params.get("createTime").toString();
		Date date = null;
		if (createTime !=null && !createTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		car.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		entityManager.persist(car);
	}

	public CarVO getCar(Integer id) {
		String jpql = "SELECT c.id, c.plateNumber, ccs.id, ccs.name, co.id, co.name, co.phone, co.email, to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') " + 
				"FROM Car c LEFT JOIN c.carStyle ccs LEFT JOIN c.owner co WHERE c.id = :id ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		CarVO carVO = new CarVO();
		carVO.setId(StringUtils.getInteger(objects[0]));
		carVO.setPlateNumber(StringUtils.getString(objects[1]));
		carVO.setCarStyleId(StringUtils.getInteger(objects[2]));
		carVO.setCarStyleName(StringUtils.getString(objects[3]));
		carVO.setOwnerId(StringUtils.getInteger(objects[4]));
		carVO.setOwnerName(StringUtils.getString(objects[5]));
		carVO.setOwnerPhone(StringUtils.getString(objects[6]));
		carVO.setOwnerEmail(StringUtils.getString(objects[7]));
		carVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[8]));
		return carVO;
	}

	public void updateCar(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Car car = entityManager.find(Car.class, id);
		Integer ownerId = StringUtils.getInteger(params.get("ownerId"));
		Integer carStyleId = StringUtils.getInteger(params.get("carStyleId"));
		Owner owner = null;
		CarStyle carStyle = null;
		if (ownerId != null) {
			owner  = entityManager.find(Owner.class, ownerId);
		}
		car.setOwner(owner);
		if (carStyleId != null) {
			carStyle = entityManager.find(CarStyle.class, carStyleId);
		}
		car.setCarStyle(carStyle);
		car.setPlateNumber(StringUtils.getString(params.get("plateNumber")));
		String createTime = params.get("createTime").toString();
		Date date = null;
		if (createTime !=null && !createTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		car.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		entityManager.merge(car);
	}

	public void deleteCar(Integer id) {
		Car car = entityManager.find(Car.class, id);
		if (car!=null) {
			entityManager.remove(car);
		}
	}

	public CarVO getOwnerVOByOwnerId(Integer ownerId) {
		String jpql = "SELECT o.phone, o.email FROM Owner o WHERE o.id = :ownerId";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("ownerId", ownerId).getSingleResult();
		CarVO carVO = new CarVO();
		carVO.setOwnerId(ownerId);
		carVO.setOwnerPhone(StringUtils.getString(objects[0]));
		carVO.setOwnerEmail(StringUtils.getString(objects[1]));
		return carVO;
	}

}
