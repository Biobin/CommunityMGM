package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.MenuVO;
import com.cmgm.dao.MenuManageDao;
import com.cmgm.entity.Menu;
import com.cmgm.entity.Role;

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

	@Transactional(readOnly=true)
	public List<Role> getRoleList() {
		return menuManageDao.getRoleList();
	}

	@Transactional(readOnly=true)
	public List<Menu> getMenuList() {
		return menuManageDao.getMenuList();
	}

	@Transactional(readOnly=true)
	public List<MenuVO> getMenus(int pageNO, int pageSize) {
		return menuManageDao.getMenus(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountMenu() {
		return menuManageDao.getCountMenu();
	}

	@Transactional
	public void addMenu(Map<String, Object> params) {
		menuManageDao.addMenu(params);
	}

	@Transactional(readOnly=true)
	public MenuVO getMenuById(Integer id) {
		return menuManageDao.getMenuById(id);
	}

	@Transactional
	public void updateMenu(Map<String, Object> params) {
		menuManageDao.updateMenu(params);
	}
	
	@Transactional
	public void deleteMenu(Integer id) {
		menuManageDao.deleteMenu(id);
	}


}
