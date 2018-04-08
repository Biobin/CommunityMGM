package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Contact> getContacts(int pageNO, int pageSize) {
		return contactDao.getContacts(pageNO,pageSize);
	}

	public Integer getCountContact() {
		return contactDao.getCountContact();
	}

	public void addContact(Map<String, Object> params) {
		contactDao.addContact(params);
	}

	public Contact getContact(Integer id) {
		return contactDao.getContact(id);
	}

	public void updateContact(Map<String, Object> params) {
		contactDao.updateContact(params);
	}

	public void deleteContact(Integer id) {
		contactDao.deleteContact(id);
	}
	
}
