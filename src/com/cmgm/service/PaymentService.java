package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmgm.VO.PaymentVO;
import com.cmgm.dao.PaymentDao;
import com.cmgm.entity.Owner;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午3:58:33
 *
 */

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Transactional(readOnly=true)
	public List<Owner> getOwnerList() {
		return paymentDao.getOwnerList();
	}

	@Transactional(readOnly=true)
	public List<PaymentVO> getPayments(int pageNO, int pageSize, String beginTime, String endTime, String stateId) {
		return paymentDao.getPayments(pageNO,pageSize,beginTime,endTime,stateId);
	}
	
	@Transactional(readOnly=true)
	public Integer getCountPayment(String beginTime, String endTime, String stateId) {
		return paymentDao.getCountPayment(beginTime,endTime,stateId);
	}

	@Transactional
	public void addPayment(Map<String, Object> params) {
		paymentDao.addPayment(params);
	}

	@Transactional(readOnly=true)
	public PaymentVO getPayment(Integer id) {
		return paymentDao.getPayment(id);
	}

	@Transactional
	public void updatePayment(Map<String, Object> params) {
		paymentDao.updatePayment(params);
	}

	@Transactional
	public void deletePayment(Integer id) {
		paymentDao.deletePayment(id);
	}

	@Transactional(readOnly=true)
	public PaymentVO getOwnerByOwnerId(Integer ownerId) {
		return paymentDao.getOwnerVOByOwnerId(ownerId);
	}

}
