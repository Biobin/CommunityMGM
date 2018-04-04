package com.cmgm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.PropertyManagerVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.PropertyManager;
import com.cmgm.entity.Role;
import com.cmgm.entity.User;

/**
 *
 * @author Bio
 * @date 2018年4月3日
 * @time 上午11:36:05
 *
 */

@Repository
public class PropertyManagerDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<PropertyManagerVO> getPropertyManagers(int pageNO, int pageSize) {
		String jpql = "SELECT pm.id, pm.name, pm.phone, pm.email, pu.id, pu.username, pu.password, pur.id, pur.name "
				+ "FROM PropertyManager pm LEFT JOIN pm.user pu LEFT JOIN pu.role pur ";
		Query query = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize);
		List<Object[]> propertyManagers  = query.getResultList();
		List<PropertyManagerVO> propertyManagerVOs = new ArrayList<>();
		PropertyManagerVO propertyManagerVO = null;
		for(Object[] propertyManager : propertyManagers) {
			propertyManagerVO = new PropertyManagerVO();
			propertyManagerVO.setId(StringUtils.getInteger(String.valueOf(propertyManager[0])));
			propertyManagerVO.setName(StringUtils.getString(String.valueOf(propertyManager[1])));
			propertyManagerVO.setPhone(StringUtils.getString(String.valueOf(propertyManager[2])));
			propertyManagerVO.setEmail(StringUtils.getString(String.valueOf(propertyManager[3])));
			propertyManagerVO.setUserId(StringUtils.getInteger(String.valueOf(propertyManager[4])));
			propertyManagerVO.setUsername(StringUtils.getString(String.valueOf(propertyManager[5])));
			propertyManagerVO.setPassword(StringUtils.getString(String.valueOf(propertyManager[6])));
			propertyManagerVO.setRoleId(StringUtils.getInteger(String.valueOf(propertyManager[7])));
			propertyManagerVO.setRoleName(StringUtils.getString(String.valueOf(propertyManager[8])));
			propertyManagerVOs.add(propertyManagerVO);
		}
		return propertyManagerVOs;
	}

	public Integer getCountPropertyManager() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM PropertyManager pm");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addPropertyManager(Map<String, Object> params) {
		PropertyManager propertyManager = new PropertyManager();
		User user = new User();
		Role role = entityManager.find(Role.class, 1);
		user.setUsername(params.get("username").toString());
		user.setPassword(params.get("password").toString());
		user.setRole(role);
		propertyManager.setName(params.get("name").toString());
		propertyManager.setPhone(params.get("phone").toString());
		propertyManager.setEmail(params.get("email").toString());
		propertyManager.setUser(user);
		entityManager.persist(user);
		entityManager.persist(propertyManager);
	}

	public void updatePropertyManager(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		PropertyManager propertyManager = entityManager.find(PropertyManager.class, id);
		User user = propertyManager.getUser();
		user.setUsername(params.get("username").toString());
		user.setPassword(params.get("password").toString());
		propertyManager.setName(params.get("name").toString());
		propertyManager.setPhone(StringUtils.getString(params.get("phone").toString()));
		propertyManager.setEmail(StringUtils.getString(params.get("email").toString()));
		propertyManager.setUser(user);
		entityManager.merge(user);
		entityManager.merge(propertyManager);
	}

	public void deletePropertyManager(Integer id) {
		PropertyManager propertyManager = entityManager.find(PropertyManager.class, id);
		if (propertyManager!=null) {
			User user = propertyManager.getUser();
			if (user!=null) {
				entityManager.remove(user);
			}
			entityManager.remove(propertyManager);
		}
	}

	public PropertyManagerVO getPropertyManagerById(Integer id) {
		String jpql = "SELECT pm.id, pm.name, pm.phone, pm.email, pu.id, pu.username, pu.password, pur.id, pur.name "
				+ "FROM PropertyManager pm LEFT JOIN pm.user pu LEFT JOIN pu.role pur WHERE pm.id = :id";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		PropertyManagerVO propertyManagerVO = new PropertyManagerVO();
		propertyManagerVO.setId(StringUtils.getInteger(String.valueOf(objects[0])));
		propertyManagerVO.setName(StringUtils.getString(String.valueOf(objects[1])));
		propertyManagerVO.setPhone(StringUtils.getString(String.valueOf(objects[2])));
		propertyManagerVO.setEmail(StringUtils.getString(String.valueOf(objects[3])));
		propertyManagerVO.setUserId(StringUtils.getInteger(String.valueOf(objects[4])));
		propertyManagerVO.setUsername(StringUtils.getString(String.valueOf(objects[5])));
		propertyManagerVO.setPassword(StringUtils.getString(String.valueOf(objects[6])));
		propertyManagerVO.setRoleId(StringUtils.getInteger(String.valueOf(objects[7])));
		propertyManagerVO.setRoleName(StringUtils.getString(String.valueOf(objects[8])));
		return propertyManagerVO;
	}
	
}
