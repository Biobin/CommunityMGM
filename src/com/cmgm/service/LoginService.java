package com.cmgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.LoginDao;
import com.cmgm.entity.User;

/**
 *
 * @author Bio
 * @date 2018年3月27日
 * @time 下午12:01:59
 *
 */

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	@Transactional(readOnly=true)
	public User getUserByUsername(String username) {
		return loginDao.getUserByUserName(username);
	}
	
}
