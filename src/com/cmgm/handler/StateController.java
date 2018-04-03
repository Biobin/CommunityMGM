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
import com.cmgm.entity.State;
import com.cmgm.service.StateService;

/**
 *
 * @author Bio
 * @date 2018年4月2日
 * @time 下午5:55:01
 * 状态维护字典
 */

@Controller
public class StateController {

	@Autowired
	private StateService stateService;
	
	@RequestMapping("/state/stateManage")
	public String stateManage() {
		return "stateManage";
	}
	
	@ResponseBody
	@RequestMapping(value="/state/getState", method=RequestMethod.POST)
	public Map<String, Object> getStates(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));// 当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));// 每页行数
		List<State> states = stateService.getStates(pageNO,pageSize);
		int count = stateService.getCountState();
		if (states == null || states.isEmpty()) {
			states = new ArrayList<>();
			count = 0;
		}
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("rows", states);
		jsonMap.put("total", count);
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/state/addOrUpdateState", method=RequestMethod.POST)
	public State addOrUpdateState(HttpServletRequest request) {
		String name = request.getParameter("name");
		String idString = request.getParameter("id");
		State state = null;
		if (idString !=null && idString.equals("")) {
			state = stateService.getStateById(StringUtils.getInteger(idString));
		} else {
			state = new State();
		}
		state.setName(name);
		return stateService.addOrUpdateState(state);
	}
	
	@ResponseBody
	@RequestMapping("/state/deleteState")
	public String deleteState(Integer id) {
		String cadelete = "";
		if (id != null) {
			cadelete = stateService.deleteState(id);
		}
		return cadelete;
	}
	
}
