package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:41:02
 *
 */

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceDao {

	@PersistenceContext
	private EntityManager entityManager;
	
}
