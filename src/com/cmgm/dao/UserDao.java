package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date   2018年3月30日
 * @time   下午2:41:45
 *
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.Role;
import com.cmgm.entity.User;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Role> getAllRole() {
		Query query = entityManager.createQuery("SELECT new Role(r.id,r.name) FROM Role r ");
		List<Role> roles = query.getResultList();
		return roles;
	}

	public List<User> getUsers(int pageNO, int pageSize) {
		String jpql = "SELECT u.id, u.username,to_char(u.createTime,'yyyy-MM-dd HH24:MI:SS') from User u";
		Query query = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize);
		
		return null;
	}

	public Integer getCountUser() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM User u");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}
	
}
