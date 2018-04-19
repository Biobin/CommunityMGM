package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:41:02
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

import com.cmgm.VO.MaintenanceVO;
import com.cmgm.common.DateUtils;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.entity.CommunalFacilities;
import com.cmgm.entity.Maintenance;
import com.cmgm.entity.Owner;
import com.cmgm.entity.PropertyManager;
import com.cmgm.entity.State;

@Repository
public class MaintenanceDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Owner> getOwnerList() {
		String jpql = "SELECT new Owner(o.id, o.name, o.phone, o.email) FROM Owner o ";
		List<Owner> owners = entityManager.createQuery(jpql).getResultList();
		return owners;
	}

//	@SuppressWarnings("unchecked")
//	public List<PropertyManager> getPropertyManagerList() {
//		String jpql = "SELECT new PropertyManager(pm.id, pm.name) FROM PropertyManager pm ";
//		List<PropertyManager> propertyManagers = entityManager.createQuery(jpql).getResultList();
//		return propertyManagers;
//	}
	
//	public MaintenanceVO getPropertyManagerByPid(Integer propertyManagerId) {
//		String jpql = "SELECT pm.name, pm.phone, pm.email FROM PropertyManager pm WHERE pm.id = :propertyManagerId ";
//		Query query = entityManager.createQuery(jpql);
//		Object[] objects = (Object[]) query.setParameter("propertyManagerId", propertyManagerId).getSingleResult();
//		MaintenanceVO maintenanceVO = new MaintenanceVO();
//		maintenanceVO.setPropertyManagerId(propertyManagerId);
//		maintenanceVO.setPropertyManagerName(StringUtils.getString(objects[0]));
//		maintenanceVO.setPropertyManagerPhone(StringUtils.getString(objects[1]));
//		maintenanceVO.setPropertyManagerEmail(StringUtils.getString(objects[2]));
//		return maintenanceVO;
//	}

	@SuppressWarnings("unchecked")
	public List<CommunalFaStyle> getCommunalFaStyleList() {
		String jpql = "SELECT new CommunalFaStyle(cs.id,cs.name) FROM CommunalFaStyle cs ";
		List<CommunalFaStyle> communalFaStyles = entityManager.createQuery(jpql).getResultList();
		return communalFaStyles;
	}

	@SuppressWarnings("unchecked")
	public List<CommunalFacilities> getCommunalFacilitiesList(Integer communalFaStyleId) {
		String jpql = "SELECT new CommunalFacilities(cf.id,cf.name) FROM CommunalFacilities cf WHERE cf.communalFaStyle.id = :communalFaStyleId ";
		List<CommunalFacilities> communalFacilities = entityManager.createQuery(jpql).setParameter("communalFaStyleId", communalFaStyleId).getResultList();
		return communalFacilities;
	}

	@SuppressWarnings("unchecked")
	public List<PropertyManager> getPropertyManager(Integer communalFacilitiesId) {
		String jpql = "SELECT cfpm.id,cfpm.name FROM CommunalFacilities cf LEFT JOIN cf.propertyManager cfpm WHERE cf.id = :communalFacilitiesId ";
		List<Object[]> objects = entityManager.createQuery(jpql).setParameter("communalFacilitiesId", communalFacilitiesId).getResultList();
		List<PropertyManager> propertyManagers = new ArrayList<>();
		PropertyManager propertyManager = null;
		for (Object[] object : objects) {
			propertyManager = new PropertyManager();
			propertyManager.setId(StringUtils.getInteger(object[0]));
			propertyManager.setName(StringUtils.getString(object[1]));
			propertyManagers.add(propertyManager);
		}
		return propertyManagers;
	}

	@SuppressWarnings("unchecked")
	public List<MaintenanceVO> getMaintenances(int pageNO, int pageSize, String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		String jpql = "SELECT m.id, m.code, to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss'), m.details, m.repairPersonnel, m.repairPerPhone, to_char(m.repairTime,'yyyy-MM-dd HH24:mm:ss'), "
				+ "m.repairRemarks, mo.id, mo.name, mo.phone, mo.email, mcf.id, mcf.name, mcfs.id, mcfs.name, mpm.id, mpm.name, mpm.phone, mpm.email, mcf.code, mcf.details, "
				+ "to_char(mcf.beginUsingTime,'yyyy-MM-dd HH24:mm:ss'), ms.id,ms.name FROM Maintenance m LEFT JOIN m.owner mo LEFT JOIN m.communalFacilities mcf "
				+ "LEFT JOIN mcf.communalFaStyle mcfs LEFT JOIN mcf.propertyManager mpm LEFT JOIN m.state ms WHERE (ms.id = :stateId or :stateId is null) "
				+ "AND (mo.id = :ownerId or :ownerId is null) AND (mpm.id = :propertyManagerId or :propertyManagerId is null) ";
		StringBuffer condition = new StringBuffer();
		if (StringUtils.getString(beginTime).equals("")) {
			condition.append(StringUtils.getString(endTime).equals("")?"":"AND to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss') <= '"+endTime+"' ");
		}else{
			if (StringUtils.getString(endTime).equals("")) {
				condition.append("AND to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss') >= '"+beginTime+"' ");
			}else{
				condition.append("AND to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss') between '"+beginTime+"' and '"+endTime+"' ");
			}
		}
		Query query = entityManager.createQuery(jpql+condition);
		query.setParameter("stateId", StringUtils.getInteger(stateId));
		query.setParameter("ownerId", ownerId);
		query.setParameter("propertyManagerId", propertyManagerId);
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<MaintenanceVO> maintenanceVOs = new ArrayList<>();
		MaintenanceVO maintenanceVO = null;
		for (Object[] object : objects) {
			maintenanceVO = new MaintenanceVO();
			maintenanceVO.setId(StringUtils.getInteger(object[0]));
			maintenanceVO.setCode(StringUtils.getString(object[1]));
			maintenanceVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[2]));
			maintenanceVO.setDetails(StringUtils.getString(object[3]));
			maintenanceVO.setRepairPersonnel(StringUtils.getString(object[4]));
			maintenanceVO.setRepairPerPhone(StringUtils.getString(object[5]));
			maintenanceVO.setRepairTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[6]));
			maintenanceVO.setRepairRemarks(StringUtils.getString(object[7]));
			maintenanceVO.setOwnerId(StringUtils.getInteger(object[8]));
			maintenanceVO.setOwnerName(StringUtils.getString(object[9]));
			maintenanceVO.setOwnerPhone(StringUtils.getString(object[10]));
			maintenanceVO.setOwnerEmail(StringUtils.getString(object[11]));
			maintenanceVO.setCommunalFacilitiesIds(StringUtils.getInteger(object[12]));
			maintenanceVO.setCommunalFacilitiesNames(StringUtils.getString(object[13]));
			maintenanceVO.setCommunalFaStyleId(StringUtils.getInteger(object[14]));
			maintenanceVO.setCommunalFaStyleName(StringUtils.getString(object[15]));
			maintenanceVO.setPropertyManagerId(StringUtils.getInteger(object[16]));
			maintenanceVO.setPropertyManagerName(StringUtils.getString(object[17]));
			maintenanceVO.setPropertyManagerPhone(StringUtils.getString(object[18]));
			maintenanceVO.setPropertyManagerEmail(StringUtils.getString(object[19]));
			maintenanceVO.setCommunalFacilitiesCode(StringUtils.getString(object[20]));
			maintenanceVO.setCommunalFacilitiesDetails(StringUtils.getString(object[21]));
			maintenanceVO.setBeginUsingTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[22]));
			maintenanceVO.setStateId(StringUtils.getInteger(object[23]));
			maintenanceVO.setStateName(StringUtils.getString(object[24]));
			maintenanceVOs.add(maintenanceVO);
		}
		return maintenanceVOs;
	}

	public Integer getCountMaintenance(String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		String jpql = "SELECT COUNT(*) FROM Maintenance m LEFT JOIN m.owner mo LEFT JOIN m.communalFacilities mcf " + 
				"LEFT JOIN mcf.communalFaStyle mcfs LEFT JOIN mcf.propertyManager mpm LEFT JOIN m.state ms WHERE (ms.id = :stateId or :stateId is null) " + 
				"AND (mo.id = :ownerId or :ownerId is null) AND (mpm.id = :propertyManagerId or :propertyManagerId is null) ";
		StringBuffer condition = new StringBuffer();
		if (StringUtils.getString(beginTime).equals("")) {
			condition.append(StringUtils.getString(endTime).equals("")?"":"AND to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss') <= '"+endTime+"' ");
		}else{
			if (StringUtils.getString(endTime).equals("")) {
				condition.append("AND to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss') >= '"+beginTime+"' ");
			}else{
				condition.append("AND to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss') between '"+beginTime+"' and '"+endTime+"' ");
			}
		}
		Query query = entityManager.createQuery(jpql+condition);
		query.setParameter("stateId", StringUtils.getInteger(stateId));
		query.setParameter("ownerId", ownerId);
		query.setParameter("propertyManagerId", propertyManagerId);
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addMaintenance(Map<String, Object> params) {
		Maintenance maintenance = new Maintenance();
		String code = StringUtils.getString(params.get("code"));
		Integer communalFacilitiesId = StringUtils.getInteger(params.get("communalFacilitiesId"));
		CommunalFacilities communalFacilities = entityManager.find(CommunalFacilities.class, communalFacilitiesId);
		String createTime = StringUtils.getString(params.get("createTime"));
		String details = StringUtils.getString(params.get("details"));
		Integer ownerId = StringUtils.getInteger(params.get("ownerId"));
		Owner owner = entityManager.find(Owner.class, ownerId);
		maintenance.setCode(code);
		maintenance.setCommunalFacilities(communalFacilities);
		Date date = null;
		if (createTime !=null && !createTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			maintenance.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		maintenance.setDetails(details);
		State state = entityManager.find(State.class, 1);//默认为待处理
		maintenance.setState(state);
		maintenance.setOwner(owner);//自动获取保存报修业主，不需要填写
		entityManager.persist(maintenance);
	}

	public MaintenanceVO getMaintenance(Integer id) {
		String jpql = "SELECT m.id, m.code, to_char(m.createTime,'yyyy-MM-dd HH24:mm:ss'), m.details, m.repairPersonnel, m.repairPerPhone, to_char(m.repairTime,'yyyy-MM-dd HH24:mm:ss'), "
				+ "m.repairRemarks, mo.id, mo.name, mo.phone, mo.email, mcf.id, mcf.name, mcfs.id, mcfs.name, mpm.id, mpm.name, mpm.phone, mpm.email, mcf.code, mcf.details, "
				+ "to_char(mcf.beginUsingTime,'yyyy-MM-dd HH24:mm:ss'), ms.id,ms.name FROM Maintenance m LEFT JOIN m.owner mo LEFT JOIN m.communalFacilities mcf "
				+ "LEFT JOIN mcf.communalFaStyle mcfs LEFT JOIN mcf.propertyManager mpm LEFT JOIN m.state ms WHERE (m.id = :id or :id is null) ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		MaintenanceVO maintenanceVO = new MaintenanceVO();
		maintenanceVO.setId(StringUtils.getInteger(objects[0]));
		maintenanceVO.setCode(StringUtils.getString(objects[1]));
		maintenanceVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[2]));
		maintenanceVO.setDetails(StringUtils.getString(objects[3]));
		maintenanceVO.setRepairPersonnel(StringUtils.getString(objects[4]));
		maintenanceVO.setRepairPerPhone(StringUtils.getString(objects[5]));
		maintenanceVO.setRepairTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[6]));
		maintenanceVO.setRepairRemarks(StringUtils.getString(objects[7]));
		maintenanceVO.setOwnerId(StringUtils.getInteger(objects[8]));
		maintenanceVO.setOwnerName(StringUtils.getString(objects[9]));
		maintenanceVO.setOwnerPhone(StringUtils.getString(objects[10]));
		maintenanceVO.setOwnerEmail(StringUtils.getString(objects[11]));
		maintenanceVO.setCommunalFacilitiesIds(StringUtils.getInteger(objects[12]));
		maintenanceVO.setCommunalFacilitiesNames(StringUtils.getString(objects[13]));
		maintenanceVO.setCommunalFaStyleId(StringUtils.getInteger(objects[14]));
		maintenanceVO.setCommunalFaStyleName(StringUtils.getString(objects[15]));
		maintenanceVO.setPropertyManagerId(StringUtils.getInteger(objects[16]));
		maintenanceVO.setPropertyManagerName(StringUtils.getString(objects[17]));
		maintenanceVO.setPropertyManagerPhone(StringUtils.getString(objects[18]));
		maintenanceVO.setPropertyManagerEmail(StringUtils.getString(objects[19]));
		maintenanceVO.setCommunalFacilitiesCode(StringUtils.getString(objects[20]));
		maintenanceVO.setCommunalFacilitiesDetails(StringUtils.getString(objects[21]));
		maintenanceVO.setBeginUsingTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[22]));
		maintenanceVO.setStateId(StringUtils.getInteger(objects[23]));
		maintenanceVO.setStateName(StringUtils.getString(objects[24]));
		return maintenanceVO;
	}

	public void updateMaintenance(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Maintenance maintenance = entityManager.find(Maintenance.class, id);
		String repairPersonnel = StringUtils.getString(params.get("repairPersonnel"));
		String repairPerPhone = StringUtils.getString(params.get("repairPerPhone"));
		String repairTime = StringUtils.getString(params.get("repairTime"));
		Date date = null;
		if (repairTime !=null && !repairTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(repairTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			maintenance.setRepairTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		Integer stateId = StringUtils.getInteger(params.get("stateId"));
		State state = entityManager.find(State.class, stateId);
		String repairRemarks = StringUtils.getString(params.get("repairRemarks"));
		maintenance.setRepairPersonnel(repairPersonnel);
		maintenance.setRepairPerPhone(repairPerPhone);
		maintenance.setState(state);
		maintenance.setRepairRemarks(repairRemarks);
		entityManager.merge(maintenance);
	}

	public void deleteMaintenance(Integer id) {
		Maintenance maintenance = entityManager.find(Maintenance.class, id);
		if (maintenance!=null) {
			entityManager.remove(maintenance);
		}
	}
	
}
