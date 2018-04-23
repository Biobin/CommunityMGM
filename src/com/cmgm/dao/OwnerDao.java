package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date 2018年4月4日
 * @time 下午3:14:44
 *
 */

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

import com.cmgm.VO.OwnerVO;
import com.cmgm.common.DateUtils;
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
		String jpql = "SELECT o.id, o.name, o.phone, o.email, ou.id, ou.username, ou.password, our.id, our.name, o.address, "
				+ "o.IDNumber, to_char(o.startTime,'yyyy-MM-dd HH24:mm:ss') FROM Owner o "
				+ "LEFT JOIN o.user ou LEFT JOIN ou.role our ";
		Query query = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize);
		List<Object[]> owners  = query.getResultList();
		List<OwnerVO> ownerVOs = new ArrayList<>();
		OwnerVO ownerVO = null;
		for(Object[] owner : owners) {
			ownerVO = new OwnerVO();
			ownerVO.setId(StringUtils.getInteger(String.valueOf(owner[0])));
			ownerVO.setName(StringUtils.getString(String.valueOf(owner[1])));
			ownerVO.setPhone(StringUtils.getString(String.valueOf(owner[2])));
			ownerVO.setEmail(StringUtils.getString(String.valueOf(owner[3])));
			ownerVO.setUserId(StringUtils.getInteger(String.valueOf(owner[4])));
			ownerVO.setUsername(StringUtils.getString(String.valueOf(owner[5])));
			ownerVO.setPassword(StringUtils.getString(String.valueOf(owner[6])));
			ownerVO.setRoleId(StringUtils.getInteger(String.valueOf(owner[7])));
			ownerVO.setRoleName(StringUtils.getString(String.valueOf(owner[8])));
			ownerVO.setAddress(StringUtils.getString(String.valueOf(owner[9])));
			ownerVO.setIDNumber(StringUtils.getString(String.valueOf(owner[10])));
			ownerVO.setStartTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", owner[11]));
//			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			LocalDateTime startTime = null;
//			if (String.valueOf(owner[11])!=null) {
//				startTime = LocalDateTime.parse(StringUtils.getString(String.valueOf(owner[11])),df);
//				ownerVO.setStartTime(startTime);
//			}
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
		owner.setPhone(params.get("phone")==null?"":params.get("phone").toString());
		owner.setEmail(params.get("email")==null?"":params.get("email").toString());
		owner.setAddress(params.get("address")==null?"":params.get("address").toString());
		owner.setIDNumber(params.get("IDNumber")==null?"":params.get("IDNumber").toString());
		String startTime = params.get("startTime").toString();
		Date date = null;
		if (startTime !=null && !startTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		owner.setStartTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime startTime = LocalDateTime.parse(params.get("startTime").toString(),df);
//		owner.setStartTime(startTime);
		entityManager.persist(user);
		owner.setUser(user);
		entityManager.persist(owner);
	}

	public OwnerVO getOwnerById(Integer id) {
		String jpql = "SELECT o.id, o.name, o.phone, o.email, ou.id, ou.username, ou.password, our.id, our.name, o.address, "
				+ "o.IDNumber, to_char(o.startTime,'yyyy-MM-dd HH24:mm:ss') FROM Owner o "
				+ "LEFT JOIN o.user ou LEFT JOIN ou.role our WHERE o.id = :id ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		OwnerVO ownerVO = new OwnerVO();
		ownerVO.setId(StringUtils.getInteger(String.valueOf(objects[0])));
		ownerVO.setName(StringUtils.getString(String.valueOf(objects[1])));
		ownerVO.setPhone(StringUtils.getString(String.valueOf(objects[2])));
		ownerVO.setEmail(StringUtils.getString(String.valueOf(objects[3])));
		ownerVO.setUserId(StringUtils.getInteger(String.valueOf(objects[4])));
		ownerVO.setUsername(StringUtils.getString(String.valueOf(objects[5])));
		ownerVO.setPassword(StringUtils.getString(String.valueOf(objects[6])));
		ownerVO.setRoleId(StringUtils.getInteger(String.valueOf(objects[7])));
		ownerVO.setRoleName(StringUtils.getString(String.valueOf(objects[8])));
		ownerVO.setAddress(StringUtils.getString(String.valueOf(objects[9])));
		ownerVO.setIDNumber(StringUtils.getString(String.valueOf(objects[10])));
		ownerVO.setStartTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[11]));
		return ownerVO;
	}

	public void updateOwner(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Owner owner = entityManager.find(Owner.class, id);
		User user = owner.getUser();
		user.setUsername(params.get("username").toString());
		if (StringUtils.getString(params.get("password"))!=null) {
			user.setPassword(StringUtils.getString(params.get("password")));
		}
		owner.setName(params.get("name").toString());
		owner.setPhone(params.get("phone")==null?"":params.get("phone").toString());
		owner.setEmail(params.get("email")==null?"":params.get("email").toString());
		owner.setAddress(params.get("address")==null?"":params.get("address").toString());
		owner.setIDNumber(params.get("IDNumber")==null?"":params.get("IDNumber").toString());
		String startTime = params.get("startTime").toString();
		Date date = null;
		if (startTime !=null && !startTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		owner.setStartTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime startTime = LocalDateTime.parse(params.get("startTime").toString(),df);
//		owner.setStartTime(startTime);
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
