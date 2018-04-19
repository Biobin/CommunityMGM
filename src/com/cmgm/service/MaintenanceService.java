package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.MaintenanceVO;
import com.cmgm.dao.MaintenanceDao;
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.entity.CommunalFacilities;
import com.cmgm.entity.Owner;
import com.cmgm.entity.PropertyManager;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:40:30
 *
 */

@Service
public class MaintenanceService {

	@Autowired
	private MaintenanceDao maintenanceDao;

	@Transactional(readOnly=true)
	public List<Owner> getOwnerList() {
		return maintenanceDao.getOwnerList();
	}

//	@Transactional(readOnly=true)
//	public List<PropertyManager> getPropertyManagerList() {
//		return maintenanceDao.getPropertyManagerList();
//	}
	
//	@Transactional(readOnly=true)
//	public MaintenanceVO getPropertyManagerByPid(Integer propertyManagerId) {
//		return maintenanceDao.getPropertyManagerByPid(propertyManagerId);
//	}

	@Transactional(readOnly=true)
	public List<CommunalFaStyle> getCommunalFaStyleList() {
		return maintenanceDao.getCommunalFaStyleList();
	}

	@Transactional(readOnly=true)
	public List<CommunalFacilities> getCommunalFacilitiesList(Integer communalFaStyleId) {
		return maintenanceDao.getCommunalFacilitiesList(communalFaStyleId);
	}

	@Transactional(readOnly=true)
	public List<PropertyManager> getPropertyManagerList(Integer communalFacilitiesId) {
		return maintenanceDao.getPropertyManager(communalFacilitiesId);
	}

	@Transactional(readOnly=true)
	public List<MaintenanceVO> getMaintenances(int pageNO, int pageSize, String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		return maintenanceDao.getMaintenances(pageNO,pageSize,beginTime,endTime,stateId,ownerId,propertyManagerId);
	}

	@Transactional(readOnly=true)
	public Integer getCountMaintenance(String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		return maintenanceDao.getCountMaintenance(beginTime,endTime,stateId,ownerId,propertyManagerId);
	}

	@Transactional
	public void addMaintenance(Map<String, Object> params) {
		maintenanceDao.addMaintenance(params);
	}

	@Transactional(readOnly=true)
	public MaintenanceVO getMaintenance(Integer id) {
		return maintenanceDao.getMaintenance(id);
	}

	@Transactional
	public void updateMaintenance(Map<String, Object> params) {
		maintenanceDao.updateMaintenance(params);
	}

	@Transactional
	public void deleteMaintenance(Integer id) {
		maintenanceDao.deleteMaintenance(id);
	}
	
}
