package com.cmgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.StateDao;
import com.cmgm.entity.State;

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

	@Transactional(readOnly=true)
	public List<State> getStates(int pageNO, int pageSize) {
		return stateDao.getState(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountState() {
		return stateDao.getCountState();
	}

	@Transactional(readOnly=true)
	public State getStateById(Integer id) {
		return stateDao.getStateById(id);
	}

	@Transactional
	public State addOrUpdateState(State state) {
		return stateDao.addOrUpdate(state);
	}

	@Transactional
	public String deleteState(Integer id) {
		return stateDao.deleteState(id);
	}
	
}
