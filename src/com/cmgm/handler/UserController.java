package com.cmgm.handler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.entity.Role;
import com.cmgm.entity.User;
import com.cmgm.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Bio
 * @date   2018年3月30日
 * @time   下午2:34:13
 * 
 * 用于用户维护
 *
 */

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/userManage")
	public String roleManage(Model model) throws Exception{
		List<Role> allRole = userService.getAllRole();
		ObjectMapper mapper = new ObjectMapper();
		// 属性为NULL 不序列化
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json ="[]";
		
		json=mapper.writeValueAsString(allRole);
		model.addAttribute("allRole", json);
		return "userManage";
	};
	
	@ResponseBody
	@RequestMapping("/user/getUsers")
	public Map<String,Object> getusers(HttpServletRequest request) throws Exception{
		int pageNO = Integer.parseInt(request.getParameter("page"));// 当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));// 每页行数
		List<User> users = userService.getUsers(pageNO, pageSize);
		int count = userService.getCountUser();
		if(users == null || users.isEmpty()){
			users = new ArrayList<>();
			count=0;
		}
		Map<String,Object> jsonMap=new HashMap<>();
		jsonMap.put("rows", users);
		jsonMap.put("total", count);
		return jsonMap;
	};
	
//	@ResponseBody
//	@RequestMapping(value="/user/addOrUpdateUser")
//	public String addOrUpdateUser(@RequestParam(value="roleId",required=false)Integer roleId,HttpServletRequest request) throws Exception{
//		String username = request.getParameter("username");
//		String idString = request.getParameter("id");
//		//判断用户名是否存在（用户名没有修改时不算存在，可以保存）
//		if(idString == null || idString.equals("")){
//			if (userService.whetherExistUser(username)) {
//				return "usernameExist";
//			}
//		}else{
//			if (!username.equals(userService.getUserById(Integer.parseInt(idString)).getUsername())) {
//				if (userService.whetherExistUser(username)) {
//					return "usernameExist";
//				}
//			}
//		}
//		String password = request.getParameter("password");
//		String userName = request.getParameter("username");
//		String createTime = request.getParameter("createTime");
//		User user = new User();
//		if (idString != null && !idString.equals("")) {
//			user = userService.getUserById(Integer.parseInt(idString));
//		} else{
//			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
//			user.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
//		}
//		user.setUsername(userName);
//		user.setPassword(password);
//		userService.addOrUpdateuser(user, roleId);
//		if (user.getId() != null) {
//			return "update";
//		}else{
//			return "add";
//		}
//	}
	
}
