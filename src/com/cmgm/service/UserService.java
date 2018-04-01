package com.cmgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.UserDao;
import com.cmgm.entity.Role;
import com.cmgm.entity.User;

/**
 *
 * @author Bio
 * @date   2018年3月30日
 * @time   下午2:39:12
 *
 */

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly=true)
	public List<Role> getAllRole() {
		return userDao.getAllRole();
	}

	public List<User> getUsers(int pageNO, int pageSize) {
		return userDao.getUsers(pageNO,pageSize);
	}

	public Integer getCountUser() {
		return userDao.getCountUser();
	}
	
}
