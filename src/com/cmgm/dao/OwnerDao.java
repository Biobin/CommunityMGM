package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date 2018年4月4日
 * @time 下午3:14:44
 *
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.OwnerVO;
import com.cmgm.VO.PropertyManagerVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Owner;
import com.cmgm.entity.Role;
import com.cmgm.entity.User;

@Repository
public class OwnerDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<OwnerVO> getOwners(int pageNO, int pageSize) {
		String jpql = "SELECT ";
		Query query = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize);
		List<Object[]> owners  = query.getResultList();
		List<OwnerVO> ownerVOs = new ArrayList<>();
		OwnerVO ownerVO = null;
		for(Object[] propertyManager : owners) {
			ownerVO = new OwnerVO();
			ownerVO.setId(StringUtils.getInteger(String.valueOf(propertyManager[0])));
			ownerVO.setName(StringUtils.getString(String.valueOf(propertyManager[1])));
			ownerVO.setPhone(StringUtils.getString(String.valueOf(propertyManager[2])));
			ownerVO.setEmail(StringUtils.getString(String.valueOf(propertyManager[3])));
			ownerVO.setUserId(StringUtils.getInteger(String.valueOf(propertyManager[4])));
			ownerVO.setUsername(StringUtils.getString(String.valueOf(propertyManager[5])));
			ownerVO.setPassword(StringUtils.getString(String.valueOf(propertyManager[6])));
			ownerVO.setRoleId(StringUtils.getInteger(String.valueOf(propertyManager[7])));
			ownerVO.setRoleName(StringUtils.getString(String.valueOf(propertyManager[8])));
			ownerVOs.add(ownerVO);
		}
		return ownerVOs;
	}

	public Integer getCountOwner() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM Owner o");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addOwner(Map<String, Object> params) {
		Owner owner = new Owner();
		User user = new User();
		Role role = entityManager.find(Role.class, 2);
		user.setUsername(params.get("username").toString());
		user.setPassword(params.get("password").toString());
		user.setRole(role);
		owner.setName(params.get("name").toString());
		owner.setPhone(params.get("phone")==null?"":params.get("name").toString());
		owner.setEmail(params.get("email")==null?"":params.get("email").toString());
		owner.setAddress(params.get("adress")==null?"":params.get("adress").toString());
		owner.setIDNumber(params.get("IDNumber")==null?"":params.get("IDNumber").toString());
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(params.get("startTime").toString(),df);
		owner.setStartTime(startTime);
		owner.setUser(user);
		entityManager.persist(user);
		entityManager.persist(owner);
	}

	public OwnerVO getOwnerById(Integer id) {
		return null;
	}

	public void updateOwner(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Owner owner = entityManager.find(Owner.class, id);
		User user = owner.getUser();
		user.setUsername(params.get("username").toString());
		user.setPassword(params.get("password").toString());
		owner.setName(params.get("name").toString());
		owner.setPhone(params.get("phone")==null?"":params.get("name").toString());
		owner.setEmail(params.get("email")==null?"":params.get("email").toString());
		owner.setAddress(params.get("adress")==null?"":params.get("adress").toString());
		owner.setIDNumber(params.get("IDNumber")==null?"":params.get("IDNumber").toString());
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(params.get("startTime").toString(),df);
		owner.setStartTime(startTime);
		owner.setUser(user);
		entityManager.merge(user);
		entityManager.merge(owner);
	}

	public void deleteOwner(Integer id) {
		Owner owner = entityManager.find(Owner.class, id);
		if (owner!=null) {
			User user = owner.getUser();
			if (user!=null) {
				entityManager.remove(user);
			}
			entityManager.remove(owner);
		}
	}
	
	
}
