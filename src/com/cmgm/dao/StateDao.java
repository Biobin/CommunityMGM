package com.cmgm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Bio
 * @date 2018年4月2日
 * @time 下午5:56:34
 *
 */

@Repository
public class StateDao {

	@PersistenceContext
	private EntityManager entityManager;
	
}
