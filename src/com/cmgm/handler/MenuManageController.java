package com.cmgm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.service.MenuManageService;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 下午6:23:18
 * 菜单管理维护
 */

@Controller
public class MenuManageController {

	@Autowired
	private MenuManageService menuManageService;
	
	@RequestMapping("/menu/menuManage")
	public String menuManage() {
		return "menuManage";
	}
	
	@ResponseBody
	@RequestMapping("/menu/deleteMenu/{id}")
	public String deleteMenu(@PathVariable("id")Integer id) {
		menuManageService.deleteMenu(id);
		return "delete";
	}
	
}
