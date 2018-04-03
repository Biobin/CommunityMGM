package com.cmgm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.State;

/**
 *
 * @author Bio
 * @date 2018年4月2日
 * @time 下午5:56:34
 *
 */

@Repository
public class StateDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<State> getState(int pageNO, int pageSize) {
		String jpql = "SELECT new State(s.id, s.name) FROM State s ";
		List<State> states = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		return states;
	}

	public Integer getCountState() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM State s ");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public State getStateById(Integer id) {
		return entityManager.find(State.class, id);
	}

	public State addOrUpdate(State state) {
		return entityManager.merge(state);
	}

	public String deleteState(Integer id) {
		String candelete = "false";
		State state = entityManager.find(State.class, id);
		if (state != null) {
			candelete = "true";
			entityManager.remove(state);
		}
		return candelete;
	}
	
}
