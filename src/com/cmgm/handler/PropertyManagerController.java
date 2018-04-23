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

import com.cmgm.VO.PropertyManagerVO;
import com.cmgm.service.PropertyManagerService;

/**
 *
 * @author Bio
 * @date 2018年4月3日
 * @time 上午11:33:05
 * 用于物业管理员字典维护
 */

@Controller
public class PropertyManagerController {

	@Autowired
	private PropertyManagerService propertyManagerService;
	
	@RequestMapping("/propertyManager/propertyManagerManage")
	public String propertyManagerManage(){
		return "propertyManagerManage";
	};
	
	@ResponseBody
	@RequestMapping("/propertyManager/getPropertyManagers")
	public Map<String, Object> getPropertyManagers(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		List<PropertyManagerVO> propertyManagerVOs = propertyManagerService.getPropertyManagers(pageNO,pageSize);
		int count = propertyManagerService.getCountPropertyManager();
		if (propertyManagerVOs == null || propertyManagerVOs.isEmpty()) {
			propertyManagerVOs = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", propertyManagerVOs);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/propertyManager/addPropertyManager", method=RequestMethod.POST)
	public String addPropertyManager(HttpServletRequest request) {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("name", name);
		params.put("username", username);
		params.put("phone", phone);
		params.put("email", email);
		params.put("password", password);
		propertyManagerService.addPropertyManager(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="/propertyManager/getPropertyManagerById/{id}", method=RequestMethod.GET)
	public PropertyManagerVO getPropertyManagerById(@PathVariable("id")Integer id,HttpServletRequest request) {
		PropertyManagerVO propertyManagerVO = null;
		if (id != null) {
			propertyManagerVO = propertyManagerService.getPropertyManagerById(id);
		}
		return  propertyManagerVO;
	}
	
	@ResponseBody
	@RequestMapping(value="/propertyManager/updatePropertyManager/{id}")
	public String updatePropertyManager(@PathVariable("id")Integer id,HttpServletRequest request) {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("rePassword");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("name", name);
		params.put("username", username);
		params.put("phone", phone);
		params.put("email", email);
		params.put("password", password);
		propertyManagerService.updatePropertyManager(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping(value="/propertyManager/deletePropertyManager/{id}")
	public String deletePropertyManager(@PathVariable("id")Integer id) {
		propertyManagerService.deletePropertyManager(id);
		return "delete";
	}
	
}
