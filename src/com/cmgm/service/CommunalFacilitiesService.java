package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.CommunalFacilitiesVO;
import com.cmgm.dao.CommunalFacilitiesDao;
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.entity.PropertyManager;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午10:19:16
 *
 */

@Service
public class CommunalFacilitiesService {

	@Autowired
	private CommunalFacilitiesDao communalFacilitiesDao;

	@Transactional(readOnly=true)
	public List<PropertyManager> getPropertyManagerList() {
		return communalFacilitiesDao.getPropertyManagerList();
	}
	
	@Transactional(readOnly=true)
	public CommunalFacilitiesVO getPropertyManagerByPid(Integer propertyManagerId) {
		return communalFacilitiesDao.getPropertyManagerByPid(propertyManagerId);
	}
	
	@Transactional(readOnly=true)
	public List<CommunalFaStyle> getCommunalFaStyleList() {
		return communalFacilitiesDao.getCommunalFaStyleList();
	}
	
	@Transactional(readOnly=true)
	public List<CommunalFacilitiesVO> getCommunalFacilities(int pageNO, int pageSize, String code, String name) {
		return communalFacilitiesDao.getCommunalFacilities(pageNO,pageSize,code,name);
	}

	@Transactional(readOnly=true)
	public Integer getCountCommunalFacilities(String code, String name) {
		return communalFacilitiesDao.getCountCommunalFacilities(code,name);
	}

	@Transactional
	public void addCommunalFacilities(Map<String, Object> params) {
		communalFacilitiesDao.addCommunalFacilitities(params);
	}

	@Transactional(readOnly=true)
	public CommunalFacilitiesVO getCommunalFaclitiesById(Integer id) {
		return communalFacilitiesDao.getCommunalFacilitiesById(id);
	}

	@Transactional
	public void updateCommunalFacilities(Map<String, Object> params) {
		communalFacilitiesDao.updateCommunalFacilities(params);
	}

	@Transactional
	public void deleteCommunalFacilities(Integer id) {
		communalFacilitiesDao.deleteCommunalFacilities(id);
	}
	
}
