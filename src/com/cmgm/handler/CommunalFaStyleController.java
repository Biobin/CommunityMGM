package com.cmgm.handler;

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
import com.cmgm.entity.CommunalFaStyle;
import com.cmgm.service.CommunalFaStyleService;

/**
 *
 * @author Bio
 * @date 2018年4月4日
 * @time 上午11:40:38
 * 小区公共设施类型字典维护
 */

@Controller
public class CommunalFaStyleController {

	@Autowired
	private CommunalFaStyleService communalFaStyleService;
	
	@RequestMapping("/communalFaStyle/communalFaStyleManage")
	public String communalFaStyleManage(){
		return "communalFaStyleManage";
	};
	
	@ResponseBody
	@RequestMapping(value="/communalFaStyle/getCommunalFaStyle", method=RequestMethod.POST)
	public Map<String, Object> getCommunalFaStyles(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));// 当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));// 每页行数
		List<CommunalFaStyle> communalFaStyles = communalFaStyleService.getCommunalFaStyle(pageNO,pageSize);
		int count = communalFaStyleService.getCountCommunalFaStyle();
		if (communalFaStyles == null || communalFaStyles.isEmpty()) {
			communalFaStyles = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", communalFaStyles);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/communalFaStyle/addOrUpdateCommunalFaStyle", method=RequestMethod.POST)
	public CommunalFaStyle addOrUpdateCommunalFaStyle(HttpServletRequest request) {
		String name = request.getParameter("name");
		String idString = request.getParameter("id");
		CommunalFaStyle communalFaStyle = null;
		if (idString != null && !idString.equals("")) {
			communalFaStyle = communalFaStyleService.getCommunalFaStyleById(StringUtils.getInteger(idString));
		} else {
			communalFaStyle = new CommunalFaStyle();
		}
		communalFaStyle.setName(name);
		return communalFaStyleService.addOrUpdateCommunalFaStyle(communalFaStyle);
	}
	
	@ResponseBody
	@RequestMapping("/communalFaStyle/deleteCommunalFaStyle")
	public String deleteCommunalFaStyle(Integer id) {
		String cadelete = "";
		if (id != null) {
			cadelete = communalFaStyleService.deleteCommunalFaStyle(id);
		}
		return cadelete;
	}
	
}
