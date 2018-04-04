package com.cmgm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.entity.CommunalFaStyle;

/**
 *
 * @author Bio
 * @date 2018年4月4日
 * @time 上午11:42:15
 *
 */

@Repository
public class CommunalFaStyleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<CommunalFaStyle> getCommunalFaStyle(int pageNO, int pageSize) {
		String jpql = "SELECT new CommunalFaStyle(cfs.id,cfs.name) FROM CommunalFaStyle cfs ";
		List<CommunalFaStyle> communalFaStyles = entityManager.createQuery(jpql).setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		return communalFaStyles;
	}

	public Integer getCountCommunalFaStyle() {
		Query query = entityManager.createQuery("SELECT COUNT(*) FROM CommunalFaStyle cfs ");
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public CommunalFaStyle getCommunalFaStyleById(Integer id) {
		return entityManager.find(CommunalFaStyle.class, id);
	}

	public CommunalFaStyle addOrUpdateCommunalFaStyle(CommunalFaStyle communalFaStyle) {
		return entityManager.merge(communalFaStyle);
	}

	public String deleteCommunalFaStyle(Integer id) {
		String candelete = "false";
		CommunalFaStyle communalFaStyle = entityManager.find(CommunalFaStyle.class, id);
		if (communalFaStyle != null) {
			if (communalFaStyle.getCommunalFacilities().size()<1) {
				candelete = "true";
				entityManager.remove(communalFaStyle);
			}
		}
		return candelete;
	}
	
}
