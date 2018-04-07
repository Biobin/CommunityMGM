package com.cmgm.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.ComplaintVO;

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

	public List<ComplaintVO> getComplaints(int pageNO, int pageSize) {
		return null;
	}

	public Integer getCountComplaint() {
		return null;
	}

	public void addComplaint(Map<String, Object> params) {
		
	}

	public void updateComplaint(Map<String, Object> params) {
		
	}

	public ComplaintVO getComplaintById(Integer id) {
		return null;
	}

	public void deleteComplaint(Integer id) {
		
	}
	
}
