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

import com.cmgm.VO.ComplaintVO;
import com.cmgm.common.DateUtils;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Complaint;
import com.cmgm.entity.Owner;
import com.cmgm.entity.PropertyManager;
import com.cmgm.entity.State;

/**
 *
 * @author Bio
 * @date   2018年4月6日
 * @time   下午2:37:53
 *
 */

@Repository
public class ComplaintDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Owner> getOwnerList() {
		String jpql = "SELECT new Owner(o.id, o.name, o.phone, o.email) FROM Owner o ";
		List<Owner> owners = entityManager.createQuery(jpql).getResultList();
		return owners;
	}
	
	@SuppressWarnings("unchecked")
	public List<PropertyManager> getPropertyManagerList() {
		String jpql = "SELECT new PropertyManager(pm.id, pm.name) FROM PropertyManager pm ";
		List<PropertyManager> propertyManagers = entityManager.createQuery(jpql).getResultList();
		return propertyManagers;
	}
	
	public ComplaintVO getPropertyManagerByPid(Integer propertyManagerId) {
		String jpql = "SELECT pm.name, pm.phone, pm.email FROM PropertyManager pm WHERE pm.id = :propertyManagerId ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("propertyManagerId", propertyManagerId).getSingleResult();
		ComplaintVO complaintVO = new ComplaintVO();
		complaintVO.setPropertyManagerId(propertyManagerId);
		complaintVO.setPropertyManagerName(StringUtils.getString(objects[0]));
		complaintVO.setPropertyManagerPhone(StringUtils.getString(objects[1]));
		complaintVO.setPropertyManagerEmail(StringUtils.getString(objects[2]));
		return complaintVO;
	}

	@SuppressWarnings("unchecked")
	public List<ComplaintVO> getComplaints(int pageNO, int pageSize, String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		String jpql = "SELECT c.id, c.content, to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss'), co.id, co.name, co.phone, co.email, "
				+ "cpm.id, cpm.name, cpm.phone, cpm.email, cs.id, cs.name, c.returnContent, to_char(c.finishTime,'yyyy-MM-dd HH24:mm:ss') FROM Complaint c "
				+ "LEFT JOIN c.owner co LEFT JOIN c.propertyManager cpm LEFT JOIN c.state cs WHERE (co.id = :ownerId or :ownerId is null ) "
				+ "AND (cpm.id = :propertyManagerId or :propertyManagerId is null) ";
		StringBuffer sBuffer = new StringBuffer();
		if (stateId==null) {
			sBuffer.append("AND (cs.id != 3) ");
		} else {
			sBuffer.append("AND (cs.id = :stateId )");
		}
		StringBuffer condition = new StringBuffer();
		if (StringUtils.getString(beginTime).equals("")) {
			condition.append(StringUtils.getString(endTime).equals("")?"":"AND to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') <= '"+endTime+"' ");
		}else{
			if (StringUtils.getString(endTime).equals("")) {
				condition.append("AND to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') >= '"+beginTime+"' ");
			}else{
				condition.append("AND to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') between '"+beginTime+"' and '"+endTime+"' ");
			}
		}
		Query query = entityManager.createQuery(jpql+sBuffer+condition);
		if (stateId!=null) {
			query.setParameter("stateId", StringUtils.getInteger(stateId));
		}
		query.setParameter("ownerId", ownerId);
		query.setParameter("propertyManagerId", propertyManagerId);
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<ComplaintVO> complaintVOs = new ArrayList<>();
		ComplaintVO complaintVO = null;
		for (Object[] object : objects) {
			complaintVO = new ComplaintVO();
			complaintVO.setId(StringUtils.getInteger(object[0]));
			complaintVO.setContent(StringUtils.getString(object[1]));
			complaintVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[2]));
			complaintVO.setOwnerId(StringUtils.getInteger(object[3]));
			complaintVO.setOwnerName(StringUtils.getString(object[4]));
			complaintVO.setOwnerPhone(StringUtils.getString(object[5]));
			complaintVO.setOwnerEmail(StringUtils.getString(object[6]));
			complaintVO.setPropertyManagerId(StringUtils.getInteger(object[7]));
			complaintVO.setPropertyManagerName(StringUtils.getString(object[8]));
			complaintVO.setPropertyManagerPhone(StringUtils.getString(object[9]));
			complaintVO.setPropertyManagerEmail(StringUtils.getString(object[10]));
			complaintVO.setStateId(StringUtils.getInteger(object[11]));
			complaintVO.setStateName(StringUtils.getString(object[12]));
			complaintVO.setReturnContent(StringUtils.getString(object[13]));
			complaintVO.setFinishTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[14]));
			complaintVOs.add(complaintVO);
		}
		return complaintVOs;
	}

	public Integer getCountComplaint(String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		String jpql = "SELECT COUNT(*) FROM Complaint c LEFT JOIN c.owner co LEFT JOIN c.propertyManager cpm  LEFT JOIN c.state cs "
				+ "WHERE (co.id = :ownerId or :ownerId is null ) AND (cpm.id = :propertyManagerId or :propertyManagerId is null) ";
		StringBuffer sBuffer = new StringBuffer();
		if (stateId==null) {
			sBuffer.append("AND (cs.id != 3) ");
		} else {
			sBuffer.append("AND (cs.id = :stateId )");
		}
		StringBuffer condition = new StringBuffer();
		if (StringUtils.getString(beginTime).equals("")) {
			condition.append(StringUtils.getString(endTime).equals("")?"":"AND to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') <= '"+endTime+"' ");
		}else{
			if (StringUtils.getString(endTime).equals("")) {
				condition.append("AND to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') >= '"+beginTime+"' ");
			}else{
				condition.append("AND to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss') between '"+beginTime+"' and '"+endTime+"' ");
			}
		}
		Query query = entityManager.createQuery(jpql+sBuffer+condition);
		if (stateId!=null) {
			query.setParameter("stateId", StringUtils.getInteger(stateId));
		}
		query.setParameter("ownerId", ownerId);
		query.setParameter("propertyManagerId", propertyManagerId);
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addComplaint(Map<String, Object> params) {
		Complaint complaint = new Complaint();
		String content = StringUtils.getString(params.get("content"));
		String createTime = StringUtils.getString(params.get("createTime"));
		Integer propertyManagerId = StringUtils.getInteger(params.get("propertyManagerId"));
		Integer ownerId = StringUtils.getInteger(params.get("ownerId"));
		Owner owner = entityManager.find(Owner.class, ownerId);
		PropertyManager propertyManager = entityManager.find(PropertyManager.class, propertyManagerId);
		Date date = null;
		if (createTime !=null && !createTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			complaint.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		complaint.setContent(content);
		complaint.setPropertyManager(propertyManager);
		State state = entityManager.find(State.class, 1);//默认为待处理
		complaint.setState(state);
		complaint.setOwner(owner);//自动获取保存投诉业主不需要填写
		entityManager.persist(complaint);
	}

	public void updateComplaint(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Complaint complaint = entityManager.find(Complaint.class, id);
		Integer stateId = StringUtils.getInteger(params.get("stateId"));
		State state = entityManager.find(State.class, stateId);
		String returnContent = StringUtils.getString(params.get("returnContent"));
		String finishTime = StringUtils.getString(params.get("finishTime"));
		complaint.setState(state);
		complaint.setReturnContent(returnContent);
		Date date = null;
		if (finishTime !=null && !finishTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(finishTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			complaint.setFinishTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		entityManager.merge(complaint);
	}

	public ComplaintVO getComplaintById(Integer id) {
		String jpql = "SELECT c.id, c.content, to_char(c.createTime,'yyyy-MM-dd HH24:mm:ss'), co.id, co.name, co.phone, co.email, "
				+ "cpm.id, cpm.name, cpm.phone, cpm.email, cs.id, cs.name, c.returnContent, to_char(c.finishTime,'yyyy-MM-dd HH24:mm:ss') FROM Complaint c "
				+ "LEFT JOIN c.owner co LEFT JOIN c.propertyManager cpm LEFT JOIN c.state cs WHERE (c.id = :id or :id is null ) ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		ComplaintVO complaintVO = new ComplaintVO();
		complaintVO.setId(StringUtils.getInteger(objects[0]));
		complaintVO.setContent(StringUtils.getString(objects[1]));
		complaintVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[2]));
		complaintVO.setOwnerId(StringUtils.getInteger(objects[3]));
		complaintVO.setOwnerName(StringUtils.getString(objects[4]));
		complaintVO.setOwnerPhone(StringUtils.getString(objects[5]));
		complaintVO.setOwnerEmail(StringUtils.getString(objects[6]));
		complaintVO.setPropertyManagerId(StringUtils.getInteger(objects[7]));
		complaintVO.setPropertyManagerName(StringUtils.getString(objects[8]));
		complaintVO.setPropertyManagerPhone(StringUtils.getString(objects[9]));
		complaintVO.setPropertyManagerEmail(StringUtils.getString(objects[10]));
		complaintVO.setStateId(StringUtils.getInteger(objects[11]));
		complaintVO.setStateName(StringUtils.getString(objects[12]));
		complaintVO.setReturnContent(StringUtils.getString(objects[13]));
		complaintVO.setFinishTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[14]));
		return complaintVO;
	}

	public void deleteComplaint(Integer id) {
		Complaint complaint = entityManager.find(Complaint.class, id);
		if (complaint!=null) {
			entityManager.remove(complaint);
		}
	}
	
}
