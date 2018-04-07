package com.cmgm.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.Notice;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午2:46:39
 *
 */

@Repository
public class NoticeDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Notice> getNotices(int pageNO, int pageSize) {
		return null;
	}

	public Integer getCountNotice() {
		return null;
	}

	public void addNotice(Map<String, Object> params) {
		
	}

	public void updateNoice(Map<String, Object> params) {
		
	}

	public Notice getNotice(Integer id) {
		return null;
	}

	public void deleteNotice(Integer id) {
		
	}
	
}
