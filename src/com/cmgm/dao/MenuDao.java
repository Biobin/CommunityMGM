package com.cmgm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.MenuVO;
import com.cmgm.entity.Menu;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午11:04:04
 *
 */

@Repository
public class MenuDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<MenuVO> getMenus(Integer roleId, Integer pid) {
		String jpql = "SELECT m.id,m.text,ms.id,ms.name,m.iconCls,m.url FROM Menu m "
				+ "LEFT JOIN m.state ms LEFT JOIN m.role mr WHERE mr.id = :roleId AND m.parent.id = :pid ";
		List<Menu> menus = entityManager.createQuery(jpql).setParameter("pid", pid).setParameter("roleId", roleId).getResultList();
		List<MenuVO> menuVOs = null;
		if (menus.size() > 0) {
			menuVOs = new ArrayList<>();
			MenuVO menuVO = null;
			for (Menu menu : menus) {
				menuVO = new MenuVO();
				menuVO.setId(menu.getId());
				menuVO.setText(menu.getText());
				menuVO.setStateId(menu.getState().getId());
				menuVO.setUrl(menu.getUrl());
				menuVO.setIconCls(menu.getIconCls());
				
				menuVOs.add(menuVO);
			}
		}
		return menuVOs;
	}
	
}
