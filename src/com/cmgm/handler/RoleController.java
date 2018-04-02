package com.cmgm.handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.common.StringUtils;
import com.cmgm.entity.Role;
import com.cmgm.service.RoleService;

/**
 *
 * @author Bio
 * @date   2018年3月30日
 * @time   下午3:42:36
 *
 * 用于角色维护
 */

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/role/roleManage")
	public String roleManage(){
		return "roleManage";
	};
	
	@ResponseBody
	@RequestMapping("/role/getRoles")
	public Map<String,Object> getRoles(HttpServletRequest request){
		int pageNO = Integer.parseInt(request.getParameter("page"));// 当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));// 每页行数
		List<Role> roles = roleService.getRoles(pageNO, pageSize);
		int count = roleService.getCountRoles();
		if(roles == null || roles.isEmpty()){
			roles = new ArrayList<>();
			count=0;
		}
		Map<String,Object> jsonMap=new HashMap<>();
		jsonMap.put("rows", roles);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/role/addOrUpdateRole", method=RequestMethod.POST)
	public Role addOrUpdateRole(HttpServletRequest request) throws Exception{
		String createTime = request.getParameter("createTime");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		Role role = null;
		if (id != null && !id.equals("")) {
			role = roleService.getRoleById(StringUtils.getInteger(id));
		} else {
			role = new Role();
		}
		role.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
		role.setName(name);
		return roleService.addOrUpdateRole(role);
	}
	
	@ResponseBody
	@RequestMapping("/role/deleteRole")
	public String deleteRole(Integer id){
		String candelete = "";
		if (id != null) {
			candelete = roleService.deleteRole(id);
		}
		return candelete;
	}
	
}
