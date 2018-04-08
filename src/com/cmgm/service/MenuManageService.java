package com.cmgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmgm.dao.MenuManageDao;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 下午6:23:57
 *
 */

@Service
public class MenuManageService {

	@Autowired
	private MenuManageDao menuManageDao;

	public void deleteMenu(Integer id) {
		menuManageDao.deleteMenu(id);
	}
	
}
