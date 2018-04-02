package com.cmgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmgm.dao.StateDao;

/**
 *
 * @author Bio
 * @date 2018年4月2日
 * @time 下午5:56:01
 *
 */

@Service
public class StateService {

	@Autowired
	private StateDao stateDao;
	
}
