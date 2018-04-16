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

import com.cmgm.VO.CommunalFacilitiesVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.entity.PropertyManager;
import com.cmgm.service.CommunalFacilitiesService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@RequestMapping("/communalFacilities/privateFacilitiesManage")
	public String privateFacilitiesManage() {
		return "privateFacilitiesManage";
	}
	
	//获取维修人员下拉列表
	@ResponseBody
	@RequestMapping("/communalFacilities/propertyManagerList")
	public List<PropertyManager> getPropertyManagerList(Model model) throws JsonProcessingException {
		List<PropertyManager> propertyManagerList = communalFacilitiesService.getPropertyManagerList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(propertyManagerList);
		model.addAttribute("propertyManagerList", json);
		return propertyManagerList;
	}
	
	//维修人员信息显示
	@ResponseBody
	@RequestMapping("/communalFacilities/showPropertyManagerInfo/{propertyManagerId}")
	public CommunalFacilitiesVO getProperManagerByPid(@PathVariable(value="propertyManagerId")Integer propertyManagerId) {
		CommunalFacilitiesVO communalFacilitiesVO = null;
		if (propertyManagerId!=null) {
			communalFacilitiesVO = communalFacilitiesService.getPropertyManagerByPid(propertyManagerId);
		}
		return communalFacilitiesVO;
	}
	
	//设施类型下拉列表
	@ResponseBody
	@RequestMapping("/communalFaStyle/communalFaStyleList")
	public List<CommunalFaStyle> getCommunalFaStyleList(Model model) throws JsonProcessingException {
		List<CommunalFaStyle> communalFaStyleList = communalFacilitiesService.getCommunalFaStyleList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(communalFaStyleList);
		model.addAttribute("communalFaStyleList", json);
		return communalFaStyleList;
	}
	
	//固定私人设施类型下拉
	@ResponseBody
	@RequestMapping("/communalFaStyle/privateFaStyleList")
	public List<CommunalFaStyle> getPrivateFaStyleList(Model model) throws JsonProcessingException {
		List<CommunalFaStyle> privateFaStyleList = communalFacilitiesService.getPrivateFaStyleList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(privateFaStyleList);
		model.addAttribute("privateFaStyleList", json);
		return privateFaStyleList;
	}
	
	//物业管理员使用，查询出全部设施，包括私人设施
	@ResponseBody
	@RequestMapping("/communalFacilities/getCommualFacilities")
	public Map<String, Object> getCommualFacilities(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		List<CommunalFacilitiesVO> communalFacilitiesVOs = communalFacilitiesService.getCommunalFacilities(pageNO,pageSize,code,name);
		int count = communalFacilitiesService.getCountCommunalFacilities(code,name);
		if (communalFacilitiesVOs == null || communalFacilitiesVOs.isEmpty()) {
			communalFacilitiesVOs = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", communalFacilitiesVOs);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	//业主使用，查出所有业主添加的私人设施
	@ResponseBody
	@RequestMapping("/communalFacilities/getPrivateFacilities")
	public Map<String, Object> getPrivateFacilities(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		List<CommunalFacilitiesVO> communalFacilitiesVOs = communalFacilitiesService.getPrivateFacilities(pageNO,pageSize,code,name);
		int count = communalFacilitiesService.getCountCommunalFacilities(code,name);
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
	@RequestMapping("/communalFacilities/addCommunalFacilities")
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
	@RequestMapping(value="/communalFacilities/getCommunalFacilitiesById/{id}", method=RequestMethod.GET)
	public CommunalFacilitiesVO getCommunalFacilitiesById(@PathVariable("id")Integer id) {
		CommunalFacilitiesVO communalFacilitiesVO = null;
		if (id != null) {
			communalFacilitiesVO = communalFacilitiesService.getCommunalFaclitiesById(id);
		}
		
		return communalFacilitiesVO;
	}
	
	@ResponseBody
	@RequestMapping("/communalFacilities/updateCommunalFacilities/{id}")
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
	@RequestMapping("/communalFacilities/deleteCommunalFacilities/{id}")
	public String deleteCommunalFacilities(@PathVariable("id")Integer id) {
		communalFacilitiesService.deleteCommunalFacilities(id);
		return "delete";
	}
	
}
