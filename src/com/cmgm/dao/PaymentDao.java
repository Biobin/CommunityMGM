package com.cmgm.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.PaymentVO;
import com.cmgm.common.DateUtils;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Owner;
import com.cmgm.entity.Payment;
import com.cmgm.entity.State;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午3:58:53
 *
 */

@Repository
public class PaymentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Owner> getOwnerList() {
		String jpql = "SELECT new Owner(o.id, o.name, o.phone, o.email) FROM Owner o ";
		List<Owner> owners = entityManager.createQuery(jpql).getResultList();
		return owners;
	}

	@SuppressWarnings("unchecked")
	public List<PaymentVO> getPayments(int pageNO, int pageSize, String beginTime, String endTime, String stateId, Integer ownerId) {
		String jpql = "SELECT pm.id, pmo.id, pmo.name, pmo.phone, pmo.email, pm.receivableFee, pm.collectFee, pm.owedFee, "
				+ "pms.id, pms.name, pm.chargingItem, to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss'), pm.details FROM Payment pm "
				+ "LEFT JOIN pm.owner pmo LEFT JOIN pm.state pms WHERE (pmo.id = :ownerId or :ownerId is null ) AND (pms.id = :stateId or :stateId is null ) ";
		stateId = stateId == null ? "" : stateId;
		StringBuffer condition = new StringBuffer();
		if (StringUtils.getString(beginTime).equals("")) {
			condition.append(StringUtils.getString(endTime).equals("")?"":"AND to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss') <= '"+endTime+"' ");
		}else{
			if (StringUtils.getString(endTime).equals("")) {
				condition.append("AND to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss') >= '"+beginTime+"' ");
			}else{
				condition.append("AND to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss') between '"+beginTime+"' and '"+endTime+"' ");
			}
		}
		Query query = entityManager.createQuery(jpql+condition);
		query.setParameter("ownerId", ownerId);
		query.setParameter("stateId", StringUtils.getInteger(stateId));
		List<Object[]> objects = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<PaymentVO> paymentVOs = new ArrayList<>();
		PaymentVO paymentVO = null;
		for (Object[] object : objects) {
			paymentVO = new PaymentVO();
			paymentVO.setId(StringUtils.getInteger(object[0]));
			paymentVO.setOwnerId(StringUtils.getInteger(object[1]));
			paymentVO.setOwnerName(StringUtils.getString(object[2]));
			paymentVO.setOwnerPhone(StringUtils.getString(object[3]));
			paymentVO.setOwnerEmail(StringUtils.getString(object[4]));
			paymentVO.setReceivableFee(StringUtils.getDoubleObj(object[5]));
			paymentVO.setCollectFee(StringUtils.getDoubleObj(object[6]));
			paymentVO.setOwedFee(StringUtils.getDoubleObj(object[7]));
			paymentVO.setStateId(StringUtils.getInteger(object[8]));
			paymentVO.setStateName(StringUtils.getString(object[9]));
			paymentVO.setChargingItem(StringUtils.getString(object[10]));
			paymentVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", object[11]));
			paymentVO.setDetails(StringUtils.getString(object[12]));
			paymentVOs.add(paymentVO);
		}
		return paymentVOs;
	}
	
	public Integer getCountPayment(String beginTime, String endTime, String stateId, Integer ownerId) {
		String jpql = "SELECT COUNT(*) FROM Payment pm LEFT JOIN pm.owner pmo LEFT JOIN pm.state pms WHERE (pmo.id = :ownerId or :ownerId is null ) AND (pms.id = :stateId or :stateId is null ) ";
		stateId = stateId == null ? "" : stateId;
		StringBuffer condition = new StringBuffer();
		if (StringUtils.getString(beginTime).equals("")) {
			condition.append(StringUtils.getString(endTime).equals("")?"":"AND to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss') <= '"+endTime+"' ");
		}else{
			if (StringUtils.getString(endTime).equals("")) {
				condition.append("AND to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss') >= '"+beginTime+"' ");
			}else{
				condition.append("AND to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss') between '"+beginTime+"' and '"+endTime+"' ");
			}
		}
		Query query = entityManager.createQuery(jpql+condition);
		query.setParameter("ownerId", ownerId);
		query.setParameter("stateId", StringUtils.getInteger(stateId));
		int count = ((Number)query.getSingleResult()).intValue();
		return count;
	}

	public void addPayment(Map<String, Object> params) {
		Payment payment = new Payment();
		Double receivableFee = StringUtils.getDoubleObj(params.get("receivableFee"));
		Double owedFee = StringUtils.getDoubleObj(params.get("owedFee"));
		Double collectFee = StringUtils.getDoubleObj(params.get("collectFee"));
		Integer stateId = StringUtils.getInteger(params.get("stateId"));
		State state = entityManager.find(State.class, stateId);
		Integer ownerId = StringUtils.getInteger(params.get("ownerId"));
		Owner owner = entityManager.find(Owner.class, ownerId);
		String chargingItem = StringUtils.getString(params.get("chargingItem"));
		String details = StringUtils.getString(params.get("details"));
		String createTime = StringUtils.getString(params.get("createTime"));
		Date date = null;
		if (createTime !=null && !createTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			payment.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		payment.setReceivableFee(receivableFee);
		payment.setOwedFee(owedFee);
		payment.setCollectFee(collectFee);
		payment.setState(state);
		payment.setOwner(owner);
		payment.setChargingItem(chargingItem);
		payment.setDetails(details);
		entityManager.persist(payment);
	}

	public PaymentVO getPayment(Integer id) {
		String jpql = "SELECT pm.id, pmo.id, pmo.name, pmo.phone, pmo.email, pm.receivableFee, pm.collectFee, pm.owedFee, "
				+ "pms.id, pms.name, pm.chargingItem, to_char(pm.createTime,'yyyy-MM-dd HH24:mm:ss'), pm.details FROM Payment pm "
				+ "LEFT JOIN pm.owner pmo LEFT JOIN pm.state pms WHERE (pm.id = :id or :id is null ) ";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("id", id).getSingleResult();
		PaymentVO paymentVO = new PaymentVO();
		paymentVO.setId(StringUtils.getInteger(objects[0]));
		paymentVO.setOwnerId(StringUtils.getInteger(objects[1]));
		paymentVO.setOwnerName(StringUtils.getString(objects[2]));
		paymentVO.setOwnerPhone(StringUtils.getString(objects[3]));
		paymentVO.setOwnerEmail(StringUtils.getString(objects[4]));
		paymentVO.setReceivableFee(StringUtils.getDoubleObj(objects[5]));
		paymentVO.setCollectFee(StringUtils.getDoubleObj(objects[6]));
		paymentVO.setOwedFee(StringUtils.getDoubleObj(objects[7]));
		paymentVO.setStateId(StringUtils.getInteger(objects[8]));
		paymentVO.setStateName(StringUtils.getString(objects[9]));
		paymentVO.setChargingItem(StringUtils.getString(objects[10]));
		paymentVO.setCreateTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", objects[11]));
		paymentVO.setDetails(StringUtils.getString(objects[12]));
		return paymentVO;
	}

	public void updatePayment(Map<String, Object> params) {
		Integer id = StringUtils.getInteger(params.get("id"));
		Payment payment = entityManager.find(Payment.class, id);
		Double receivableFee = StringUtils.getDoubleObj(params.get("receivableFee"));
		Double owedFee = StringUtils.getDoubleObj(params.get("owedFee"));
		Double collectFee = StringUtils.getDoubleObj(params.get("collectFee"));
		Integer stateId = StringUtils.getInteger(params.get("stateId"));
		State state = entityManager.find(State.class, stateId);
		Integer ownerId = StringUtils.getInteger(params.get("ownerId"));
		Owner owner = entityManager.find(Owner.class, ownerId);
		String chargingItem = StringUtils.getString(params.get("chargingItem"));
		String details = StringUtils.getString(params.get("details"));
		String createTime = StringUtils.getString(params.get("createTime"));
		Date date = null;
		if (createTime !=null && !createTime.equals("")) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			payment.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
		}
		payment.setReceivableFee(receivableFee);
		payment.setOwedFee(owedFee);
		payment.setCollectFee(collectFee);
		payment.setState(state);
		payment.setOwner(owner);
		payment.setChargingItem(chargingItem);
		payment.setDetails(details);
		entityManager.merge(payment);
	}

	public void deletePayment(Integer id) {
		Payment payment = entityManager.find(Payment.class, id);
		if (payment!=null) {
			entityManager.remove(payment);
		}
	}

	public PaymentVO getOwnerVOByOwnerId(Integer ownerId) {
		String jpql = "SELECT o.phone, o.email FROM Owner o WHERE o.id = :ownerId";
		Query query = entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.setParameter("ownerId", ownerId).getSingleResult();
		PaymentVO paymentVO = new PaymentVO();
		paymentVO.setOwnerId(ownerId);
		paymentVO.setOwnerPhone(StringUtils.getString(objects[0]));
		paymentVO.setOwnerEmail(StringUtils.getString(objects[1]));
		return paymentVO;
	}
	
}
