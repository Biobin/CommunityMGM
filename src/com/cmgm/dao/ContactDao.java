package com.cmgm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.common.StringUtils;
import com.cmgm.entity.Contact;
import com.cmgm.entity.User;

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

	@SuppressWarnings("unchecked")
	public List<Contact> getContacts(int pageNO, int pageSize, String name, Integer uid) {
		String jpql = "SELECT c.id,c.name,c.phone,c.email FROM Contact c LEFT JOIN c.user cu WHERE (cu.id = :uid or :uid is null) and (c.name like :name or :name is null) ";
		Query query = entityManager.createQuery(jpql);
		name = name == null ? "" : name;
		query.setParameter("uid", uid).setParameter("name", "%"+ name +"%");
		/*if (name != null && !name.equals("")) {
			jpql += "and (c.name like :name or :name is null) ";
			query.setParameter("name", "%"+name+"%");
		}*/
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<Contact> contacts = new ArrayList<>();
		Contact contact = null;
		for (Object[] object : objects) {
			contact = new Contact();
			contact.setId(StringUtils.getInteger(object[0]));
			contact.setName(StringUtils.getString(object[1]));
			contact.setPhone(StringUtils.getString(object[2]));
			contact.setEmail(StringUtils.getString(object[3]));
			contacts.add(contact);
		}
		return contacts;
	}

	public Integer getCountContact(String name, Integer uid) {
		String jpql = "SELECT COUNT(*) FROM Contact c LEFT JOIN c.user cu WHERE (cu.id = :uid or :uid is null) and (c.name like :name or :name is null) ";
		Query query = entityManager.createQuery(jpql);
		name = name == null ? "" : name;
		query.setParameter("uid", uid).setParameter("name", "%"+ name +"%");
		/*if (name != null && !name.equals("")) {
			jpql += "and (c.name like :name or :name is null) ";
			query.setParameter("name", "%"+name+"%");
		}*/
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addContact(Map<String, Object> params) {
		Contact contact = new Contact();
		String name = StringUtils.getString(params.get("name"));
		String phone  = StringUtils.getString(params.get("phone"));
		String email = StringUtils.getString(params.get("email"));
		Integer userId = StringUtils.getInteger(params.get("userId"));
		User user = null;
		if (userId != null) {
			user = entityManager.find(User.class, userId);
		}
		contact.setName(name);
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setUser(user);
		entityManager.persist(contact);
	}

	public Contact getContact(Integer id) {
		String jpql = "SELECT c.id,c.name,c.phone,c.email FROM Contact c LEFT JOIN c.user cu WHERE c.id = :id ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		Contact contact = new Contact();
		contact.setId(StringUtils.getInteger(objects[0]));
		contact.setName(StringUtils.getString(objects[1]));
		contact.setPhone(StringUtils.getString(objects[2]));
		contact.setEmail(StringUtils.getString(objects[3]));
		return contact;
	}

	public void updateContact(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Contact contact = null;
		if (id!=null) {
			contact = entityManager.find(Contact.class, id);
		}
		String name = StringUtils.getString(params.get("name"));
		String phone  = StringUtils.getString(params.get("phone"));
		String email = StringUtils.getString(params.get("email"));
		Integer userId = StringUtils.getInteger(params.get("userId"));
		User user = null;
		if (userId != null) {
			user = entityManager.find(User.class, userId);
		}
		contact.setName(name);
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setUser(user);
		entityManager.merge(contact);
	}

	public void deleteContact(Integer id) {
		Contact contact = entityManager.find(Contact.class, id);
		if (contact!=null) {
			entityManager.remove(contact);
		}
	}
	
	
	
}
