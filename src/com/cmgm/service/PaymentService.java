package com.cmgm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Owner> getOwnerList() {
		return paymentDao.getOwnerList();
	}

	public List<PaymentVO> getPayments(int pageNO, int pageSize) {
		return paymentDao.getPayments(pageNO,pageSize);
	}
	
	public Integer getCountPayment() {
		return paymentDao.getCountPayment();
	}

	public void addPayment(Map<String, Object> params) {
		paymentDao.addPayment(params);
	}

	public PaymentVO getPayment(Integer id) {
		return paymentDao.getPayment(id);
	}

	public void updatePayment(Map<String, Object> params) {
		paymentDao.updatePayment(params);
	}

	public void deletePayment(Integer id) {
		paymentDao.deletePayment(id);
	}

	
}
