package com.cmgm.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmgm.VO.CarVO;
import com.cmgm.service.CarService;

/**
 *
 * @author Bio
 * @date   2018年4月8日
 * @time   上午1:01:29
 * 车辆管理
 */

@Controller
public class CarController {

	@Autowired
	private CarService carService;
	
	@RequestMapping("/car/carManage")
	public String carManage() {
		return "carManage";
	}
	
	public Map<String, Object> getCars(HttpServletRequest request) {
		int pageNO = Integer.parseInt(request.getParameter("page"));	//当前页
		int pageSize = Integer.parseInt(request.getParameter("rows"));	//每页行数
		List<CarVO> carVOs = carService.getCars(pageNO,pageSize);
		
		Map<String, Object> jsonMap = new HashMap<>();
		return jsonMap;
	}
	
}
