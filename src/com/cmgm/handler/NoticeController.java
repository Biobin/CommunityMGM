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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cmgm.entity.Notice;
import com.cmgm.service.NoticeService;


/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午2:45:45
 * 公告管理
 */

@SessionAttributes(value={"user"})
@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/notice/noticeManage")
	public String noticeManage(){
		return "noticeManage";
	};
	
	@ResponseBody
	@RequestMapping("/notice/getNotices")
	public Map<String, Object> getNotice(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		List<Notice> notices = noticeService.getNotices(pageNO,pageSize,code,title);
		int count = noticeService.getCountNotice(code,title);
		if(notices == null || notices.isEmpty()) {
			notices = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", notices);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping("/notice/addNotice")
	public String addNotice(HttpServletRequest request) {
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String createTime = request.getParameter("createTime");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("title", title);
		params.put("content", content);
		params.put("createTime", createTime);
		noticeService.addNotice(params);
		return "add";
	}
	
	@ResponseBody
	@RequestMapping(value="/notice/getNotice/{id}", method=RequestMethod.GET)
	public Notice getNotice(@PathVariable("id")Integer id) {
		Notice notice = null;
		if (id != null) {
			notice = noticeService.getNotice(id);
		}
		return notice;
	}
	
	@ResponseBody
	@RequestMapping("/notice/updateNotice/{id}")
	public String updateNotice(@PathVariable("id")Integer id, HttpServletRequest request) {
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String createTime = request.getParameter("createTime");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("code", code);
		params.put("title", title);
		params.put("content", content);
		params.put("createTime", createTime);
		noticeService.updateNotice(params);
		return "update";
	}
	
	@ResponseBody
	@RequestMapping("/notice/deleteNotice/{id}")
	public String deleteNotice(@PathVariable("id")Integer id) {
		noticeService.deleteNotice(id);
		return "delete";
	}
	
}
