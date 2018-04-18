package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午10:19:38
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

import com.cmgm.VO.CommunalFacilitiesVO;
import com.cmgm.common.DateUtils;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.entity.CommunalFacilities;
import com.cmgm.entity.PropertyManager;

@Repository
public class CommunalFacilitiesDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<PropertyManager> getPropertyManagerList() {
		String jqpl = "SELECT new PropertyManager(p.id,p.name,p.phone,p.email) FROM PropertyManager p ";
		List<PropertyManager> propertyManagers = entityManager.createQuery(jqpl).getResultList();
		return propertyManagers;
	}
	
	public CommunalFacilitiesVO getPropertyManagerByPid(Integer propertyManagerId) {
		String jpql = "SELECT p.phone, p.email FROM PropertyManager p WHERE p.id = :propertyManagerId ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("propertyManagerId", propertyManagerId).getSingleResult();
		CommunalFacilitiesVO communalFacilitiesVO = new CommunalFacilitiesVO();
		communalFacilitiesVO.setPropertyManagerId(propertyManagerId);
		communalFacilitiesVO.setPropertyManagerPhone(StringUtils.getString(objects[0]));
		communalFacilitiesVO.setPropertyManagerEmail(StringUtils.getString(objects[1]));
		return communalFacilitiesVO;
	}
	
	@SuppressWarnings("unchecked")
	public List<CommunalFaStyle> getCommunalFaStyleList() {
		String jpql = "SELECT new CommunalFaStyle(cs.id,cs.name) FROM CommunalFaStyle cs ";
		List<CommunalFaStyle> communalFaStyles = entityManager.createQuery(jpql).getResultList();
		return communalFaStyles;
	}
	
	@SuppressWarnings("unchecked")
	public List<CommunalFaStyle> getPrivateFaStyleList() {
		String jpql = "SELECT new CommunalFaStyle(cs.id,cs.name) FROM CommunalFaStyle cs WHERE cs.id = 4 ";
		List<CommunalFaStyle> communalFaStyles = entityManager.createQuery(jpql).getResultList();
		return communalFaStyles;
	}

	@SuppressWarnings("unchecked")
	public List<CommunalFacilitiesVO> getCommunalFacilities(int pageNO, int pageSize, String code, String name) {
		String jpql = "SELECT cf.id, cf.code, cf.name, cfs.id, cfs.name, to_char(cf.beginUsingTime,'yyyy-MM-dd HH24:mm:ss'), "
				+ "cf.details, cfp.id, cfp.name, cfp.phone, cfp.email FROM CommunalFacilities cf LEFT JOIN cf.communalFaStyle cfs "
				+ "LEFT JOIN cf.propertyManager cfp WHERE (cf.code like :code or :code is null) AND (cf.name like :name or :name is null) ";
		Query query = entityManager.createQuery(jpql);
		code = code == null ? "" : code;
		name = name == null ? "" : name;
		query.setParameter("code", "%"+code+"%").setParameter("name", "%"+name+"%");
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<CommunalFacilitiesVO> communalFacilitiesVOs = new ArrayList<>();
		CommunalFacilitiesVO communalFacilities = null;
		for (Object[] object : objects) {
			communalFacilities = new CommunalFacilitiesVO();
			communalFacilities.setId(StringUtils.getInteger(object[0]));
			communalFacilities.setCode(StringUtils.getString(object[1]));
			communalFacilities.setName(StringUtils.getString(object[2]));
			communalFacilities.setCommunalFaStyleId(StringUtils.getInteger(object[3]));
			communalFacilities.setCommunalFaStyleName(StringUtils.getString(object[4]));
			communalFacilities.setBeginUsingTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[5]));
			communalFacilities.setDetails(StringUtils.getString(object[6]));
			communalFacilities.setPropertyManagerId(StringUtils.getInteger(object[7]));
			communalFacilities.setPropertyManagerName(StringUtils.getString(object[8]));
			communalFacilities.setPropertyManagerPhone(StringUtils.getString(object[9]));
			communalFacilities.setPropertyManagerEmail(StringUtils.getString(object[10]));
			communalFacilitiesVOs.add(communalFacilities);
		}
		return communalFacilitiesVOs;
	}
	
	@SuppressWarnings("unchecked")
	public List<CommunalFacilitiesVO> getPrivateFacilities(int pageNO, int pageSize, String code, String name) {
		String jpql = "SELECT cf.id, cf.code, cf.name, cfs.id, cfs.name, to_char(cf.beginUsingTime,'yyyy-MM-dd HH24:mm:ss'), "
				+ "cf.details, cfp.id, cfp.name, cfp.phone, cfp.email FROM CommunalFacilities cf LEFT JOIN cf.communalFaStyle cfs "
				+ "LEFT JOIN cf.propertyManager cfp WHERE (cfs.id = 4) AND (cf.code like :code or :code is null) AND (cf.name like :name or :name is null) ";
		Query query = entityManager.createQuery(jpql);
		code = code == null ? "" : code;
		name = name == null ? "" : name;
		query.setParameter("code", "%"+code+"%").setParameter("name", "%"+name+"%");
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<CommunalFacilitiesVO> privateFacilitiesVOs = new ArrayList<>();
		CommunalFacilitiesVO privateFacilities = null;
		for (Object[] object : objects) {
			privateFacilities = new CommunalFacilitiesVO();
			privateFacilities.setId(StringUtils.getInteger(object[0]));
			privateFacilities.setCode(StringUtils.getString(object[1]));
			privateFacilities.setName(StringUtils.getString(object[2]));
			privateFacilities.setCommunalFaStyleId(StringUtils.getInteger(object[3]));
			privateFacilities.setCommunalFaStyleName(StringUtils.getString(object[4]));
			privateFacilities.setBeginUsingTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[5]));
			privateFacilities.setDetails(StringUtils.getString(object[6]));
			privateFacilities.setPropertyManagerId(StringUtils.getInteger(object[7]));
			privateFacilities.setPropertyManagerName(StringUtils.getString(object[8]));
			privateFacilities.setPropertyManagerPhone(StringUtils.getString(object[9]));
			privateFacilities.setPropertyManagerEmail(StringUtils.getString(object[10]));
			privateFacilitiesVOs.add(privateFacilities);
		}
		return privateFacilitiesVOs;
	}

	public Integer getCountCommunalFacilities(String code, String name) {
		String jpql = "SELECT COUNT(*) FROM CommunalFacilities cf WHERE (cf.code like :code or :code is null) AND (cf.name like :name or :name is null) ";
		Query query = entityManager.createQuery(jpql);
		code = code == null ? "" : code;
		name = name == null ? "" : name;
		query.setParameter("code", "%"+code+"%").setParameter("name", "%"+name+"%");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addCommunalFacilitities(Map<String, Object> params) {
		CommunalFacilities communalFacilities = new CommunalFacilities();
		String code = StringUtils.getString(params.get("code"));
		String name = StringUtils.getString(params.get("name"));
		Integer communalFaStyleId = StringUtils.getInteger(params.get("communalFaStyleId"));
		CommunalFaStyle communalFaStyle = null;
		Integer propertyManagerId = StringUtils.getInteger(params.get("propertyManagerId"));
		PropertyManager propertyManager = null;
		if (propertyManagerId!=null) {
			propertyManager = entityManager.find(PropertyManager.class, propertyManagerId);
		}
		if (communalFaStyleId!=null) {
			communalFaStyle = entityManager.find(CommunalFaStyle.class, communalFaStyleId);
		}
		if (communalFaStyleId==4 && propertyManagerId==null) {
			propertyManager = entityManager.find(PropertyManager.class, 22);
		}
		String beginUsingTime = StringUtils.getString(params.get("beginUsingTime"));
		Date date = null;
		if (beginUsingTime !=null && !beginUsingTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginUsingTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			communalFacilities.setBeginUsingTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		String details = StringUtils.getString(params.get("details"));
		communalFacilities.setCode(code);
		communalFacilities.setName(name);
		communalFacilities.setCommunalFaStyle(communalFaStyle);
		communalFacilities.setDetails(details);
		communalFacilities.setPropertyManager(propertyManager);
		entityManager.persist(communalFacilities);
	}

	public CommunalFacilitiesVO getCommunalFacilitiesById(Integer id) {
		String jpql = "SELECT cf.id, cf.code, cf.name, cfs.id, cfs.name, to_char(cf.beginUsingTime,'yyyy-MM-dd HH24:mm:ss'), "
				+ "cf.details, cfp.id, cfp.name, cfp.phone, cfp.email FROM CommunalFacilities cf LEFT JOIN cf.communalFaStyle cfs "
				+ "LEFT JOIN cf.propertyManager cfp WHERE cf.id = :id ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		CommunalFacilitiesVO communalFacilitiesVO = new CommunalFacilitiesVO();
		communalFacilitiesVO = new CommunalFacilitiesVO();
		communalFacilitiesVO.setId(StringUtils.getInteger(objects[0]));
		communalFacilitiesVO.setCode(StringUtils.getString(objects[1]));
		communalFacilitiesVO.setName(StringUtils.getString(objects[2]));
		communalFacilitiesVO.setCommunalFaStyleId(StringUtils.getInteger(objects[3]));
		communalFacilitiesVO.setCommunalFaStyleName(StringUtils.getString(objects[4]));
		communalFacilitiesVO.setBeginUsingTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[5]));
		communalFacilitiesVO.setDetails(StringUtils.getString(objects[6]));
		communalFacilitiesVO.setPropertyManagerId(StringUtils.getInteger(objects[7]));
		communalFacilitiesVO.setPropertyManagerName(StringUtils.getString(objects[8]));
		communalFacilitiesVO.setPropertyManagerPhone(StringUtils.getString(objects[9]));
		communalFacilitiesVO.setPropertyManagerEmail(StringUtils.getString(objects[10]));
		return communalFacilitiesVO;
	}

	public void updateCommunalFacilities(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		CommunalFacilities communalFacilities = entityManager.find(CommunalFacilities.class, id);
		String code = StringUtils.getString(params.get("code"));
		String name = StringUtils.getString(params.get("name"));
		Integer communalFaStyleId = StringUtils.getInteger(params.get("communalFaStyleId"));
		CommunalFaStyle communalFaStyle = null;
		Integer propertyManagerId = StringUtils.getInteger(params.get("propertyManagerId"));
		PropertyManager propertyManager = null;
		if (propertyManagerId!=null) {
			propertyManager = entityManager.find(PropertyManager.class, propertyManagerId);
		}
		if (communalFaStyleId!=null) {
			communalFaStyle = entityManager.find(CommunalFaStyle.class, communalFaStyleId);
		}
		if (communalFaStyleId==4 && propertyManagerId==null) {
			propertyManager = entityManager.find(PropertyManager.class, 22);
		}
		String beginUsingTime = StringUtils.getString(params.get("beginUsingTime"));
		Date date = null;
		if (beginUsingTime !=null && !beginUsingTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginUsingTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			communalFacilities.setBeginUsingTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		String details = StringUtils.getString(params.get("details"));
		communalFacilities.setCode(code);
		communalFacilities.setName(name);
		communalFacilities.setCommunalFaStyle(communalFaStyle);
		communalFacilities.setDetails(details);
		communalFacilities.setPropertyManager(propertyManager);
		entityManager.merge(communalFacilities);
	}

	public void deleteCommunalFacilities(Integer id) {
		CommunalFacilities communalFacilities = entityManager.find(CommunalFacilities.class, id);
		if (communalFacilities!=null) {
			entityManager.remove(communalFacilities);
		}
	}
	
}
