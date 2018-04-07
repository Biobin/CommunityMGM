package com.cmgm.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.VO.MenuVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.User;
import com.cmgm.service.MenuService;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午11:01:14
 * 菜单功能
 */

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping("/getMenus")
	public List<MenuVO> getMenusByPid(HttpServletRequest request, HttpSession httpSession) {
		String pid = request.getParameter("menuId");
		User user = (User) httpSession.getAttribute("user");
		Integer roleId = user.getRole().getId();
		List<MenuVO> menuVOs = new ArrayList<>();
		if (pid != null && !pid.equals("")) {
			menuVOs = menuService.getMenus(roleId, StringUtils.getInteger(pid));
		}else{
			//在数据库中设置根节点的id为-1了。
			menuVOs = menuService.getMenus(roleId,-1);
		}
		return menuVOs;
	}
	
}
