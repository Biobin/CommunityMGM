package com.cmgm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.VO.PaymentVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Owner;
import com.cmgm.service.PaymentService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午3:49:16
 *
 */

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	//页面跳转
	@RequestMapping("/payment/paymentManage")
	public String paymentManage(){
		return "paymentManage";
	};
	
	//获取业主下拉列表
	@ResponseBody
	@RequestMapping("payment/ownerList")
	public List<Owner> getClasssList(Model model) throws JsonProcessingException {
		List<Owner> ownerList = paymentService.getOwnerList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(ownerList);
		model.addAttribute("ownerList", json);
		return ownerList;
	}
	
	@ResponseBody
	@RequestMapping("/payment/getPayments")
	public Map<String, Object> getPayments(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String stateId = request.getParameter("sateId");
		List<PaymentVO> paymentVOs = paymentService.getPayments(pageNO,pageSize,beginTime,endTime,stateId);
		int count = paymentService.getCountPayment(beginTime,endTime,stateId);
		if (paymentVOs == null || paymentVOs.isEmpty()) {
			paymentVOs = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", paymentVOs);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/payment/addPayment")
	public String addPayment(HttpServletRequest request) {
		Double receivableFee = StringUtils.getDoubleObj(request.getParameter("receivableFee")); 
		Double owedFee = StringUtils.getDoubleObj(request.getParameter("owedFee"));
		Double collectFee = StringUtils.getDoubleObj(request.getParameter("collectFee"));
		Integer stateId = StringUtils.getInteger(request.getParameter("stateId"));
		Integer ownerId = StringUtils.getInteger(request.getParameter("ownerId"));
		String chargingItem = StringUtils.getString(request.getParameter("chargingItem"));
		String details = StringUtils.getString(request.getParameter("chargingItem"));
		String createTime = StringUtils.getString(request.getParameter("createTime"));
		Map<String, Object> params = new HashMap<>();
		params.put("receivableFee", receivableFee);
		params.put("owedFee", owedFee);
		params.put("collectFee", collectFee);
		params.put("stateId", stateId);
		params.put("ownerId", ownerId);
		params.put("chargingItem", chargingItem);
		params.put("details", details);
		params.put("createTime", createTime);
		paymentService.addPayment(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="/payment/getPayment/{id}", method=RequestMethod.GET)
	public PaymentVO getPayment(@PathVariable("id")Integer id) {
		PaymentVO paymentVO = null;
		if (id!=null) {
			paymentVO = paymentService.getPayment(id);
		}
		return paymentVO;
	}
	
	@ResponseBody
	@RequestMapping("payment/updatePayment/{id}")
	public String updatePayment(@PathVariable("id")Integer id, HttpServletRequest request) {
		Double receivableFee = StringUtils.getDoubleObj(request.getParameter("receivableFee")); 
		Double owedFee = StringUtils.getDoubleObj(request.getParameter("owedFee"));
		Double collectFee = StringUtils.getDoubleObj(request.getParameter("collectFee"));
		Integer stateId = StringUtils.getInteger(request.getParameter("stateId"));
		Integer ownerId = StringUtils.getInteger(request.getParameter("ownerId"));
		String chargingItem = StringUtils.getString(request.getParameter("chargingItem"));
		String details = StringUtils.getString(request.getParameter("chargingItem"));
		String createTime = StringUtils.getString(request.getParameter("createTime"));
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("receivableFee", receivableFee);
		params.put("owedFee", owedFee);
		params.put("collectFee", collectFee);
		params.put("stateId", stateId);
		params.put("ownerId", ownerId);
		params.put("chargingItem", chargingItem);
		params.put("details", details);
		params.put("createTime", createTime);
		paymentService.updatePayment(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping("/payment/deletePayment/{id}")
	public String deletePayment(@PathVariable("id")Integer id) {
		paymentService.deletePayment(id);
		return "delete";
	}
	
	//缴费业主信息显示
	@ResponseBody
	@RequestMapping("/payment/showOwnerInfo/{ownerId}")
	public PaymentVO getOwnerByOwnerId(@PathVariable("ownerId")Integer ownerId) {
		PaymentVO paymentVO = null;
		if (ownerId != null) {
			paymentVO = paymentService.getOwnerByOwnerId(ownerId);
		}
		return paymentVO;
	}
	
}
