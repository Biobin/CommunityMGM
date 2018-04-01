package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date   2018年3月30日
 * @time   下午3:45:32
 *
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.Role;

@Repository
public class RoleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Role> getRoles(int pageNO, int pageSize) {
		String jpql = "SELECT new Role(r.id,r.name,r.createTime) FROM Role r ";
		List<Role> roles = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		return roles;
	}

	public Integer getCountRoles() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM Role r ");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public Role getRoleById(int id) {
		return entityManager.find(Role.class, id);
	}

	public Role addOrUpdateRole(Role role) {
		return entityManager.merge(role);
	}

	public String deleteRole(Integer id) {
		String candelete = "false";
		Role role = entityManager.find(Role.class, id);
		if (role != null) {
			if (role.getUsers().size()<1) {
				candelete = "true";
				entityManager.remove(role);
			}
		}
		return candelete;
	}
	
}
