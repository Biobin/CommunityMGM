package com.cmgm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.VO.ComplaintVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Owner;
import com.cmgm.entity.PropertyManager;
import com.cmgm.entity.User;
import com.cmgm.service.ComplaintService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	//获取业主(投诉人)下拉列表
	@ResponseBody
	@RequestMapping("/complaint/ownerList")
	public List<Owner> getOwnerList(Model model) throws JsonProcessingException {
		List<Owner> ownerList = complaintService.getOwnerList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(ownerList);
		model.addAttribute("ownerList", json);
		return ownerList;
	}
	
	//获取物业管理员(投诉受理人)下拉列表
	@ResponseBody
	@RequestMapping("/complaint/propertyManagerList")
	public List<PropertyManager> getPropertyManagerList(Model model) throws JsonProcessingException {
		List<PropertyManager> propertyManagerList = complaintService.getPropertyManagerList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(propertyManagerList);
		model.addAttribute("propertyManagerList", json);
		return propertyManagerList;
	}
	
	//物业管理员(投诉受理人)信息显示
	@ResponseBody
	@RequestMapping("/complaint/showPropertyManagerInfo/{propertyManagerId}")
	public ComplaintVO getPropertyManagerByPid(@PathVariable("propertyManagerId")Integer propertyManagerId) {
		ComplaintVO complaintVO = null;
		if (propertyManagerId != null) {
			complaintVO = complaintService.getPropertyManagerByPid(propertyManagerId);
		}
		return complaintVO;
	}
	
	@ResponseBody
	@RequestMapping("/complaint/getComplaints")
	public Map<String, Object> getComplaint(HttpServletRequest request,HttpSession httpSession) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String stateId = request.getParameter("stateId");
		User user = (User) httpSession.getAttribute("user");
		Integer ownerId = null;
		Integer propertyManagerId = null;
		if (user.getRole().getId()==2) {
			ownerId = (user.getOwner().getId())==null?null:(user.getOwner().getId());
		}
		if (user.getRole().getId()==1) {
			propertyManagerId = (user.getPropertyManager().getId())==null?null:(user.getPropertyManager().getId());
		}
		List<ComplaintVO> complaints = complaintService.getComplaints(pageNO,pageSize,beginTime,endTime,stateId,ownerId,propertyManagerId);
		int count = complaintService.getCountComplaint(beginTime,endTime,stateId,ownerId,propertyManagerId);
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
	public String addComplaint(HttpServletRequest request,HttpSession httpSession) {
		String content = request.getParameter("content");
		String createTime = request.getParameter("createTime");
		Integer propertyManagerId = StringUtils.getInteger(request.getParameter("propertyManagerId"));
		User user = (User) httpSession.getAttribute("user");
		Integer ownerId = null;
		if (user.getRole().getId()==2) {
			ownerId = (user.getOwner().getId())==null?null:(user.getOwner().getId());
		}
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("content", content);
		params.put("createTime", createTime);
		params.put("propertyManagerId", propertyManagerId);
		params.put("ownerId", ownerId);
		complaintService.addComplaint(params);
		return "add";
	}
	
	//回显
	@ResponseBody
	@RequestMapping(value="/complaint/getComplaint/{id}", method=RequestMethod.GET)
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
		Integer stateId = StringUtils.getInteger(request.getParameter("stateId"));
		String returnContent = request.getParameter("returnContent");
		String finishTime = request.getParameter("finishTime");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("stateId", stateId);
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
