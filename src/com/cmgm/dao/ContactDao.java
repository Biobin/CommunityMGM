package com.cmgm.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.Contact;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午11:19:56
 *
 */

@Repository
public class ContactDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Contact> getContacts(int pageNO, int pageSize) {
		return null;
	}

	public Integer getCountContact() {
		return null;
	}

	public void addContact(Map<String, Object> params) {
		
	}

	public Contact getContact(Integer id) {
		return null;
	}

	public void updateContact(Map<String, Object> params) {
		
	}

	public void deleteContact(Integer id) {
		
	}
	
	
	
}
