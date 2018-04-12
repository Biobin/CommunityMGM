package com.cmgm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cmgm.VO.OwnerVO;
import com.cmgm.common.StringUtils;
import com.cmgm.entity.User;
import com.cmgm.service.OwnerService;

/**
 *
 * @author Bio
 * @date 2018年4月4日
 * @time 下午3:12:37
 * 业主管理页
 */

@SessionAttributes(value={"user"})
@Controller
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	//用于页面跳转(ownerManage.jsp)
	@RequestMapping("/owner/ownerManage")
	public String ownerManage(){
		return "ownerManage";
	}
	
	//用于页面跳转(ownerInfo.jsp)
	@RequestMapping("/owner/ownerInfo")
	public String ownerInfo() {
		return "ownerInfo";
	}
	
	@ResponseBody
	@RequestMapping("/owner/getOwners")
	public Map<String, Object> getOwners(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		List<OwnerVO> ownerVOs = ownerService.getOwners(pageNO,pageSize);
		int count = ownerService.getCountOwner();
		if (ownerVOs == null || ownerVOs.isEmpty()) {
			ownerVOs = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", ownerVOs);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/owner/addOwner", method=RequestMethod.POST)
	public String addOwner(HttpServletRequest request) {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String IDNumber = request.getParameter("idnumber");
		String address = request.getParameter("address");
		String startTime = request.getParameter("startTime");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("name", name);
		params.put("username", username);
		params.put("phone", phone);
		params.put("email", email);
		params.put("password", password);
		params.put("IDNumber", IDNumber);
		params.put("address", address);
		params.put("startTime", startTime);
		ownerService.addOwner(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="/owner/getOwnerById/{id}", method=RequestMethod.GET)
	public OwnerVO getOwnerById(@PathVariable("id")Integer id,HttpServletRequest request) {
		OwnerVO ownerVO = null;
		if (id != null) {
			ownerVO = ownerService.getOwnerById(id);
		}
		return ownerVO;
	}
	
	@ResponseBody
	@RequestMapping(value="/owner/updateOwner/{id}")
	public String updateOwner(@PathVariable("id")Integer id,HttpServletRequest request) {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String IDNumber = request.getParameter("idnumber");
		String address = request.getParameter("address");
		String startTime = request.getParameter("startTime");
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("name", name);
		params.put("username", username);
		params.put("phone", phone);
		params.put("email", email);
		params.put("password", password);
		params.put("IDNumber", IDNumber);
		params.put("address", address);
		params.put("startTime", startTime);
		ownerService.updateOwner(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping(value="/owner/deleteOwner/{id}")
	public String deleteOwner(@PathVariable("id")Integer id) {
		ownerService.deleteOwner(id);
		return "delete";
	}
	
	@ResponseBody
	@RequestMapping("/owner/showOwnerInfo")
	public OwnerVO showOwnerInfo(HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		Integer id = (user.getOwner().getId())==null?null:(user.getOwner().getId());
		OwnerVO ownerVO = null;
		if (id != null) {
			ownerVO = ownerService.getOwnerById(StringUtils.getInteger(id));
		}
		return ownerVO;
	}
	
}
