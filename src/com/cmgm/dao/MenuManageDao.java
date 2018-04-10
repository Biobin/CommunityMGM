package com.cmgm.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.MenuVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Menu;
import com.cmgm.entity.Role;
import com.cmgm.entity.State;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 下午6:24:22
 *
 */

@Repository
public class MenuManageDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Role> getRoleList() {
		String jpql = "SELECT new Role(r.id,r.name) FROM Role r ";
		List<Role> roles = entityManager.createQuery(jpql).getResultList();
		return roles;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getMenuList() {
		String jqpl = "SELECT new Menu(m.id,m.text) FROM Menu m ";
		List<Menu> menus = entityManager.createQuery(jqpl).getResultList();
		return menus;
	}

	@SuppressWarnings("unchecked")
	public List<MenuVO> getMenus(int pageNO, int pageSize) {
		String jpql = "SELECT m.id, m.text, ms.id, ms.name, m.url, m.iconCls, m.parent.id, m.parent.text from Menu m LEFT JOIN m.state ms ";
		Query query = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize);
		List<Object[]> objects = query.getResultList();
		List<MenuVO> menuVOs = new ArrayList<>();
		MenuVO menuVO = null;
		for (Object[] object : objects) {
			menuVO = new MenuVO();
			menuVO.setId(StringUtils.getInteger(object[0]));
			menuVO.setText(StringUtils.getString(object[1]));
			menuVO.setStateId(StringUtils.getInteger(object[2]));
			menuVO.setState(StringUtils.getString(object[3]));
			menuVO.setUrl(StringUtils.getString(object[4]));
			menuVO.setIconCls(StringUtils.getString(object[5]));
			menuVO.setPid(StringUtils.getInteger(object[6]));
			menuVO.setPname(StringUtils.getString(object[7]));
			List<String> result = entityManager.createQuery("SELECT mr.name FROM Menu m JOIN m.roles mr WHERE m.id = :mid ").setParameter("mid", StringUtils.getInteger(String.valueOf(object[0]))).getResultList();
			Set<String> roleNames = new HashSet<String>(result);
			menuVO.setRoleNames(roleNames);
			menuVOs.add(menuVO);
		}
		return menuVOs;
	}

	public Integer getCountMenu() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM Menu m");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addMenu(Map<String, Object> params) {
		Menu menu = new Menu();
		Set<Role> roles = new HashSet<>();
		Integer[] rolesIds = (Integer[]) params.get("rolesIds");
		for (Integer roleId : rolesIds) {
			Role role = entityManager.find(Role.class, roleId);
			roles.add(role);
		}
		State state = entityManager.find(State.class, StringUtils.getInteger(params.get("stateId")));
		Menu parent = null;
		if (params.get("pid")!=null) {
			parent = entityManager.find(Menu.class, StringUtils.getInteger(params.get("pid")));
		} else {
			parent = entityManager.find(Menu.class, -1);
		}
		menu.setText(params.get("text").toString());
		menu.setState(state);
		menu.setUrl(params.get("url").toString());
		menu.setIconCls(params.get("iconCls").toString());
		menu.setParent(parent);
		menu.setRoles(roles);
		entityManager.persist(menu);
	}

	@SuppressWarnings("unchecked")
	public MenuVO getMenuById(Integer id) {
		String jpql = "SELECT m.id, m.text, ms.id, ms.name, m.url, m.iconCls, m.parent.id, m.parent.text from Menu m LEFT JOIN m.state ms WHERE m.id = :id ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		MenuVO menuVO = new MenuVO();
		menuVO.setId(StringUtils.getInteger(objects[0]));
		menuVO.setText(StringUtils.getString(objects[1]));
		menuVO.setStateId(StringUtils.getInteger(objects[2]));
		menuVO.setState(StringUtils.getString(objects[3]));
		menuVO.setUrl(StringUtils.getString(objects[4]));
		menuVO.setIconCls(StringUtils.getString(objects[5]));
		menuVO.setPid(StringUtils.getInteger(objects[6]));
		menuVO.setPname(StringUtils.getString(objects[7]));
		List<String> result = entityManager.createQuery("SELECT mr.name FROM Menu m JOIN m.roles mr WHERE m.id = :mid ").setParameter("mid", StringUtils.getInteger(String.valueOf(objects[0]))).getResultList();
		Set<String> roleNames = new HashSet<String>(result);
		menuVO.setRoleNames(roleNames);
		List<Integer> idResult = entityManager.createQuery("SELECT mr.id FROM Menu m JOIN m.roles mr WHERE m.id = :mid ").setParameter("mid", StringUtils.getInteger(String.valueOf(objects[0]))).getResultList();
		Set<Integer> rolesIds = new HashSet<Integer>(idResult);
		menuVO.setRolesIds(rolesIds);
		return menuVO;
	}

	public void updateMenu(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Menu menu = entityManager.find(Menu.class, id);
		Set<Role> roles = new HashSet<>();
		Integer[] rolesIds = (Integer[]) params.get("rolesIds");
		for (Integer roleId : rolesIds) {
			Role role = entityManager.find(Role.class, roleId);
			roles.add(role);
		}
		State state = entityManager.find(State.class, StringUtils.getInteger(params.get("stateId")));
		Menu parent = entityManager.find(Menu.class, StringUtils.getInteger(params.get("pid")));
		menu.setText(params.get("text").toString());
		menu.setState(state);
		menu.setUrl(params.get("url").toString());
		menu.setIconCls(params.get("iconCls").toString());
		menu.setParent(parent);
		menu.setRoles(roles);
		entityManager.merge(menu);
	}
	
	public void deleteMenu(Integer id) {
		Menu menu = entityManager.find(Menu.class, id);
		if (menu != null) {
			entityManager.remove(menu);
		}
	}


}
