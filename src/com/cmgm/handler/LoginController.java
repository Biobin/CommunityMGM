package com.cmgm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmgm.entity.User;
import com.cmgm.service.LoginService;

/**
 *
 * @author Bio
 * @date 2018年3月27日
 * @time 下午12:02:12
 * 登录功能
 */

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String loginNew(@RequestParam(value="cancel",required=false) Integer cancel,HttpSession httpSession){
		if (cancel!=null&&cancel==1) {
			httpSession.removeAttribute("user");
		}
		return "loginNew";
	}
	
	@RequestMapping("/index")
	public String loginSuccess(){
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/loginValidate")
	public String loginValidate(@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,Map<String, Object> map,HttpServletRequest request){
		User user = loginService.getUserByUsername(username);
		HttpSession session = request.getSession();
		if (user == null) {
			return "2";
		}else if(!(password.trim()).equals(user.getPassword())){
			return "3";
		}else {
			map.put("user", user);
			session.setAttribute("user", user);
			return "1";
		}
	}
	
}
