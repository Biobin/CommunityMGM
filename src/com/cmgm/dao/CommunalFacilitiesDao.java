package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午10:19:38
 *
 */

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.CommunalFacilitiesVO;

@Repository
public class CommunalFacilitiesDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<CommunalFacilitiesVO> getCommunalFacilities(int pageNO, int pageSize) {
		return null;
	}

	public Integer getCountCommunalFacilities() {
		return null;
	}

	public void addCommunalFacilitities(Map<String, Object> params) {
		
	}

	public CommunalFacilitiesVO getCommunalFacilitiesById(Integer id) {
		return null;
	}

	public void updateCommunalFacilities(Map<String, Object> params) {
		
	}

	public void deleteCommunalFacilities(Integer id) {
		
	}
	
}
