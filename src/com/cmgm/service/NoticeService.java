package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.dao.NoticeDao;
import com.cmgm.entity.Notice;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午2:46:10
 *
 */

@Service
public class NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	@Transactional(readOnly=true)
	public List<Notice> getNotices(int pageNO, int pageSize) {
		return noticeDao.getNotices(pageNO,pageSize);
	}

	@Transactional(readOnly=true)
	public Integer getCountNotice() {
		return noticeDao.getCountNotice();
	}

	@Transactional
	public void addNotice(Map<String, Object> params) {
		noticeDao.addNotice(params);
	}

	@Transactional
	public void updateNotice(Map<String, Object> params) {
		noticeDao.updateNoice(params);
	}

	@Transactional
	public Notice getNotice(Integer id) {
		return noticeDao.getNotice(id);
	}

	public void deleteNotice(Integer id) {
		noticeDao.deleteNotice(id);
	}
	
}
