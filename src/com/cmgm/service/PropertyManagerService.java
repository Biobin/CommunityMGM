package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.PropertyManagerVO;
import com.cmgm.dao.PropertyManagerDao;

/**
 *
 * @author Bio
 * @date 2018年4月3日
 * @time 上午11:35:12
 *
 */

@Service
public class PropertyManagerService {

	@Autowired
	private PropertyManagerDao propertyManagerDao;

	@Transactional(readOnly=true)
	public List<PropertyManagerVO> getPropertyManagers(int pageNO, int pageSize, String username, String name, String phone) {
		return propertyManagerDao.getPropertyManagers(pageNO,pageSize,username,name,phone);
	}

	@Transactional(readOnly=true)
	public Integer getCountPropertyManager() {
		return propertyManagerDao.getCountPropertyManager();
	}

	@Transactional
	public void addPropertyManager(Map<String, Object> params) {
		propertyManagerDao.addPropertyManager(params);
	}

	@Transactional
	public void updatePropertyManager(Map<String, Object> params) {
		propertyManagerDao.updatePropertyManager(params);
	}

	@Transactional
	public void deletePropertyManager(Integer id) {
		propertyManagerDao.deletePropertyManager(id);
	}

	@Transactional(readOnly=true)
	public PropertyManagerVO getPropertyManagerById(Integer id) {
		return propertyManagerDao.getPropertyManagerById(id);
	}
	
}
