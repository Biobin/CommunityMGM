package com.cmgm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmgm.service.MaintenanceService;

/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:40:02
 * 报修功能
 */

@Controller
public class MaintenanceController {

	@Autowired
	private MaintenanceService maintenanceService;
	
	@RequestMapping("/maintenance/maintenanceManage")
	public String maintenanceManage(){
		return "maintenanceManage";
	};
	
	
	
}
