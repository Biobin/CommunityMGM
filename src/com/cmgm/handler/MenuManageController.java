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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.VO.MenuVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.Menu;
import com.cmgm.entity.Role;
import com.cmgm.service.MenuManageService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 下午6:23:18
 * 菜单管理维护
 */

@Controller("/menu")
public class MenuManageController {

	@Autowired
	private MenuManageService menuManageService;
	
	@RequestMapping("/menuManage")
	public String menuManage() {
		return "menuManage";
	}
	
	//获取角色下拉列表
	@ResponseBody
	@RequestMapping("/menuManage/roleList")
	public List<Role> getRoleList(Model model) throws JsonProcessingException {
		List<Role> roleList = menuManageService.getRoleList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(roleList);
		model.addAttribute("roleList", json);
		return roleList;
	}

	//获取父节点下拉列表
	@ResponseBody
	@RequestMapping("/menuManage/menuList")
	public List<Menu> getMenuList(Model model) throws JsonProcessingException {
		List<Menu> menuList = menuManageService.getMenuList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(menuList);
		model.addAttribute("menuList", json);
		return menuList;
	}
	
	//查询及显示(查询条件：名称,角色)
	@ResponseBody
	@RequestMapping("/menuManage/getMenus")
	public Map<String, Object> getMenus(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
//		Integer roleId = StringUtils.getInteger(request.getParameter("roleId"));
//		String text = request.getParameter("text");
		List<MenuVO> menuVOs = menuManageService.getMenus(pageNO,pageSize);
		int count = menuManageService.getCountMenu();
		if (menuVOs == null || menuVOs.isEmpty()) {
			menuVOs = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", menuVOs);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/menuManage/addMenu",method=RequestMethod.POST)
	public String addMenu(@RequestParam(value="rolesIds",required=false)Integer[] rolesIds, HttpServletRequest request) {
		String text = request.getParameter("text");
		Integer stateId = StringUtils.getInteger(request.getParameter("stateId"));
		String iconCls = request.getParameter("iconCls");
		String url = request.getParameter("url");
		Integer pid = StringUtils.getInteger(request.getParameter("pid"));
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("text", text);
		params.put("stateId", stateId);
		params.put("iconCls", iconCls);
		params.put("url", url);
		params.put("pid", pid);
		params.put("rolesIds", rolesIds);
		menuManageService.addMenu(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="/menuMange/getMenuById/{id}", method=RequestMethod.GET)
	public MenuVO getMenuById(@PathVariable("id")Integer id,HttpServletRequest request) {
		MenuVO menuVO = null;
		if (id != null) {
			menuVO = menuManageService.getMenuById(id);
		}
		return menuVO;
	}
	
	@ResponseBody
	@RequestMapping("/menuManage/updateMenu/{id}")
	public String updateMenu(@RequestParam(value="rolesIds",required=false)Integer[] rolesIds, @PathVariable("id")Integer id, HttpServletRequest request) {
		String text = request.getParameter("text");
		Integer stateId = StringUtils.getInteger(request.getParameter("stateId"));
		String iconCls = request.getParameter("iconCls");
		String url = request.getParameter("url");
		Integer pid = StringUtils.getInteger(request.getParameter("pid"));
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("text", text);
		params.put("stateId", stateId);
		params.put("iconCls", iconCls);
		params.put("url", url);
		params.put("pid", pid);
		params.put("rolesIds", rolesIds);
		menuManageService.updateMenu(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping("/menuManage/deleteMenu/{id}")
	public String deleteMenu(@PathVariable("id")Integer id) {
		menuManageService.deleteMenu(id);
		return "delete";
	}
	
}
