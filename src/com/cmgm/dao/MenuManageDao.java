package com.cmgm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 下午6:24:22
 *
 */

@RequestMapping
public class MenuManageDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void deleteMenu(Integer id) {
		
	}
	
}
