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

import com.cmgm.VO.CommunalFacilitiesVO;
import com.cmgm.common.StringUtils;
import com.cmgm.service.CommunalFacilitiesService;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午10:17:57
 * 公共设施管理
 */

@Controller
public class CommunalFacilitiesController {

	@Autowired
	private CommunalFacilitiesService communalFacilitiesService;
	
	@RequestMapping("/communalFacilities/communalFacilitiesManage")
	public String CommunalFacilitiesManage() {
		return "communalFacilitiesManage";
	}
	
	@ResponseBody
	@RequestMapping("/communalFacilities/getCommualFacilities")
	public Map<String, Object> getCommualFacilities(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		List<CommunalFacilitiesVO> communalFacilitiesVOs = communalFacilitiesService.getCommunalFacilities(pageNO,pageSize);
		int count = communalFacilitiesService.getCountCommunalFacilities();
		if (communalFacilitiesVOs == null || communalFacilitiesVOs.isEmpty()) {
			communalFacilitiesVOs = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", communalFacilitiesVOs);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("communalFacilities/addCommunalFacilities")
	public String addCommunalFacilities(HttpServletRequest request) {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		Integer communalFaStyleId = StringUtils.getInteger(request.getParameter("communalFaStyleId"));
		String beginUsingTime = request.getParameter("beginUsingTime");
		String details = request.getParameter("details");
		Integer propertyManagerId = StringUtils.getInteger(request.getParameter("propertyManagerId"));
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		params.put("name", name);
		params.put("communalFaStyleId", communalFaStyleId);
		params.put("beginUsingTime", beginUsingTime);
		params.put("details", details);
		params.put("propertyManagerId", propertyManagerId);
		communalFacilitiesService.addCommunalFacilities(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="communalFacilities/getCommunalFacilitiesById/{id}", method=RequestMethod.GET)
	public CommunalFacilitiesVO getCommunalFacilitiesById(@PathVariable("id")Integer id) {
		CommunalFacilitiesVO communalFacilitiesVO = null;
		if (id != null) {
			communalFacilitiesVO = communalFacilitiesService.getCommunalFaclitiesById(id);
		}
		
		return communalFacilitiesVO;
	}
	
	@ResponseBody
	@RequestMapping("communalFacilities/updateCommunalFacilities/{id}")
	public String updateCommunalFacilities(@PathVariable("id")Integer id, HttpServletRequest request) {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		Integer communalFaStyleId = StringUtils.getInteger(request.getParameter("communalFaStyleId"));
		String beginUsingTime = request.getParameter("beginUsingTime");
		String details = request.getParameter("details");
		Integer propertyManagerId = StringUtils.getInteger(request.getParameter("propertyManagerId"));
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("code", code);
		params.put("name", name);
		params.put("communalFaStyleId", communalFaStyleId);
		params.put("beginUsingTime", beginUsingTime);
		params.put("details", details);
		params.put("propertyManagerId", propertyManagerId);
		communalFacilitiesService.updateCommunalFacilities(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping("communalFacilities/deleteCommunalFacilities/{id}")
	public String deleteCommunalFacilities(@PathVariable("id")Integer id) {
		communalFacilitiesService.deleteCommunalFacilities(id);
		return "delete";
	}
	
}
