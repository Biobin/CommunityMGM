package com.cmgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmgm.VO.MenuVO;
import com.cmgm.dao.MenuDao;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午11:03:06
 *
 */

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;

	public List<MenuVO> getMenus(Integer roleId, Integer pid) {
		return menuDao.getMenus(roleId,pid);
	}
	
}
