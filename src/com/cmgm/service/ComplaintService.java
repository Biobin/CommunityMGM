package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.ComplaintVO;
import com.cmgm.dao.ComplaintDao;

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
	public List<ComplaintVO> getComplaints(int pageNO, int pageSize) {
		return complaintDao.getComplaints(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountComplaint() {
		return complaintDao.getCountComplaint();
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

	public void deleteComplaint(Integer id) {
		complaintDao.deleteComplaint(id);
	}
	
}
