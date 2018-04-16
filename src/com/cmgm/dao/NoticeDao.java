package com.cmgm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.common.DateUtils;
import com.cmgm.common.StringUtils;
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

	@SuppressWarnings("unchecked")
	public List<Notice> getNotices(int pageNO, int pageSize, String code, String title) {
		String jpql = "SELECT n.id,n.code,n.title,to_char(n.createTime,'yyyy-MM-dd HH24:mm:ss'),n.content FROM Notice n "
				+ "WHERE (n.code like :code or :code is null ) AND (n.title like :title or :title is null )";
		Query query = entityManager.createQuery(jpql);
		code = code == null ? "" : code;
		title = title == null ? "" : title;
		query.setParameter("code", "%"+code+"%").setParameter("title", "%"+title+"%");
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<Notice> notices = new ArrayList<>();
		Notice notice = null;
		for (Object[] object : objects) {
			notice = new Notice();
			notice.setId(StringUtils.getInteger(object[0]));
			notice.setCode(StringUtils.getString(object[1]));
			notice.setTitle(StringUtils.getString(object[2]));
			notice.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[3]));
			notice.setContent(StringUtils.getString(object[4]));
			notices.add(notice);
		}
		return notices;
	}

	public Integer getCountNotice(String code, String title) {
		String jpql = "SELECT COUNT(*) FROM Notice n WHERE (n.code like :code or :code is null ) AND (n.title like :title or :title is null )";
		Query query = entityManager.createQuery(jpql);
		code = code == null ? "" : code;
		title = title == null ? "" : title;
		query.setParameter("code", "%"+code+"%").setParameter("title", "%"+title+"%");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addNotice(Map<String, Object> params) {
		Notice notice = new Notice();
		String code = StringUtils.getString(params.get("code"));
		String title = StringUtils.getString(params.get("title"));
		String content = StringUtils.getString(params.get("content"));
		String createTime = StringUtils.getString(params.get("createTime"));
		notice.setCode(code);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", createTime));
		entityManager.persist(notice);
	}

	public void updateNoice(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Notice notice = entityManager.find(Notice.class, id);
		String code = StringUtils.getString(params.get("code"));
		String title = StringUtils.getString(params.get("title"));
		String content = StringUtils.getString(params.get("content"));
		String createTime = StringUtils.getString(params.get("createTime"));
		notice.setCode(code);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", createTime));
		entityManager.merge(notice);
	}

	public Notice getNotice(Integer id) {
		String jpql = "SELECT n.id,n.code,n.title,to_char(n.createTime,'yyyy-MM-dd HH24:mm:ss'),n.content FROM Notice n WHERE n.id = :id ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		Notice notice = new Notice();
		notice.setId(StringUtils.getInteger(objects[0]));
		notice.setCode(StringUtils.getString(objects[1]));
		notice.setTitle(StringUtils.getString(objects[2]));
		notice.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[3]));
		notice.setContent(StringUtils.getString(objects[4]));
		return notice;
	}

	public void deleteNotice(Integer id) {
		Notice notice = entityManager.find(Notice.class, id);
		if (notice!=null) {
			entityManager.remove(notice);
		}
	}
	
}
