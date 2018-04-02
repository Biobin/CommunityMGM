package com.cmgm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	
}
