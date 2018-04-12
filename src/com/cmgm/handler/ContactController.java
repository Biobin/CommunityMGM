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

import com.cmgm.entity.Contact;
import com.cmgm.entity.User;
import com.cmgm.service.ContactService;

/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午11:18:55
 * 通讯录管理
 */

@SessionAttributes(value={"user"})
@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/contact/contactManage")
	public String contactManage() {
		return "contactManage";
	}
	
	@ResponseBody
	@RequestMapping("/contact/getContacts")
	public Map<String, Object> getContacts(HttpServletRequest request,HttpSession httpSession) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		String name = request.getParameter("name");
		User user = (User) httpSession.getAttribute("user");
		Integer uid = (user.getId())==null?null:(user.getId());
		List<Contact> contacts = contactService.getContacts(pageNO,pageSize,name,uid);
		int count = contactService.getCountContact(name,uid);
		if (contacts == null || contacts.isEmpty()) {
			contacts = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", contacts);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/contact/addContact")
	public String addContact(HttpServletRequest request,HttpSession httpSession) {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		User user = (User) httpSession.getAttribute("user");
		Integer userId = (user.getId())==null?null:(user.getId());
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("phone", phone);
		params.put("email", email);
		params.put("userId", userId);
		contactService.addContact(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="/contact/getContact/{id}", method=RequestMethod.GET)
	public Contact getContact(@PathVariable("id")Integer id,HttpServletRequest request) {
		Contact contact = null;
		if (id != null) {
			contact = contactService.getContact(id);
		}
		return contact;
	}
	
	@ResponseBody
	@RequestMapping("/contact/updateContact/{id}")
	public String updateContact(@PathVariable("id")Integer id, HttpServletRequest request,HttpSession httpSession) {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		User user = (User) httpSession.getAttribute("user");
		Integer userId = (user.getId())==null?null:(user.getId());
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("name", name);
		params.put("phone", phone);
		params.put("email", email);
		params.put("userId", userId);
		contactService.updateContact(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping("/contact/deleteContact/{id}")
	public String deleteContact(@PathVariable("id")Integer id) {
		contactService.deleteContact(id);
		return "delete";
	}
	
}
