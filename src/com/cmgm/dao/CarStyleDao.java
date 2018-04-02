package com.cmgm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.CarStyle;

/**
 *
 * @author Bio
 * @date 2018年4月2日
 * @time 下午3:56:02
 *
 */

@Repository
public class CarStyleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<CarStyle> getCarStyle(int pageNO, int pageSize) {
		String jpql = "SELECT new CarStyle(cs.id,cs.name) FROM CarStyle cs ";
		List<CarStyle> carStyles = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		return carStyles;
	}
	
	public Integer getCarStyle() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM CarStyle cs ");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public CarStyle getCarStyleById(Integer id) {
		return entityManager.find(CarStyle.class, id);
	}

	public CarStyle addOrUpdateCarStyle(CarStyle carStyle) {
		return entityManager.merge(carStyle);
	}

	public String deleteCarStyle(Integer id) {
		String candelete = "false";
		CarStyle carStyle = entityManager.find(CarStyle.class, id);
		if (carStyle != null) {
			if (carStyle.getCars().size()<1) {
				candelete = "true";
				entityManager.remove(carStyle);
			}
		}
		return candelete;
	}

	
	
	
}
