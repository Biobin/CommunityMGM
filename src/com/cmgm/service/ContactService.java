package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.ContactDao;
import com.cmgm.entity.Contact;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午11:19:28
 *
 */

@Service
public class ContactService {

	@Autowired
	private ContactDao contactDao;

	@Transactional(readOnly=true)
	public List<Contact> getContacts(int pageNO, int pageSize, String name, Integer uid) {
		return contactDao.getContacts(pageNO,pageSize,name,uid);
	}

	@Transactional(readOnly=true)
	public Integer getCountContact(String name, Integer uid) {
		return contactDao.getCountContact(name,uid);
	}

	@Transactional
	public void addContact(Map<String, Object> params) {
		contactDao.addContact(params);
	}

	@Transactional(readOnly=true)
	public Contact getContact(Integer id) {
		return contactDao.getContact(id);
	}

	@Transactional
	public void updateContact(Map<String, Object> params) {
		contactDao.updateContact(params);
	}

	@Transactional
	public void deleteContact(Integer id) {
		contactDao.deleteContact(id);
	}
	
}
