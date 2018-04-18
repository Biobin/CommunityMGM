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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.VO.MaintenanceVO;
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.entity.CommunalFacilities;
import com.cmgm.entity.Maintenance;
import com.cmgm.entity.Owner;
import com.cmgm.entity.PropertyManager;
import com.cmgm.entity.User;
import com.cmgm.service.MaintenanceService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:40:02
 * 报修功能
 */

@Controller
public class MaintenanceController {

	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping("/maintenance/maintenanceManage")
	public String maintenanceManage(){
		return "maintenanceManage";
	};
	
	//获取业主(报修人)下拉列表
	@ResponseBody
	@RequestMapping("/maintenance/ownerList")
	public List<Owner> getOwnerList(Model model) throws JsonProcessingException {
		List<Owner> ownerList = maintenanceService.getOwnerList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(ownerList);
		model.addAttribute("ownerList", json);
		return ownerList;
	}
	
	//获取物业管理员(维修负责人)下拉列表
//	@ResponseBody
//	@RequestMapping("/maintenance/propertyManagerList")
//	public List<PropertyManager> getPropertyManagerList(Model model) throws JsonProcessingException {
//		List<PropertyManager> propertyManagerList = maintenanceService.getPropertyManagerList();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setSerializationInclusion(Include.NON_NULL);
//		String json = "";
//		json = mapper.writeValueAsString(propertyManagerList);
//		model.addAttribute("propertyManagerList", json);
//		return propertyManagerList;
//	}
	
	//物业管理员(维修负责人)信息显示
//	@ResponseBody
//	@RequestMapping("/maintenance/showPropertyManagerInfo/{propertyManagerId}")
//	public MaintenanceVO getPropertyManagerByPid(@PathVariable("propertyManagerId")Integer propertyManagerId) {
//		MaintenanceVO maintenanceVO = null;
//		if (propertyManagerId!=null) {
//			maintenanceVO = maintenanceService.getPropertyManagerByPid(propertyManagerId);
//		}
//		return maintenanceVO;
//	}
	
	//获取设施类型下拉列表
	@ResponseBody
	@RequestMapping("/maintenance/communalFaStyleList")
	public List<CommunalFaStyle> getCommunalFaStyleList(Model model) throws JsonProcessingException {
		List<CommunalFaStyle> communalFaStyleList = maintenanceService.getCommunalFaStyleList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(communalFaStyleList);
		model.addAttribute("communalFaStyleList", json);
		return communalFaStyleList;
	}
	
	//根据设施类型获取设施下拉列表
	@ResponseBody
	@RequestMapping("/maintenance/communalFacilitiesList")
	public List<CommunalFacilities> getCommunalFacilitiesList(@RequestParam(name="communalFaStyleId")Integer communalFaStyleId) {
		List<CommunalFacilities> communalFacilitieList = new ArrayList<>();
		if (communalFaStyleId!=null) {
			communalFacilitieList = maintenanceService.getCommunalFacilitiesList(communalFaStyleId);
		}
		return communalFacilitieList;
	}
	
	//根据设备显示维护负责人
	@ResponseBody
	@RequestMapping("/maintenance/propertyManagerList")
	public List<PropertyManager> getPropertyManagerList(@RequestParam(name="communalFacilitiesId")Integer communalFacilitiesId) {
		List<PropertyManager> propertyManagerList = new ArrayList<>();
		if (communalFacilitiesId!=null) {
			propertyManagerList = maintenanceService.getPropertyManagerList(communalFacilitiesId);
		}
		return propertyManagerList;
	}
	
	@ResponseBody
	@RequestMapping("/maintenance/getMaintenances")
	public Map<String, Object> getMaintenances(HttpServletRequest request, HttpSession httpSession) {
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
		List<Maintenance> maintenances = maintenanceService.getMaintenances(pageNO,pageSize,beginTime,endTime,stateId,ownerId,propertyManagerId);
		int count = maintenanceService.getCountMaintenance(beginTime,endTime,stateId,ownerId,propertyManagerId);
		if (maintenances == null || maintenances.isEmpty()) {
			maintenances = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", maintenances);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	//业主添加报修内容
	@ResponseBody
	@RequestMapping("/maintenance/getMaintenance")
	public String addMaintenance(HttpServletRequest request,HttpSession httpSession) {
		
		return "add";
	}
	
	//回显
	@ResponseBody
	@RequestMapping("/maintenance/getMaintenance/{id}")
	public MaintenanceVO getMaintenance(@PathVariable("id")Integer id) {
		MaintenanceVO maintenanceVO = null;
		
		return maintenanceVO;
	}
	
	
	
	
}
