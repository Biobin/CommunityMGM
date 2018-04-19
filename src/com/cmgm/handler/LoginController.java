package com.cmgm.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cmgm.entity.User;
import com.cmgm.service.LoginService;

/**
 *
 * @author Bio
 * @date 2018年3月27日
 * @time 下午12:02:12
 * 登录功能
 */

@SessionAttributes(value={"user"})
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String loginNew(HttpSession httpSession){
		
		return "loginNew";
	}
	
	@RequestMapping("/index")
	public String loginSuccess(){
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/loginValidate")
	public String loginValidate(@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,Map<String, Object> map){
		User user = loginService.getUserByUsername(username);
		if (user == null) {
			return "2";
		}else if(!(password.trim()).equals(user.getPassword())){
			return "3";
		}else {
			map.put("user", user);
			return "1";
		}
	}
	
}
