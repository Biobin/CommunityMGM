package com.cmgm.dao;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:41:02
 *
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.MaintenanceVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.entity.CommunalFacilities;
import com.cmgm.entity.Owner;
import com.cmgm.entity.PropertyManager;

@Repository
public class MaintenanceDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Owner> getOwnerList() {
		String jpql = "SELECT new Owner(o.id, o.name, o.phone, o.email) FROM Owner o ";
		List<Owner> owners = entityManager.createQuery(jpql).getResultList();
		return owners;
	}

//	@SuppressWarnings("unchecked")
//	public List<PropertyManager> getPropertyManagerList() {
//		String jpql = "SELECT new PropertyManager(pm.id, pm.name) FROM PropertyManager pm ";
//		List<PropertyManager> propertyManagers = entityManager.createQuery(jpql).getResultList();
//		return propertyManagers;
//	}
	
//	public MaintenanceVO getPropertyManagerByPid(Integer propertyManagerId) {
//		String jpql = "SELECT pm.name, pm.phone, pm.email FROM PropertyManager pm WHERE pm.id = :propertyManagerId ";
//		Query query = entityManager.createQuery(jpql);
//		Object[] objects = (Object[]) query.setParameter("propertyManagerId", propertyManagerId).getSingleResult();
//		MaintenanceVO maintenanceVO = new MaintenanceVO();
//		maintenanceVO.setPropertyManagerId(propertyManagerId);
//		maintenanceVO.setPropertyManagerName(StringUtils.getString(objects[0]));
//		maintenanceVO.setPropertyManagerPhone(StringUtils.getString(objects[1]));
//		maintenanceVO.setPropertyManagerEmail(StringUtils.getString(objects[2]));
//		return maintenanceVO;
//	}

	@SuppressWarnings("unchecked")
	public List<CommunalFaStyle> getCommunalFaStyleList() {
		String jpql = "SELECT new CommunalFaStyle(cs.id,cs.name) FROM CommunalFaStyle cs ";
		List<CommunalFaStyle> communalFaStyles = entityManager.createQuery(jpql).getResultList();
		return communalFaStyles;
	}

	@SuppressWarnings("unchecked")
	public List<CommunalFacilities> getCommunalFacilitiesList(Integer communalFaStyleId) {
		String jpql = "SELECT new CommunalFacilities(cf.id,cf.name) FROM CommunalFacilities cf WHERE cf.communalFaStyle.id = :communalFaStyleId ";
		List<CommunalFacilities> communalFacilities = entityManager.createQuery(jpql).setParameter("communalFaStyleId", communalFaStyleId).getResultList();
		return communalFacilities;
	}
	
}
