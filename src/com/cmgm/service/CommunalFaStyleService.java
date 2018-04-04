package com.cmgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.CommunalFaStyleDao;
import com.cmgm.entity.CommunalFaStyle;

/**
 *
 * @author Bio
 * @date 2018年4月4日
 * @time 上午11:41:43
 *
 */

@Service
public class CommunalFaStyleService {

	@Autowired
	private CommunalFaStyleDao communalFaStyleDao;

	@Transactional(readOnly=true)
	public List<CommunalFaStyle> getCommunalFaStyle(int pageNO, int pageSize) {
		return communalFaStyleDao.getCommunalFaStyle(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountCommunalFaStyle() {
		return communalFaStyleDao.getCountCommunalFaStyle();
	}

	@Transactional(readOnly=true)
	public CommunalFaStyle getCommunalFaStyleById(Integer id) {
		return communalFaStyleDao.getCommunalFaStyleById(id);
	}

	@Transactional
	public CommunalFaStyle addOrUpdateCommunalFaStyle(CommunalFaStyle communalFaStyle) {
		return communalFaStyleDao.addOrUpdateCommunalFaStyle(communalFaStyle);
	}

	@Transactional
	public String deleteCommunalFaStyle(Integer id) {
		return communalFaStyleDao.deleteCommunalFaStyle(id);
	}
	
}
