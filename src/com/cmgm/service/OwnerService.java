package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.OwnerVO;
import com.cmgm.dao.OwnerDao;

/**
 *
 * @author Bio
 * @date 2018年4月4日
 * @time 下午3:13:29
 *
 */

@Service
public class OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@Transactional(readOnly=true)
	public List<OwnerVO> getOwners(int pageNO, int pageSize) {
		return ownerDao.getOwners(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountOwner() {
		return ownerDao.getCountOwner();
	}

	@Transactional
	public void addOwner(Map<String, Object> params) {
		ownerDao.addOwner(params);
	}

	@Transactional(readOnly=true)
	public OwnerVO getOwnerById(Integer id) {
		return ownerDao.getOwnerById(id);
	}

	@Transactional
	public void updateOwner(Map<String, Object> params) {
		ownerDao.updateOwner(params);
	}

	@Transactional
	public void deleteOwner(Integer id) {
		ownerDao.deleteOwner(id);
	}
	
}
