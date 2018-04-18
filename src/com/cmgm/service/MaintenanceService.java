package com.cmgm.service;

import java.util.List;

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
	
}
