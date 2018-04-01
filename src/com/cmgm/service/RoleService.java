package com.cmgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.RoleDao;
import com.cmgm.entity.Role;

/**
 *
 * @author Bio
 * @date   2018年3月30日
 * @time   下午3:44:59
 *
 */

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	@Transactional(readOnly=true)
	public List<Role> getRoles(int pageNO, int pageSize) {
		return roleDao.getRoles(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountRoles() {
		return roleDao.getCountRoles();
	}

	@Transactional(readOnly=true)
	public Role getRoleById(int id) {
		return roleDao.getRoleById(id);
	}

	@Transactional
	public Role addOrUpdateRole(Role role) {
		return roleDao.addOrUpdateRole(role);
	}

	@Transactional
	public String deleteRole(Integer id) {
		return roleDao.deleteRole(id);
	}
	
}
