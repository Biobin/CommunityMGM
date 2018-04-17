package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.ComplaintVO;
import com.cmgm.dao.ComplaintDao;
import com.cmgm.entity.PropertyManager;

/**
 *
 * @author Bio
 * @date   2018年4月6日
 * @time   下午2:37:20
 *
 */

@Service
public class ComplaintService {

	@Autowired
	private ComplaintDao complaintDao;
	
	@Transactional(readOnly=true)
	public List<PropertyManager> getPropertyManagerList() {
		return complaintDao.getPropertyManagerList();
	}
	
	@Transactional(readOnly=true)
	public ComplaintVO getPropertyManagerByPid(Integer propertyManagerId) {
		return complaintDao.getPropertyManagerByPid(propertyManagerId);
	}

	@Transactional(readOnly=true)
	public List<ComplaintVO> getComplaints(int pageNO, int pageSize, String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		return complaintDao.getComplaints(pageNO,pageSize,beginTime,endTime,stateId,ownerId,propertyManagerId);
	}

	@Transactional(readOnly=true)
	public Integer getCountComplaint(String beginTime, String endTime, String stateId, Integer ownerId, Integer propertyManagerId) {
		return complaintDao.getCountComplaint(beginTime,endTime,stateId,ownerId,propertyManagerId);
	}

	@Transactional
	public void addComplaint(Map<String, Object> params) {
		complaintDao.addComplaint(params);
	}

	@Transactional
	public void updateComplaint(Map<String, Object> params) {
		complaintDao.updateComplaint(params);
	}

	@Transactional
	public ComplaintVO getComplaintById(Integer id) {
		return complaintDao.getComplaintById(id);
	}

	@Transactional
	public void deleteComplaint(Integer id) {
		complaintDao.deleteComplaint(id);
	}

}
