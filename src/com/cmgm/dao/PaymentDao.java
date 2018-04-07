package com.cmgm.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cmgm.VO.PaymentVO;
import com.cmgm.entity.Owner;

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

	public List<Owner> getOwnerList() {
		return null;
	}

	public Integer getCountPayment() {
		return null;
	}

	public List<PaymentVO> getPayments(int pageNO, int pageSize) {
		return null;
	}

	public void addPayment(Map<String, Object> params) {
		
	}

	public PaymentVO getPayment(Integer id) {
		return null;
	}

	public void updatePayment(Map<String, Object> params) {
		
	}

	public void deletePayment(Integer id) {
		
	}
	
}
