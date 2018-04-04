package com.cmgm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.User;

/**
 *
 * @author Bio
 * @date 2018年3月27日
 * @time 下午12:01:41
 *
 */

@Repository
public class LoginDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public User getUserByUserName(String username) {
		try {
			String jpql = "From User u where u.username = :username";
			User myUser = (User) entityManager.createQuery(jpql).setParameter("username", username).getSingleResult();
			return myUser;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
}
