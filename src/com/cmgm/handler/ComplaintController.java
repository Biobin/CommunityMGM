package com.cmgm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.VO.ComplaintVO;
import com.cmgm.common.StringUtils;
import com.cmgm.service.ComplaintService;

/**
 *
 * @author Bio
 * @date   2018年4月6日
 * @time   下午2:33:38
 * 投诉处理(业主投诉填写投诉页，管理员处理，修改状态)
 */

@Controller
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
	@RequestMapping("/complaint/complaintManage")
	public String complaintManage(){
		return "complaintManage";
	};
	
	@ResponseBody
	@RequestMapping("/complaint/getComplaint")
	public Map<String, Object> getComplaint(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		List<ComplaintVO> complaints = complaintService.getComplaints(pageNO,pageSize);
		int count = complaintService.getCountComplaint();
		if (complaints == null || complaints.isEmpty()) {
			complaints = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", complaints);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	//业主添加投诉内容
	@ResponseBody
	@RequestMapping("/complaint/addComplaint")
	public String addComplaint(HttpServletRequest request) {
		String content = request.getParameter("content");
		String createTime = request.getParameter("craeteTime");
		Integer ownerId = StringUtils.getInteger(request.getParameter("ownerId"));
		String ownerName = request.getParameter("ownerName");
		String ownerPhone = request.getParameter("ownerPhone");
		String ownerEmail = request.getParameter("ownerEmail");
		Integer stateId = StringUtils.getInteger(request.getParameter("stateId"));
		String stateName = request.getParameter("stateName");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("content", content);
		params.put("createTime", createTime);
		params.put("ownerId", ownerId);
		params.put("ownerName", ownerName);
		params.put("ownerPhone", ownerPhone);
		params.put("ownerEmail", ownerEmail);
		params.put("stateId", stateId);
		params.put("stateName", stateName);
		complaintService.addComplaint(params);
		return "add";
	}
	
	//回显
	@ResponseBody
	@RequestMapping(value="/complaint/getComplaintById/{id}", method=RequestMethod.GET)
	public ComplaintVO getComplaint(@PathVariable("id")Integer id) {
		ComplaintVO complaintVO = null;
		if (id != null) {
			complaintVO = complaintService.getComplaintById(id);
		}
		return complaintVO;
	}
	
	//物业管理员接收投诉处理后填写
	@ResponseBody
	@RequestMapping("/complaint/updateComplaint/{id}")
	public String updateComplaint(@PathVariable("id")Integer id,HttpServletRequest request) {
		Integer propertyMangerId = StringUtils.getInteger(request.getParameter("propertyManagerId"));
		String propertyManagerName = request.getParameter("propertyManagerName");
		String properManagerPhone = request.getParameter("propertyManagerPhone");
		String properManagerEmail = request.getParameter("propertyManagerEmail");
		Integer stateId = StringUtils.getInteger(request.getParameter("stateId"));
		String stateName = request.getParameter("StateName");
		String returnContent = request.getParameter("returnContent");
		String finishTime = request.getParameter("finishTime");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("propetyManagerId", propertyMangerId);
		params.put("propetyManagerName", propertyManagerName);
		params.put("propertyManagerPhone", properManagerPhone);
		params.put("propertyManagerEmail", properManagerEmail);
		params.put("stateId", stateId);
		params.put("stateName", stateName);
		params.put("returnContent", returnContent);
		params.put("finishTime", finishTime);
		complaintService.updateComplaint(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping(value="complaint/deleteComplaint/{id}")
	public String deleteComplaint(@PathVariable("id")Integer id) {
		complaintService.deleteComplaint(id);
		return "delete";
	}
	
}
