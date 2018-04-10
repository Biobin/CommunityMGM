package com.cmgm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.MenuVO;
import com.cmgm.common.StringUtils;

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
				+ "LEFT JOIN m.state ms JOIN m.roles mr WHERE mr.id = :roleId AND m.parent.id = :pid ";
		List<Object[]> objects = entityManager.createQuery(jpql).setParameter("pid", pid).setParameter("roleId", roleId).getResultList();
		List<MenuVO> menuVOs = new ArrayList<>();
		MenuVO menuVO = null;
		for (Object[] object : objects) {
			menuVO = new MenuVO();
			menuVO.setId(StringUtils.getInteger(object[0]));
			menuVO.setText(StringUtils.getString(object[1]));
			menuVO.setStateId(StringUtils.getInteger(object[2]));
			menuVO.setState(StringUtils.getString(object[3]));
			menuVO.setIconCls(StringUtils.getString(object[4]));
			menuVO.setUrl(StringUtils.getString(object[5]));
			
			menuVOs.add(menuVO);
		}
		return menuVOs;
	}
	
}
